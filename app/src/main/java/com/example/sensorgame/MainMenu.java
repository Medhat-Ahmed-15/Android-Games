package com.example.sensorgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class MainMenu extends AppCompatActivity {

    GifImageView dice;
    GifImageView bottle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        dice=(GifImageView) findViewById(R.id.dice);
        bottle=(GifImageView) findViewById(R.id.bottle);



        bottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openBottleActivity();

            }
        });


        dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDiceActivity();

            }
        });

    }

    public void openDiceActivity(){
        Intent intent=new Intent(this,DICE.class);
        startActivity(intent);
    }


    public void openBottleActivity(){
        Intent intent=new Intent(this,BOTTLE.class);
        startActivity(intent);
    }

}
