package com.erza.prizrencityguide.Accommodation;

/**
 * Created by thaqibsm on 12/24/2016.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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


public class Accommodation extends AppCompatActivity implements AsyncResponse {
    private ArrayList<AccommodationDB> productList;
    private ListView lvProduct;
    public Button webButton;

    static final String TAG = "Accommodation";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accommodation_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(com.erza.prizrencityguide.Accommodation.Accommodation.this, MapsActivity.class);
                startActivity(i);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(com.erza.prizrencityguide.Accommodation.Accommodation.this, this);
        taskRead.execute("http://www.regjisori.com/pcg/Accommodation/accommodation.php");
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

        productList = new JsonConverter<AccommodationDB>().toArrayList(s, AccommodationDB.class);
        final BindDictionary<AccommodationDB> dict = new BindDictionary<AccommodationDB>();

        dict.addStringField(R.id.tvName, new StringExtractor<AccommodationDB>() {
            @Override
            public String getStringValue(AccommodationDB AccommodationDB, int i) {
                return AccommodationDB.emri;
            }
        });
        dict.addStringField(R.id.tvPershkrimi, new StringExtractor<AccommodationDB>() {
            @Override
            public String getStringValue(AccommodationDB AccommodationDB, int i) {
                return "" + AccommodationDB.pershkrimi;
            }
        });
        dict.addStringField(R.id.tvLokacioni, new StringExtractor<AccommodationDB>() {
            @Override
            public String getStringValue(AccommodationDB AccommodationDB, int i) {
                return "Location: " + AccommodationDB.lokacioni;
            }
        });
        dict.addStringField(R.id.tvCmimi, new StringExtractor<AccommodationDB>() {
            @Override
            public String getStringValue(AccommodationDB AccommodationDB, int i) {
                return "Average price per night: " + AccommodationDB.cmimi + " €";
            }
        });

        dict.addDynamicImageField(R.id.ivImazhi,
                new StringExtractor<AccommodationDB>() {
                    @Override
                    public String getStringValue(AccommodationDB AccommodationDB, int i) {
                        return AccommodationDB.imazhi_link;
                    }
                }, new DynamicImageLoader() {
                    @Override
                    public void loadImage(String link, ImageView imageView) {
                        Picasso.with(com.erza.prizrencityguide.Accommodation.Accommodation.this).load(link).into(imageView);
                    }
                });


        FunDapter<AccommodationDB> adapter = new FunDapter<>(com.erza.prizrencityguide.Accommodation.Accommodation.this, productList, R.layout.accommodation_layout_list,dict);
        lvProduct = (ListView)findViewById(R.id.lvProduct);
        lvProduct.setAdapter(adapter);

        //webButton = (Button) findViewById(R.id.accommodation_website_button);
        Log.d(TAG,"webButton 0 " + webButton);
    }

    public void sendToWebsite(View view) {
        Log.d(TAG,"sendToWebsite");
        String url = "https://www.google.com/";
        Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        //i.setData(Uri.parse(url));
        startActivity(i);
    }

}