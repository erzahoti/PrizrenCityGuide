package com.erza.prizrencityguide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by thaqibsm on 11/30/2016.
 */

public class MonumentsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Monuments Activity", Toast.LENGTH_SHORT).show();
    }
}