package com.erza.prizrencityguide.Busses;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.erza.prizrencityguide.R;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.lang.reflect.Array;
import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.internal.ViewToClickToExpand;
import it.gmariotti.cardslib.library.prototypes.CardSection;
import it.gmariotti.cardslib.library.view.CardListView;

public class Busses extends AppCompatActivity implements AsyncResponse {

    private ArrayList<BussesDB> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busses_activity);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView imageView = (ImageView) findViewById(R.id.busses_map);

        Glide.with(this).load(R.drawable.busses_map).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Busses.this, BussesMap.class);
                startActivity(i);
            }
        });

        if(!isNetworkAvailable()){
            TextView txv = (TextView) findViewById(R.id.no_net_busses);
            txv.setText("No internet connection. The map and the route list won't show up.");
        }

//        Ky kod eshte lene per cdo rast te riperdoret me vone

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Busses.this, MapsActivity.class);
//                startActivity(i);
//            }
//        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Ketu behet lidhja e mundshme me hostin per t'i marre te dhenat nga PHP skedari
        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(Busses.this, this);
        taskRead.execute("http://www.regjisori.com/pcg/Busses/busses.php");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Butoni per dergimin mbrapa
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return  true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }


    @Override
    public void processFinish(String s) {

        // Te dhenat merren nga JSON-i dhe vendosen ne nje liste
        productList = new JsonConverter<BussesDB>().toArrayList(s, BussesDB.class);

        // Pyesim se a eshte lista productList null a jo. Kete e bejme per shkak
        // qe ne rastin e mosqasjes  ne internet ne aplikacion, te mos te na dale error
        if (productList != null) {

            // Imazhet te cilat mund te vendosen ne karta
            int listImages[] = new int[]{R.drawable.bus_icon};

            // Krijimi i nje liste te kartes
            ArrayList<Card> cards = new ArrayList<Card>();

            // Per cdo element ne listen e produkteve krijohet nje karte
            for (int i = 0; i < productList.size(); i++) {

                // Krijimi i kartes
                Card card = new Card(this);

                // Krijimi i nje titulli per karte
                CardHeader header = new CardHeader(this);
                header.setTitle(productList.get(i).linja);
                card.addCardHeader(header);

                // Shtimi i nje permbajtjes ne karte
                String content = "\nRoute " + (i + 1) + "\nBus Fare: 0.50 €";
                card.setTitle(content);

                // Krijimi i nje thumbnail-i per karte
                CardThumbnail thumb = new CardThumbnail(this);
                thumb.setDrawableResource(listImages[0]);
                card.addCardThumbnail(thumb);

                // Vendosja e kartes ne vargun e kartave
                cards.add(card);

                // Mundesia e kartes per t'u klikuar
                card.setClickable(true);
                final ArrayList<String> shtesa = new ArrayList<String>();
                shtesa.add(0, productList.get(i).linja);
                shtesa.add(1, productList.get(i).orari);
                shtesa.add(2, productList.get(i).koment_shtese);


                /* Vendosja e nje listener-i qe kur te shtypet karta t'i dergoje shfrytezuesit
                ne nje aktivitet tjeter */
                card.setOnClickListener(new Card.OnCardClickListener() {
                    @Override
                    public void onClick(Card card, View view) {

                        // Krijimi i intentes per ta derguar shfryetzuesin ne aktivitetin Routes
                        Intent intent = new Intent(Busses.this, Routes.class);
                        intent.putStringArrayListExtra("shtesa", shtesa);
                        startActivity(intent);
                    }
                });

            }

            // Ketu permblidhen kartat dhe vendosen ne nje varg te adapaterit
            CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(this, cards);

            //Krijohet nje CardListView e cila do t'i permbaje keto karta
            CardListView listView = (CardListView) this.findViewById(R.id.cardListView);

            //nese vargu nuk eshte i zbrazet keto vendosen ne View
            if (listView != null) {

                // Vendosen te dhenat ne adapater
                listView.setAdapter(mCardArrayAdapter);
            }

        }



}}