package com.erza.prizrencityguide.PracticalInformation;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.erza.prizrencityguide.PracticalInformation.Weather.WeatherActivity;
import com.erza.prizrencityguide.R;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardGridArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardGridView;

public class PracticalInformation extends AppCompatActivity implements AsyncResponse {

    static String jsonString;
    static TextView userinputtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_information);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imageView = (ImageView) findViewById(R.id.emergency_numbers);
        Glide.with(this).load(R.drawable.emergency_numbers).into(imageView);

        ImageView weather_button = (ImageView) findViewById(R.id.weatheractivity_button);
        Glide.with(this).load(R.drawable.weather_banner).into(weather_button);
        weather_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PracticalInformation.this, WeatherActivity.class);
                startActivity(i);
            }
        });

        ArrayList<Card> cards = new ArrayList<Card>();
        int listImages[] = new int[]{R.drawable.sos_icon, R.drawable.police_icon, R.drawable.firefighter_icon, R.drawable.ambulance_icon};
        final String[][] emergencynumbers = {{"SOS","122"},{"Police","192"},{"Fire","193"},{"Ambulance","194"}};

        for (int i=0; i<emergencynumbers.length; i++){

            Card card = new Card(this);

            CardHeader header = new CardHeader(this);
            header.setTitle(emergencynumbers[i][0]+" - "+emergencynumbers[i][1]);
            card.addCardHeader(header);

            CardThumbnail thumbnail = new CardThumbnail(this);
            thumbnail.setDrawableResource(listImages[i]);
            card.addCardThumbnail(thumbnail);

            card.setTitle("Click to\ncall");
            card.setClickable(true);
            final String number = emergencynumbers[i][1];
            card.setOnClickListener(new Card.OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+number));
                    startActivity(intent);
                }
            });
            cards.add(card);

        }
        CardGridArrayAdapter mCardArrayAdapter = new CardGridArrayAdapter(this,cards);

        CardGridView gridView = (CardGridView) this.findViewById(R.id.myGrid);
        if (gridView!=null){
            gridView.setAdapter(mCardArrayAdapter);
        }

        ImageView imageView2 = (ImageView) findViewById(R.id.currency_converter_banner);
        Glide.with(this).load("http://regjisori.com/pcg/practical_information/currency_converter_banner.png").into(imageView2);

        sendData("USD", "EUR", "1");
        userinputtext = (TextView) findViewById(R.id.currency_show);

        Button showDialog = (Button) findViewById(R.id.currency_button);

        showDialog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                View view = (LayoutInflater.from(PracticalInformation.this)).inflate(R.layout.currency_input, null);

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(PracticalInformation.this);
                alertBuilder.setView(view);
                final Spinner spinner_from = (Spinner) view.findViewById(R.id.spinner_from_currency);
                final Spinner spinner_to = (Spinner) view.findViewById(R.id.spinner_to_currency);
                final EditText amount2 = (EditText) view.findViewById(R.id.currency_amount);

                alertBuilder.setCancelable(true)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendData(spinner_from.getSelectedItem().toString(), spinner_to.getSelectedItem().toString(), amount2.getText().toString());

                            }
                        });
                Dialog dialog = alertBuilder.create();
                dialog.show();
            }
        });


    }

    public void sendData(String from, String to, String amount){
        String url = "http://regjisori.com/pcg/practical_information/currencyconverter.php?from="+from+"&to="+to+"&amount="+amount;
        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(PracticalInformation.this, this);
        taskRead.execute(url);
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

    ArrayList<String> jsonStringToArray(String jsonString) throws JSONException {

        ArrayList<String> stringArray = new ArrayList<String>();

        JSONArray jsonArray = new JSONArray(jsonString);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json_obj = jsonArray.getJSONObject(i);

            String from = json_obj.getString("from");
            String to = json_obj.getString("to");
            String amount = json_obj.getString("amount");
            String sendamount = json_obj.getString("sendamount");

            stringArray.add(sendamount);
            stringArray.add(from);
            stringArray.add(amount);
            stringArray.add(to);


        }

        return stringArray;
    }

    @Override
    public void processFinish(String s) {


        try{
            ArrayList<String> a = jsonStringToArray(s);
            if(a.get(1).equals(a.get(3))){
                userinputtext.setText("Same currency inputs.");
            }else {
                String temp = String.format("%s %s = %s %s", a.get(0), a.get(1), a.get(2), a.get(3));
                userinputtext.setText(temp);
            }
        }catch (JSONException e){

        }


    }



}
