package com.erza.prizrencityguide;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by thaqibsm on 1/14/2017.
 */

public class PreferencesFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}
