package com.androidwithshiv.compass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CompassActivity extends AppCompatActivity implements SensorEventListener {
    private Context context;
    private ImageView compass;
    private TextView degreeText;
    private SensorManager sensorManager;
    private void init(){
        context = CompassActivity.this;
        compass = findViewById(R.id.compass);
        degreeText = findViewById(R.id.degree_value);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        init();

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int compassDegree = Math.round(sensorEvent.values[0]);
        degreeText.setText(compassDegree+"°");
        compass.setRotation(-compassDegree);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s: sensorList){
            Log.d("Sensor", s.toString());
        }

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}