package com.erza.prizrencityguide.Events;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.erza.prizrencityguide.R;

import java.io.InputStream;
import java.util.ArrayList;

public class Sport_Events_Details extends AppCompatActivity {

    ArrayList<String> str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sport_events_layout_list_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        str =  getIntent().getStringArrayListExtra("lista");

        TextView lokacioni = (TextView) findViewById(R.id.tvLokacioniSport);
        lokacioni.setText(str.get(0).toString());

        new DownloadImageFromInternet((ImageView) findViewById(R.id.ivImazhiEkipit))
                .execute(str.get(1).toString());

        new DownloadImageFromInternet((ImageView) findViewById(R.id.ivImazhiKundershtarit))
                .execute(str.get(2).toString());

        new DownloadImageFromInternet((ImageView) findViewById(R.id.ivCover))
                .execute(str.get(3).toString());

        TextView pershkrimi = (TextView) findViewById(R.id.tvPershkrimiSport);
        pershkrimi.setText(str.get(4).toString());

        TextView ora = (TextView) findViewById(R.id.tvOraSport);
        ora.setText(str.get(5).toString());


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

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;

        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }



}
