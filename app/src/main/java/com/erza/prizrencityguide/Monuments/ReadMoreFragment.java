package com.erza.prizrencityguide.Monuments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.erza.prizrencityguide.R;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Erza on 1/17/2017.
 */

public class ReadMoreFragment extends Fragment{

    ArrayList<String> str;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.read_more_fragment, container, false);
       // str =  getIntent().getStringArrayListExtra("lista");

        str =  getArguments().getStringArrayList("listaa");
                //getStringArrayListExtra("lista");

        TextView emri = (TextView) v.findViewById(R.id.emri_monument_rm);
        emri.setText(str.get(0).toString());

        TextView lo = (TextView) v.findViewById(R.id.lokacioni_monument_rm);
        lo.setText(str.get(1).toString());

        new ReadMoreFragment.DownloadImageFromInternet((ImageView) v.findViewById(R.id.imazhi_monument_rm))
                .execute(str.get(2).toString());

        TextView pershkrimi = (TextView) v.findViewById(R.id.description_monument);
        pershkrimi.setText(str.get(3).toString());
        return v;
    }

    //klasa ne vazhdim eshte per marrjen e stringut dhe kthimin e tij ne fotografi/link

    private class  DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
            Toast.makeText(getActivity().getApplicationContext(), "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show();
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
