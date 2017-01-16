package com.erza.prizrencityguide;

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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardGridArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardGridView;

public class PracticalInformation extends AppCompatActivity implements AsyncResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_information);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imageView = (ImageView) findViewById(R.id.emergency_numbers);
        Glide.with(this).load(R.drawable.emergency_numbers).into(imageView);

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
        sendData("ALL", "TRY", "1");

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

            stringArray.add(from);
            stringArray.add(to);
            stringArray.add(amount);
        }

        return stringArray;
    }

    @Override
    public void processFinish(String s) {

        try{
            ArrayList<String> a = jsonStringToArray(s);
//            Button showDialog = (Button) findViewById(R.id.showdialog);
//            final  TextView userinputtext = (TextView) findViewById(R.id.userinputtext);
//            showDialog.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    View view = (LayoutInflater.from(PracticalInformation.this)).inflate(R.layout.user_input, null);
//
//                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(PracticalInformation.this);
//                    alertBuilder.setView(view);
//                    final EditText userInput = (EditText) view.findViewById(R.id.userinput);
//
//                    alertBuilder.setCancelable(true)
//                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    userinputtext.setText(userInput.getText());
//
//                                }
//                            });
//                    Dialog dialog = alertBuilder.create();
//                    dialog.show();
//                }
//            });
        }catch (JSONException e){

        }


    }


}
