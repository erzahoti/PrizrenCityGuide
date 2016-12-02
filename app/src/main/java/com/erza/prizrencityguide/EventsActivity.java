package com.erza.prizrencityguide;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by thaqibsm on 11/30/2016.
 */

public class EventsActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ///getLayoutInflater().inflate(R.layout.activity_events, mFrameLayout);
        Toast.makeText(this, "Events Activity", Toast.LENGTH_SHORT).show();
    }

    }
