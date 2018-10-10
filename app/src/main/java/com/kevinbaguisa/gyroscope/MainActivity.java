package com.kevinbaguisa.gyroscope;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnCalc;
    SensorManager sensorManager;
    Sensor gyroscopeSensor, acceleromSensor;
    TextView showValueZ, showValueY, showValueX;
    TextView showValueZA, showValueYA, showValueXA;
    TextView intensityLevel, tvDispAvgX, tvDispAvgY, tvDispAvgZ;
    EditText etTimePer;
    float xValG, yValG, zValG, xValUnsG, yValUnsG, zValUnsG;
    float xValA, yValA, zValA;
    LinearLayout zLL;
    DecimalFormat df;
    boolean high;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        df = new DecimalFormat("00.00");

        zLL = (LinearLayout) findViewById(R.id.zLL);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        acceleromSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        showValueZ = (TextView) findViewById(R.id.tvZValue);
        showValueY = (TextView) findViewById(R.id.tvYValue);
        showValueX = (TextView) findViewById(R.id.tvXValue);
        intensityLevel = (TextView) findViewById(R.id.intLvlNumTV);
        btnCalc = (Button) findViewById(R.id.calcBtn);
        tvDispAvgX = (TextView) findViewById(R.id.XdispAvgTV);
        tvDispAvgY = (TextView) findViewById(R.id.YdispAvgTV);
        tvDispAvgZ = (TextView) findViewById(R.id.ZdispAvgTV);

        etTimePer = (EditText) findViewById(R.id.timePerET);

        SensorEventListener gyroscopeSensorListener = new SensorEventListener(){
            @Override
            public void onSensorChanged(SensorEvent sensorEvent)
            {
                xValG = sensorEvent.values[0];
                yValG = sensorEvent.values[1];
                zValG = sensorEvent.values[2];

                if(xValG < 0){
                    xValUnsG = xValG * -1.0f;
                }
                if(yValG < 0){
                    yValUnsG = yValG * -1.0f;
                }
                if(zValG < 0){
                    zValUnsG = zValG * -1.0f;
                }

                showValueX.setText(df.format(xValG));
                showValueY.setText(df.format(yValG));
                showValueZ.setText(df.format(zValG));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i){

            }
        };

        showValueZA = (TextView) findViewById(R.id.tvZValueA);
        showValueYA = (TextView) findViewById(R.id.tvYValueA);
        showValueXA = (TextView) findViewById(R.id.tvXValueA);

        SensorEventListener acceleromSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                xValA = sensorEvent.values[0];
                yValA = sensorEvent.values[1];
                zValA = sensorEvent.values[2];

                showValueXA.setText(df.format(xValA));
                showValueYA.setText(df.format(yValA));
                showValueZA.setText(df.format(zValA));

//                if((xValA <= 1) || (yValA <= 1) || (zValA <= 1)){
//                    zLL.setBackgroundColor(Color.parseColor("#1F18FF"));
//                    intensityLevel.setText("0");
//                    high = false;
//                }
//                if(((xValA > 1) && (xValA <=5)) || ((yValA > 1) && (yValA <=5)) || ((zValA > 1) && (zValA <=5))){
//                    zLL.setBackgroundColor(Color.parseColor("#3518FF"));
//                    intensityLevel.setTextColor(Color.parseColor("#FFFFFF"));
//                    intensityLevel.setText("1");
//                }
//                if(((xValA > 5) && (xValA <=9)) || ((yValA > 5) && (yValA <=9)) || ((zValA > 5) && (zValA <=9))){
//                    zLL.setBackgroundColor(Color.parseColor("#4E18E1"));
//                    intensityLevel.setTextColor(Color.parseColor("#FFFFFF"));
//                    intensityLevel.setText("2");
//                }
//                if(((xValA > 9) && (xValA <=13)) || ((yValA > 9) && (yValA <=13)) || ((zValA > 9) && (zValA <=13))){
//                    zLL.setBackgroundColor(Color.parseColor("#6318C4"));
//                    intensityLevel.setTextColor(Color.parseColor("#FFFFFF"));
//                    intensityLevel.setText("3");
//                }
//                if(((xValA > 13) && (xValA <=17)) || ((yValA > 13) && (yValA <=17)) || ((zValA > 13) && (zValA <=17))){
//                    zLL.setBackgroundColor(Color.parseColor("#7C18A4"));
//                    intensityLevel.setTextColor(Color.parseColor("#FFFFFF"));
//                    intensityLevel.setText("4");
//                }
//                if(((xValA > 17) && (xValA <=21)) || ((yValA > 17) && (yValA <=21)) || ((zValA > 17) && (zValA <=21))){
//                    zLL.setBackgroundColor(Color.parseColor("#981889"));
//                    intensityLevel.setTextColor(Color.parseColor("#FFFFFF"));
//                    intensityLevel.setText("5");
//                }
//                if(((xValA > 21) && (xValA <=25)) || ((yValA > 21) && (yValA <=25)) || ((zValA > 21) && (zValA <=25))){
//                    zLL.setBackgroundColor(Color.parseColor("#B8186D"));
//                    intensityLevel.setTextColor(Color.parseColor("#FFFFFF"));
//                    intensityLevel.setText("6");
//                }
//                if(((xValA > 25) && (xValA <=29)) || ((yValA > 25) && (yValA <=29)) || ((zValA > 25) && (zValA <=29))){
//                    zLL.setBackgroundColor(Color.parseColor("#D51855"));
//                    intensityLevel.setTextColor(Color.parseColor("#FFFFFF"));
//                    intensityLevel.setText("7");
//                }
//                if(((xValA > 29) && (xValA <=33)) || ((yValA > 29) && (yValA <=33)) || ((zValA > 29) && (zValA <=33))){
//                    zLL.setBackgroundColor(Color.parseColor("#F5183E"));
//                    intensityLevel.setTextColor(Color.parseColor("#FFFFFF"));
//                    intensityLevel.setText("8");
//                }
//                if(((xValA > 33) && (xValA <=37)) || ((yValA > 33) && (yValA <=37)) || ((zValA > 33) && (zValA <=37))){
//                    zLL.setBackgroundColor(Color.parseColor("#FF182A"));
//                    intensityLevel.setTextColor(Color.parseColor("#FFFFFF"));
//                    intensityLevel.setText("9");
//                }
//                if(((xValA>37)) || ((yValA>37)) || (zValA>37)){
//                    zLL.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    intensityLevel.setText("10");
//                    intensityLevel.setTextColor(Color.parseColor("#000000"));
//                }

                if((xValA < 9) || (yValA < 9) || (zValA < 9)){
                    zLL.setBackgroundColor(Color.parseColor("#1F18FF"));
                    intensityLevel.setText("0");
                    high = false;
                }
                if(((xValA > 10) && (xValA <=13)) || ((yValA > 10) && (yValA <=13)) || ((zValA > 10) && (zValA <=13))){
                    zLL.setBackgroundColor(Color.parseColor("#3518FF"));
                    intensityLevel.setTextColor(Color.parseColor("#FFFFFF"));
                    intensityLevel.setText("1");
                }
                if(((xValA > 13) && (xValA <=17)) || ((yValA > 13) && (yValA <=17)) || ((zValA > 13) && (zValA <=17))){
                    zLL.setBackgroundColor(Color.parseColor("#4E18E1"));
                    intensityLevel.setTextColor(Color.parseColor("#FFFFFF"));
                    intensityLevel.setText("2");
                }
                if(((xValA > 17) && (xValA <=21)) || ((yValA > 17) && (yValA <=21)) || ((zValA > 17) && (zValA <=21))){
                    zLL.setBackgroundColor(Color.parseColor("#6318C4"));
                    intensityLevel.setTextColor(Color.parseColor("#FFFFFF"));
                    intensityLevel.setText("3");
                }
                if(((xValA > 21) && (xValA <=25)) || ((yValA > 21) && (yValA <=25)) || ((zValA > 21) && (zValA <=25))){
                    zLL.setBackgroundColor(Color.parseColor("#7C18A4"));
                    intensityLevel.setTextColor(Color.parseColor("#FFFFFF"));
                    intensityLevel.setText("4");
                }
                if(((xValA > 25) && (xValA <=29)) || ((yValA > 25) && (yValA <=29)) || ((zValA > 25) && (zValA <=29))){
                    zLL.setBackgroundColor(Color.parseColor("#981889"));
                    intensityLevel.setTextColor(Color.parseColor("#FFFFFF"));
                    intensityLevel.setText("5");
                }
                if(((xValA > 33) && (xValA <=37)) || ((yValA > 33) && (yValA <=37)) || ((zValA > 33) && (zValA <=37))){
                    zLL.setBackgroundColor(Color.parseColor("#B8186D"));
                    intensityLevel.setTextColor(Color.parseColor("#FFFFFF"));
                    intensityLevel.setText("6");
                }
                if(((xValA > 41) && (xValA <=45)) || ((yValA > 41) && (yValA <=45)) || ((zValA > 41) && (zValA <=45))){
                    zLL.setBackgroundColor(Color.parseColor("#D51855"));
                    intensityLevel.setTextColor(Color.parseColor("#FFFFFF"));
                    intensityLevel.setText("7");
                }
                if(((xValA > 49) && (xValA <=53)) || ((yValA > 49) && (yValA <=53)) || ((zValA > 49) && (zValA <=53))){
                    zLL.setBackgroundColor(Color.parseColor("#F5183E"));
                    intensityLevel.setTextColor(Color.parseColor("#FFFFFF"));
                    intensityLevel.setText("8");
                }
                if(((xValA > 53) && (xValA <=57)) || ((yValA > 53) && (yValA <=57)) || ((zValA > 53) && (zValA <=57))){
                    zLL.setBackgroundColor(Color.parseColor("#FF182A"));
                    intensityLevel.setTextColor(Color.parseColor("#FFFFFF"));
                    intensityLevel.setText("9");
                }
                if(((xValA>57)) || ((yValA>57)) || (zValA>57)){
                    zLL.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    intensityLevel.setText("10");
                    intensityLevel.setTextColor(Color.parseColor("#000000"));
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(gyroscopeSensorListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(acceleromSensorListener, acceleromSensor, SensorManager.SENSOR_DELAY_NORMAL);

        btnCalc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                String str = "Calculating averages...";
                intensityLevel.setText(str);

                try{
//                    intensityLevel.setTextSize(90.00F);

                    int num = Integer.parseInt(etTimePer.getText().toString());
                    RunningStat[] stats = SensorUtils.sensorStats(MainActivity.this,
                            Sensor.TYPE_ACCELEROMETER, num);
                    double xMean = stats[0].mean();
                    double yMean = stats[1].mean();
                    double zMean = stats[2].mean();
//                    double xVar  = stats[0].variance();

                    double xMeanUns = 0;
                    double yMeanUns = 0;
                    double zMeanUns = 0;

                    if (xMean < 0){
                        xMeanUns = xMean * -1.0;
                    }
                    else{
                        xMeanUns = xMean;
                    }

                    if (yMean < 0){
                        yMeanUns = yMean * -1.0;
                    }
                    else{
                        yMeanUns = yMean;
                    }

                    if (zMean < 0){
                        zMeanUns = zMean * -1.0;
                    }
                    else{
                        zMeanUns = zMean;
                    }

                    tvDispAvgX.setText(df.format(xMeanUns));
                    tvDispAvgY.setText(df.format(yMeanUns));
                    tvDispAvgZ.setText(df.format(zMeanUns));
                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
}
