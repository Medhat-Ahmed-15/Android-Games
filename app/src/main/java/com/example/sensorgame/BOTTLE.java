package com.example.sensorgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;
import java.util.Random;

public class BOTTLE extends AppCompatActivity {
    ImageView imageView;
    Animation rotateAnimation;

    EditText editTextTextPersonName;

    private Random random=new Random();

    private int lastdir;
    private boolean spinning;


    int i;

    String[] questions;

    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_o_t_t_l_e);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;


        imageView=(ImageView)findViewById(R.id.imageView);
        editTextTextPersonName=(EditText) findViewById(R.id.editTextTextPersonName);




        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rotateAnimation();

            }
        });
    }

    //SHAKE

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
                rotateAnimation();
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

    //SHAKE



    private void rotateAnimation() {

        if(!spinning) {

            int newDir = random.nextInt(1800);//generates a random number between 0 and 1800,,which is the point that that starts animation  from it
            float pivotX = imageView.getWidth() / 2;//will get the middle point of the bottle to rotate around x-axis
            float pivotY = imageView.getHeight() / 2;//will get the middle point of the bottle to rotate around y-axis

            Animation rotae = new RotateAnimation(lastdir, newDir, pivotX, pivotY);//takes(starting direction which is=0,new direction,width/2,height/2)

            rotae.setDuration(5000);//bat rotate fa 2d eih
            rotae.setFillAfter(true);//this means that you want to keep the image view as it is after the animation,we don't want to reset it to it's initial direction

            rotae.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning=true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning=false;
                    i=Integer.parseInt(editTextTextPersonName.getText().toString());

                    questions = new String[i];
                    for(int j=0;j<questions.length;j++)
                    {
                        questions[j] = "USER" + " "+ j;

                    }
                    String users=questions[random.nextInt(i)];
                    Toast.makeText(BOTTLE.this,users,Toast.LENGTH_LONG).show();

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            lastdir = newDir;
            imageView.startAnimation(rotae);

        }

    }
}