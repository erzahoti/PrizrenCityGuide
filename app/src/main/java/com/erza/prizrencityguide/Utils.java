package com.erza.prizrencityguide;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by thaqibsm on 1/16/2017.
 */

public class Utils {
    private static int sTheme;
    public final static int THEME_DEFAULT = 0;
    public final static int THEME_CUSTOM = 1;

    public static void changeToTheme(Activity activity, int theme)
    {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    public static void onActivityCreateSetTheme(Activity activity)
    {
        switch (sTheme)
        {
            default:
            case THEME_DEFAULT:
                activity.setTheme(R.style.AppTheme);
                break;
            case THEME_CUSTOM:
                activity.setTheme(R.style.CustomTheme);
                break;
        }
    }
}
