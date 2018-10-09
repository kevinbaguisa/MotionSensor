package com.kevinbaguisa.gyroscope;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GyroscopeActivity extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor gyroscopeSensor;
    TextView showValueZ, showValueY, showValueX;
    float xValG, yValG, zValG, xValUnsG, yValUnsG, zValUnsG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
    }
}
