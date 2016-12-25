package com.erza.prizrencityguide.Entertainment;

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
import com.erza.prizrencityguide.MapsActivity;
import com.erza.prizrencityguide.R;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Entertainment extends AppCompatActivity implements AsyncResponse {
    private ArrayList<entertainmentDB> entertainmentList;
    private ListView lvEntertainment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entertainment_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Entertainment.this, MapsActivity.class);
                startActivity(i);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(Entertainment.this, this );
        taskRead.execute("http://www.regjisori.com/pcg/Entertainment/Entertainment.php");
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
        entertainmentList = new JsonConverter<entertainmentDB>().toArrayList(s, entertainmentDB.class);
        BindDictionary<entertainmentDB> dict = new BindDictionary<entertainmentDB>();
        dict.addStringField(R.id.tvEmriEntertainment, new StringExtractor<entertainmentDB>() {
            @Override
            public String getStringValue(entertainmentDB entertainmentDB, int i) {
                return entertainmentDB.emri;
            }
        });
        dict.addStringField(R.id.tvLlojiEntertainment, new StringExtractor<entertainmentDB>() {
            @Override
            public String getStringValue(entertainmentDB entertainmentDB, int i) {
                return "" + entertainmentDB.lloji;
            }
        });
        dict.addStringField(R.id.tvLokacioniEntertainment, new StringExtractor<entertainmentDB>() {
            @Override
            public String getStringValue(entertainmentDB entertainmentDB, int i) {
                return "" + entertainmentDB.lokacioni;
            }
        });
        dict.addStringField(R.id.tvPershkrimiEntertainment, new StringExtractor<entertainmentDB>() {
            @Override
            public String getStringValue(entertainmentDB entertainmentDB, int i) {
                return "" + entertainmentDB.pershkrimi;
            }
        });
        dict.addDynamicImageField(R.id.ivImazhiEntertainment,
                new StringExtractor<entertainmentDB>() {
            @Override
            public String getStringValue(entertainmentDB entertainmentDB, int i) {
                return entertainmentDB.imazhi_link;
            }
        }, new DynamicImageLoader() {
            @Override
            public void loadImage(String link, ImageView imageView) {
                Picasso.with(Entertainment.this).load(link).into(imageView);
            }
        });


        FunDapter<entertainmentDB> adapter = new FunDapter<>(Entertainment.this, entertainmentList, R.layout.entertainment_layout_list,dict);
        lvEntertainment = (ListView)findViewById(R.id.lvEntertainment);
        lvEntertainment.setAdapter(adapter);

    }



}
