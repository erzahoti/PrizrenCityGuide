package com.erza.prizrencityguide.Busses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.erza.prizrencityguide.R;
import com.google.android.gms.vision.text.Text;

public class Routes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busses_routes);
        Intent intent = getIntent();
        String routestr = intent.getStringExtra("route");

        // Ketu do te vendosen te dhenat mbi linjat...


        // Ketu eshte marre "extra" e derguar nga intenta nga Busses
        TextView route = (TextView) findViewById(R.id.route_id);
        route.setText(routestr);

        // Vendoset ActionBar
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
