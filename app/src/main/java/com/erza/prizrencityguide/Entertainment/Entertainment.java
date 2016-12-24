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
    /*final  String LOG = "ListActivity";*/

    private ArrayList<entertainmentDB> productList;
    private ListView lvProduct;
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
        productList = new JsonConverter<entertainmentDB>().toArrayList(s, entertainmentDB.class);
        BindDictionary<entertainmentDB> dict = new BindDictionary<entertainmentDB>();
        dict.addStringField(R.id.tvName, new StringExtractor<entertainmentDB>() {
            @Override
            public String getStringValue(entertainmentDB entertainmentDB, int i) {
                return entertainmentDB.emri;
            }
        });
        dict.addStringField(R.id.tvPershkrimi, new StringExtractor<entertainmentDB>() {
            @Override
            public String getStringValue(entertainmentDB entertainmentDB, int i) {
                return "" + entertainmentDB.pershkrimi;
            }
        });
        dict.addStringField(R.id.tvWebsite, new StringExtractor<entertainmentDB>() {
            @Override
            public String getStringValue(entertainmentDB entertainmentDB, int i) {
                return "" + entertainmentDB.website;
            }
        });
        dict.addDynamicImageField(R.id.ivImazhi,
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


        FunDapter<entertainmentDB> adapter = new FunDapter<>(Entertainment.this, productList, R.layout.entertainment_layout_list,dict);
        lvProduct = (ListView)findViewById(R.id.lvProduct);
        lvProduct.setAdapter(adapter);

    }



}
