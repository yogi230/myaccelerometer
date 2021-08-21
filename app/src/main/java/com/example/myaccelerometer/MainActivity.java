package com.example.myaccelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.BatchUpdateException;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    private static final String LOG_TAG="MainActivity";
    private boolean reading=false;
    TextView xval,yval,zval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Log.v(LOG_TAG,"sensor manager initiated");
        xval=(TextView)findViewById(R.id.xVal);
        yval=(TextView)findViewById(R.id.yVal);
        zval=(TextView)findViewById(R.id.zVal);
        Button startReading=(Button)findViewById(R.id.startRead);
        Button stopReading=(Button)findViewById(R.id.stopRead);
        startReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reading==false) {
                    sensorManager.registerListener(MainActivity.this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
                    reading = true;
                }
            }
        });
        stopReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reading==true)
                {
                    sensorManager.unregisterListener(MainActivity.this);
                }
                reading=false;
            }
        });
    }
    protected void onResume() {
        super.onResume();
    }
    protected void onPause() {
        super.onPause();
        if(reading==true) {
            sensorManager.unregisterListener(this);
            reading=false;
        }
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.v(LOG_TAG,"Sensor Values X: "+event.values[0]+" Y: "+event.values[1]+" Z: "+event.values[2]);
        xval.setText("xValue: "+event.values[0]);
        yval.setText("yValue: "+event.values[1]);
        zval.setText("zValue: "+event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}