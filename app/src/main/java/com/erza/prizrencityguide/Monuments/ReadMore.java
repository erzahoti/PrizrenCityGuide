package com.erza.prizrencityguide.Monuments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.erza.prizrencityguide.R;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

public class ReadMore extends AppCompatActivity  {
    ArrayList<String> str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monuments_read_more);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        str =  getIntent().getStringArrayListExtra("lista");
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("listaa", str);

        ReadMoreFragment readMoreFragment = new ReadMoreFragment();
        readMoreFragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_readmore, readMoreFragment, readMoreFragment.getTag()).commit();

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
