package com.erza.prizrencityguide.Monuments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.erza.prizrencityguide.R;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

public class ReadMore extends AppCompatActivity {
    ArrayList<String> str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monuments_read_more);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


       str =  getIntent().getStringArrayListExtra("lista");

        TextView emri = (TextView) findViewById(R.id.emri_monument_rm);
        emri.setText(str.get(0).toString());

        TextView lo = (TextView) findViewById(R.id.lokacioni_monument_rm);
        lo.setText(str.get(1).toString());

        new DownloadImageFromInternet((ImageView) findViewById(R.id.imazhi_monument_rm))
                .execute(str.get(2).toString());

        TextView pershkrimi = (TextView) findViewById(R.id.description_monument);
        pershkrimi.setText(str.get(3).toString());
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
            Toast.makeText(getApplicationContext(), "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show();
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
