package com.erza.prizrencityguide;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.LinearLayout;


/**
 * Created by thaqibsm on 11/30/2016.
 */

public class FoodDrinkActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   Toast.makeText(this, "Food and Drink Activity", Toast.LENGTH_SHORT).show();
    }
}
