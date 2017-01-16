package com.erza.prizrencityguide.Busses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.erza.prizrencityguide.R;
import com.google.android.gms.vision.text.Text;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Routes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busses_routes);
        Intent intent = getIntent();
        final ArrayList<String> shtesa = intent.getStringArrayListExtra("shtesa");

        ImageView img = (ImageView) findViewById(R.id.busclipart);
        Glide.with(this).load(R.drawable.busses_clipart).into(img);

        ImageView img2 = (ImageView) findViewById(R.id.bustimetablepreview);
        Glide.with(this).load(shtesa.get(1)).into(img2);
        img2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Routes.this, ViewPhoto.class);
                i.putExtra("timetable", shtesa.get(1));
                startActivity(i);
            }
        });


        // Ketu do te vendosen te dhenat mbi linjat...

        // Ketu eshte marre "extra" e derguar nga intenta nga Busses
        TextView route = (TextView) findViewById(R.id.route_id);
        route.setText(shtesa.get(0));


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
