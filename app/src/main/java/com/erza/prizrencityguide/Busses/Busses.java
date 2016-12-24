package com.erza.prizrencityguide.Busses;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.amigold.fundapter.interfaces.DynamicImageLoader;
import com.erza.prizrencityguide.Busses.BussesDB;
import com.erza.prizrencityguide.MapsActivity;
import com.erza.prizrencityguide.R;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Busses extends AppCompatActivity implements AsyncResponse {
    private ArrayList<BussesDB> productList;
    private ListView lvProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busses_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Busses.this, MapsActivity.class);
                startActivity(i);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(Busses.this, this);
        taskRead.execute("http://www.regjisori.com/pcg/Busses/busses.php");
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

    @Override
    public void processFinish(String s) {
        productList = new JsonConverter<BussesDB>().toArrayList(s, BussesDB.class);
        BindDictionary<BussesDB> dict = new BindDictionary<BussesDB>();
        dict.addStringField(R.id.tvLinja, new StringExtractor<BussesDB>() {
            @Override
            public String getStringValue(BussesDB BussesDB, int i) {
                return BussesDB.linja;
            }
        });

        dict.addDynamicImageField(R.id.tvOrari,
                new StringExtractor<BussesDB>() {
                    @Override
                    public String getStringValue(BussesDB BussesDB, int i) {
                        return BussesDB.orari;
                    }
                }, new DynamicImageLoader() {
                    @Override
                    public void loadImage(String link, ImageView imageView) {
                        Picasso.with(Busses.this).load(link).into(imageView);
                    }
                });


        FunDapter<BussesDB> adapter = new FunDapter<>(Busses.this, productList, R.layout.busses_layout_list,dict);
        lvProduct = (ListView)findViewById(R.id.lvProduct);
        lvProduct.setAdapter(adapter);

    }



}