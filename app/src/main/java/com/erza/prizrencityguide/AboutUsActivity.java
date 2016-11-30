package com.erza.prizrencityguide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by thaqibsm on 11/30/2016.
 */

public class AboutUsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "About Us Activity", Toast.LENGTH_SHORT).show();
    }
}
