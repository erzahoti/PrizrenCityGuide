package com.erza.prizrencityguide.Busses;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.amigold.fundapter.interfaces.DynamicImageLoader;
import com.bumptech.glide.Glide;
import com.erza.prizrencityguide.Busses.BussesDB;
import com.erza.prizrencityguide.MapsActivity;
import com.erza.prizrencityguide.R;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardListView;

public class Busses extends AppCompatActivity implements AsyncResponse {
    private ArrayList<BussesDB> productList;
    private ListView lvProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busses_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Glide.with(this).load(R.drawable.busses_map).into(imageView);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Busses.this, MapsActivity.class);
//                startActivity(i);
//            }
//        });
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

        int listImages[] = new int[]{R.drawable.bus_icon};
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i<5; i++) {
            // Create a Card
            Card card = new Card(this);
            // Create a CardHeader
            CardHeader header = new CardHeader(this);
            // Add Header to card
            header.setTitle("Linja "+(i+1));

            card.setTitle(productList.get(i).linja);
            card.addCardHeader(header);
            CardThumbnail thumb = new CardThumbnail(this);
            thumb.setDrawableResource(listImages[0]);
            card.addCardThumbnail(thumb);
            cards.add(card);
        }

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(this, cards);
        CardListView listView = (CardListView) this.findViewById(R.id.cardListView);
        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);

    }



}}