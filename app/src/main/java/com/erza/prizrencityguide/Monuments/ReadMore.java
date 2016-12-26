package com.erza.prizrencityguide.Monuments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.erza.prizrencityguide.R;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

public class ReadMore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monuments_read_more);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
