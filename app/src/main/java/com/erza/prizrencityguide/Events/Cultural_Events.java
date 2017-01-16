package com.erza.prizrencityguide.Events;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.amigold.fundapter.interfaces.ItemClickListener;
import com.erza.prizrencityguide.MapsActivity;
import com.erza.prizrencityguide.R;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;

/**
 * Created by Donika on 12/24/2016.
 */

public class Cultural_Events extends AppCompatActivity implements AsyncResponse {
    private ArrayList<Cultural_Events_DB> productList;
    private ListView lvProduct;
    public Button Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cultural_events_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Cultural_Events.this, MapsActivity.class);
                startActivity(i);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button = (Button) findViewById(R.id.button_id);





        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(Cultural_Events.this, this );
        taskRead.execute("http://www.regjisori.com/pcg/Events/cultural_events.php");
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
        productList = new JsonConverter<Cultural_Events_DB>().toArrayList(s, Cultural_Events_DB.class);
        BindDictionary<Cultural_Events_DB> dict = new BindDictionary<Cultural_Events_DB>();
        dict.addStringField(R.id.tvName, new StringExtractor<Cultural_Events_DB>() {
            @Override
            public String getStringValue(Cultural_Events_DB FoodDrinkDB, int i) {
                return FoodDrinkDB.emri;
            }
        });
        dict.addStringField(R.id.tvPershkrimi, new StringExtractor<Cultural_Events_DB>() {
            @Override
            public String getStringValue(Cultural_Events_DB FoodDrinkDB, int i) {
                return "" + FoodDrinkDB.pershkrimi;
            }
        });
        dict.addStringField(R.id.tvLokacioni, new StringExtractor<Cultural_Events_DB>() {
            @Override
            public String getStringValue(Cultural_Events_DB FoodDrinkDB, int i) {
                return "" + FoodDrinkDB.lokacioni;
            }
        });
        dict.addStringField(R.id.tvLloji, new StringExtractor<Cultural_Events_DB>() {
            @Override
            public String getStringValue(Cultural_Events_DB FoodDrinkDB, int i) {
                return "" + FoodDrinkDB.ora;
            }
        });
        dict.addStringField(R.id.button_id, new StringExtractor<Cultural_Events_DB>() {
            @Override
            public String getStringValue(Cultural_Events_DB FoodDrinkDB, int i) {
                return "";
            }
        }).onClick(new ItemClickListener<Cultural_Events_DB>() {

            @Override
            public void onClick(Cultural_Events_DB food_drink_sdb, int position, View view) {
                String url =food_drink_sdb.website;
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(in);

            }
        });;




        FunDapter<Cultural_Events_DB> adapter = new FunDapter<>(Cultural_Events.this, productList, R.layout.cultural_events_layout_list,dict);
        lvProduct = (ListView)findViewById(R.id.lvProduct);
        lvProduct.setAdapter(adapter);

    }

    public void sendToWeb(View view){



    }


}
