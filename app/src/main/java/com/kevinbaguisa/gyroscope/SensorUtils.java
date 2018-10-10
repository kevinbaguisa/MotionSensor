package com.kevinbaguisa.gyroscope;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class SensorUtils {
// Collect sensors data for specific period and return statistics of
// sensor values e.g. mean and variance for x, y and z-axis
    public static RunningStat[] sensorStats(Context context, int sensorType,
                                            long timeout) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<RunningStat[]> future = executor.submit(new SensorTask(context,
                sensorType, timeout));

        RunningStat[] stats = future.get();
        return stats;

    }

    private static class SensorTask implements Callable<RunningStat[]> {
        private final Context context;
        private final long timeout;
        private final int sensorType;
        // We need a dedicated handler for the onSensorChanged
        HandlerThread handler = new HandlerThread("SensorHandlerThread");

        public SensorTask(Context context, int sensorType, long timeout) {
            this.context = context;
            this.timeout = timeout;
            this.sensorType = sensorType;
        }

        @Override
        public RunningStat[] call() throws Exception {
            final SensorCollector collector = new SensorCollector(context);
            handler.start();
            Thread sensorThread = new Thread() {
                public void run() {
                    collector.start(sensorType,
                            new Handler(handler.getLooper()));
                };
            };
            sensorThread.start();
            Thread.sleep(timeout);
            return collector.finishWithResult();
        }
    }

    private static class SensorCollector implements SensorEventListener {
        protected Context context;
        protected RunningStat[] runningStat;
        protected SensorManager sensorManager;
        protected int sensorType;

        public SensorCollector(Context context) {
            this.context = context;
        }

        protected void start(int sensorType, Handler handle) {
            if (runningStat == null) {
                runningStat = new RunningStat[3];
                runningStat[0] = new RunningStat(3);
                runningStat[1] = new RunningStat(3);
                runningStat[2] = new RunningStat(3);
            } else {
                runningStat[0].clear();
                runningStat[1].clear();
                runningStat[2].clear();
            }
            this.sensorType = sensorType;
            sensorManager = (SensorManager) context
                    .getSystemService(Context.SENSOR_SERVICE);
            Sensor sensor = sensorManager.getDefaultSensor(sensorType);
            sensorManager.registerListener(this, sensor,
                    SensorManager.SENSOR_DELAY_NORMAL, handle);
        }

        public RunningStat[] finishWithResult() {
            if (sensorManager != null) {
                sensorManager.unregisterListener(this);
            }
            return runningStat;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == sensorType) {
                runningStat[0].push(event.values[0]);
                runningStat[1].push(event.values[1]);
                runningStat[2].push(event.values[2]);
            }
        }
    }
}
