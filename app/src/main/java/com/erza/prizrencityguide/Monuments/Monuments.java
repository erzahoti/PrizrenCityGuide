package com.erza.prizrencityguide.Monuments;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.amigold.fundapter.interfaces.DynamicImageLoader;
import com.amigold.fundapter.interfaces.ItemClickListener;
import com.erza.prizrencityguide.R;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Monuments extends AppCompatActivity implements AsyncResponse {

    private ArrayList<monumentsdb> productList;
    private ListView monuments;

    public static final String GET_ID = "item_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monuments_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(Monuments.this, this );
        taskRead.execute("http://www.regjisori.com/pcg/Monuments/monumentet.php");
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
        productList = new JsonConverter<monumentsdb>().toArrayList(s, monumentsdb.class);
        BindDictionary<monumentsdb> dict = new BindDictionary<monumentsdb>();

        dict.addStringField(R.id.emri_monument, new StringExtractor<monumentsdb>() {
            @Override
            public String getStringValue(monumentsdb monumentsdb, int i) {
                return monumentsdb.emri;
            }
        });


        dict.addStringField(R.id.lokacioni_monument, new StringExtractor<monumentsdb>() {
            @Override
            public String getStringValue(monumentsdb monumentsdb, int i) {
                return "Name of street: " + monumentsdb.lokacioni;
            }
        });
        dict.addDynamicImageField(R.id.imazhi_monument,
                new StringExtractor<monumentsdb>() {
                    @Override
                    public String getStringValue(monumentsdb monumentsdb, int i) {
                        return monumentsdb.imazhi_link;
                    }
                }, new DynamicImageLoader() {
                    @Override
                    public void loadImage(String link, ImageView imageView) {
                        Picasso.with(Monuments.this).load(link).into(imageView);
                    }
                });

        dict.addStringField(R.id.readmore_button, new StringExtractor<monumentsdb>() {

            @Override
            public String getStringValue(monumentsdb monumentsdb, int i) {
                return monumentsdb.read_more;

            }
        }).onClick(new ItemClickListener<monumentsdb>() {

            @Override
            public void onClick(monumentsdb monumentsdb, int position, View view) {
                Intent i = new Intent(Monuments.this,ReadMore.class);
                startActivity(i);
            }
        });


        FunDapter<monumentsdb> adapter = new FunDapter<>(Monuments.this, productList, R.layout.monuments_layout_list,dict);
        monuments = (ListView)findViewById(R.id.Product_monuments);
        monuments.setAdapter(adapter);


    }

}
