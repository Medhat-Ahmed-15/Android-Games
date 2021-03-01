package com.example.sensorgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Objects;
import java.util.Random;

public class DICE extends AppCompatActivity {

    private ImageView image_view_dice;
    private Random rng = new Random();

    private int lastdir;
    private boolean spinning;


    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_i_c_e);

        image_view_dice=(ImageView)findViewById(R.id.image_view_dice);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

    }



    private final SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            if (mAccel > 12) {
                rollDice();
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    @Override
    protected void onResume() {
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }
    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }


    private void rollDice() {
        int randomNumber = rng.nextInt(6) + 1;
        switch (randomNumber) {
            case 1:
                image_view_dice.setImageResource(R.drawable.dice1);
                break;
            case 2:
                image_view_dice.setImageResource(R.drawable.dice2);
                break;
            case 3:
                image_view_dice.setImageResource(R.drawable.dice3);
                break;
            case 4:
                image_view_dice.setImageResource(R.drawable.dice4);
                break;
            case 5:
                image_view_dice.setImageResource(R.drawable.dice5);
                break;
            case 6:
                image_view_dice.setImageResource(R.drawable.dice6);
                break;
        }
    }
}