package com.erza.prizrencityguide.Events;

import android.content.Intent;
import android.os.Bundle;
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
import com.amigold.fundapter.interfaces.ItemClickListener;
import com.erza.prizrencityguide.R;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Sport_Events extends AppCompatActivity implements AsyncResponse {

    private ArrayList<sporteventDB> sporteventList;
    private ListView sports;
    public static final String GET_ID = "item_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sport_events_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(Sport_Events.this, this );
        taskRead.execute("http://regjisori.com/pcg/Events/sport_events.php");
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
        sporteventList = new JsonConverter<sporteventDB>().toArrayList(s, sporteventDB.class);


        BindDictionary<sporteventDB> dict = new BindDictionary<sporteventDB>();

        dict.addStringField(R.id.tvEmriSport, new StringExtractor<sporteventDB>() {
            @Override
            public String getStringValue(sporteventDB sporteventDB, int i) {
                return sporteventDB.emri;
            }
        });
        dict.addStringField(R.id.tvKundershtari, new StringExtractor<sporteventDB>() {
            @Override
            public String getStringValue(sporteventDB sporteventDB, int i) {
                return sporteventDB.kundershtari;
            }
        });

        dict.addDynamicImageField(R.id.ivImazhiEkipit,
                new StringExtractor<sporteventDB>() {
                    @Override
                    public String getStringValue(sporteventDB sporteventDB, int i) {
                        return "" + sporteventDB.imazhiEkipit;
                    }
                }, new DynamicImageLoader() {
                    @Override
                    public void loadImage(String link, ImageView imageView) {
                        Picasso.with(Sport_Events.this).load(link).into(imageView);
                    }
                });

        dict.addDynamicImageField(R.id.ivKundershtari,
                new StringExtractor<sporteventDB>() {
                    @Override
                    public String getStringValue(sporteventDB sporteventDB, int i) {
                        return "" + sporteventDB.imazhiKundershtarit;
                    }
                }, new DynamicImageLoader() {
                    @Override
                    public void loadImage(String link, ImageView imageView) {
                        Picasso.with(Sport_Events.this).load(link).into(imageView);
                    }
                });


        dict.addStringField(R.id.tvKlubiDetails, new StringExtractor<sporteventDB>() {

            @Override
            public String getStringValue(sporteventDB sporteventDB, int i) {
                return sporteventDB.info;

            }
        }).onClick(new ItemClickListener<sporteventDB>() {

            @Override
            public void onClick(sporteventDB sporteventDB, int position, View view) {

                Intent i = new Intent(Sport_Events.this,Sport_Events_Details.class);
                String  rez="";
                int pos = position;
                ArrayList<String> listdata = new ArrayList<String>();
                rez =rez+ sporteventDB.id;
                //int len = productList.size();
                // int pos1 = len -position+4;
                if (sporteventList != null) {

                    listdata.add(sporteventList.get(pos).lokacioni);
                    listdata.add(sporteventList.get(pos).imazhiEkipit);
                    listdata.add(sporteventList.get(pos).imazhiKundershtarit);
                    listdata.add(sporteventList.get(pos).cover);
                    listdata.add(sporteventList.get(pos).pershkrimi);
                    listdata.add(sporteventList.get(pos).ora);




                }
                i.putExtra("lista",listdata);
                startActivity(i);
            }
        });


        FunDapter<sporteventDB> adapter = new FunDapter<>(Sport_Events.this, sporteventList, R.layout.sport_events_layout_list,dict);
        sports = (ListView)findViewById(R.id.lvSportEvents);
        sports.setAdapter(adapter);


    }

}
