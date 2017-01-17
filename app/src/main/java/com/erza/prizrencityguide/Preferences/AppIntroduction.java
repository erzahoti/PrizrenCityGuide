package com.erza.prizrencityguide.Preferences;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.erza.prizrencityguide.R;

/**
 * Created by thaqibsm on 1/17/2017.
 */

public class AppIntroduction extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //PreferencesActivity.this.setTheme(R.style.CustomTheme)

        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction_settings);
    }
}