package com.erza.prizrencityguide.Preferences;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.erza.prizrencityguide.Accommodation.Accommodation;
import com.erza.prizrencityguide.Busses.Busses;
import com.erza.prizrencityguide.Entertainment.Entertainment;
import com.erza.prizrencityguide.FoodDrink.Food_Drink;
import com.erza.prizrencityguide.MainActivity;
import com.erza.prizrencityguide.Monuments.Monuments;
import com.erza.prizrencityguide.R;

/**
 * Created by thaqibsm on 1/17/2017.
 */

public class AppIntroduction extends AppCompatActivity {

    FloatingActionButton flMonuments;
    FloatingActionButton flFoodDrink;
    FloatingActionButton flAccommodation;
    FloatingActionButton flEntertainment;
    FloatingActionButton flBusses;
    ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        flMonuments = (FloatingActionButton) findViewById(R.id.fl_monuments);
        flMonuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AppIntroduction.this, Monuments.class);
                startActivity(i);
            }
        });

        flFoodDrink = (FloatingActionButton) findViewById(R.id.fl_fooddrink);
        flFoodDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AppIntroduction.this, Food_Drink.class);
                startActivity(i);
            }
        });

        flAccommodation = (FloatingActionButton) findViewById(R.id.fl_accommodation);
        flAccommodation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AppIntroduction.this, Accommodation.class);
                startActivity(i);
            }
        });

        flEntertainment = (FloatingActionButton) findViewById(R.id.fl_entertainment);
        flEntertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AppIntroduction.this, Entertainment.class);
                startActivity(i);
            }
        });

        flBusses = (FloatingActionButton) findViewById(R.id.fl_busses);
        flBusses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AppIntroduction.this, Busses.class);
                startActivity(i);
            }
        });

        imgLogo = (ImageView) findViewById(R.id.img_Logo);
        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AppIntroduction.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return  true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}