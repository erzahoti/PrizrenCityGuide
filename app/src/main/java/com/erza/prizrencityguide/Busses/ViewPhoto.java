package com.erza.prizrencityguide.Busses;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.erza.prizrencityguide.R;
import com.squareup.picasso.Picasso;

public class ViewPhoto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);
        Intent i = getIntent();
        ImageView img = (ImageView) findViewById(R.id.bustimetable);
        Picasso.with(getApplicationContext()).load(i.getStringExtra("timetable")).into(img);
    }
}
