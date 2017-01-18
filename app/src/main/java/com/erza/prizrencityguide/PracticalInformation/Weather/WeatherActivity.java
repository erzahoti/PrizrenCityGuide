package com.erza.prizrencityguide.PracticalInformation.Weather;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bumptech.glide.Glide;
import com.erza.prizrencityguide.R;
import com.erza.prizrencityguide.PracticalInformation.Weather.model.Location;
import com.erza.prizrencityguide.PracticalInformation.Weather.model.Weather;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Copyright (C) 2013 Surviving with Android (http://www.survivingwithandroid.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class WeatherActivity extends FragmentActivity implements OnMapReadyCallback {

	
	private TextView cityText;
	private TextView condDescr;
	private TextView temp;
	private TextView press;
	private TextView windSpeed;
	private TextView windDeg;
	
	private TextView hum;
	private ImageView imgView;
	private GoogleMap mMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather_activity);
		String city = "Pristina,XK";
		JSONWeatherTask task = new JSONWeatherTask();
		task.execute(new String[]{city});
		cityText = (TextView) findViewById(R.id.cityText);
		condDescr = (TextView) findViewById(R.id.condDescr);
		temp = (TextView) findViewById(R.id.temp);
		hum = (TextView) findViewById(R.id.hum);
		press = (TextView) findViewById(R.id.press);
		windSpeed = (TextView) findViewById(R.id.windSpeed);
		windDeg = (TextView) findViewById(R.id.windDeg);
		imgView = (ImageView) findViewById(R.id.condIcon);

		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map2);
		mapFragment.getMapAsync(this);


	}

	public void onMapReady(GoogleMap googleMap) {

		mMap = googleMap;


		LatLng kosova = new LatLng(42.615390, 20.860624);
		LatLng prizreni = new LatLng(42.214896, 20.738030);
		LatLng prishtina = new LatLng(42.662209, 21.165250);
		LatLng ferizaj = new LatLng(42.368616, 21.149249);
		LatLng gjilani = new LatLng(42.464336, 21.469419);
		LatLng mitrovica = new LatLng(42.893159, 20.854900);
		LatLng peja = new LatLng(42.658349, 20.288949);
		LatLng shkupi = new LatLng(41.998563, 21.423393);



		Marker pz = mMap.addMarker(new MarkerOptions()
				.position(prizreni)
				.title("Prizreni"));
		pz.setTag(0);
		Marker pr = mMap.addMarker(new MarkerOptions()
				.position(prishtina)
				.title("Prishtina"));
		pz.setTag(0);
		Marker fr = mMap.addMarker(new MarkerOptions()
				.position(ferizaj)
				.title("Ferizaj"));
		pz.setTag(0);
		Marker gjl = mMap.addMarker(new MarkerOptions()
				.position(gjilani)
				.title("Gjilani"));
		pz.setTag(0);
		Marker mv = mMap.addMarker(new MarkerOptions()
				.position(mitrovica)
				.title("Mitrovica"));
		pz.setTag(0);
		Marker pj = mMap.addMarker(new MarkerOptions()
				.position(peja)
				.title("Peja"));
		pz.setTag(0);
		Marker shk = mMap.addMarker(new MarkerOptions()
				.position(shkupi)
				.title("Shkupi"));
		pz.setTag(0);


		mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
		{

			@Override
			public boolean onMarkerClick(Marker arg0) {
				if(arg0.getTitle().equals("Prishtina")) {
					String city = "Pristina,XK";
					JSONWeatherTask task = new JSONWeatherTask();
					task.execute(new String[]{city});
				}else if(arg0.getTitle().equals("Prizreni")){
					String city = "Prizren,XK";
					JSONWeatherTask task = new JSONWeatherTask();
					task.execute(new String[]{city});
				}else if(arg0.getTitle().equals("Ferizaj")){
					String city = "Ferizaj,XK";
					JSONWeatherTask task = new JSONWeatherTask();
					task.execute(new String[]{city});
				}else if(arg0.getTitle().equals("Gjilani")){
					String city = "Gjilan,XK";
					JSONWeatherTask task = new JSONWeatherTask();
					task.execute(new String[]{city});
				}else if(arg0.getTitle().equals("Mitrovica")){
					String city = "Mitrovice,XK";
					JSONWeatherTask task = new JSONWeatherTask();
					task.execute(new String[]{city});
				}else if(arg0.getTitle().equals("Shkupi")){
					String city = "Skopje,MK";
					JSONWeatherTask task = new JSONWeatherTask();
					task.execute(new String[]{city});
				}else if(arg0.getTitle().equals("Peja")){
					String city = "Peje,XK";
					JSONWeatherTask task = new JSONWeatherTask();
					task.execute(new String[]{city});
				}
				return true;
			}

		});
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kosova, 8));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {
		
		@Override
		protected Weather doInBackground(String... params) {
			Weather weather = new Weather();
			String data = ( (new WeatherHttpClient()).getWeatherData(params[0]));

			try {
				weather = JSONWeatherParser.getWeather(data);
				
				// Let's retrieve the icon
				weather.iconData = ( (new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return weather;
		
	}



		
	@Override
		protected void onPostExecute(Weather weather) {			
			super.onPostExecute(weather);
			
			Glide.with(getApplication()).load(weather.iconData).into(imgView);
			
			cityText.setText(weather.location.getCity());
			condDescr.setText(weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescr() + ")");
			temp.setText("" + Math.round((weather.temperature.getTemp() - 273.15)) + "\u2103");
			hum.setText("" + weather.currentCondition.getHumidity() + "%");
			press.setText("" + weather.currentCondition.getPressure() + " hPa");
			windSpeed.setText("" + weather.wind.getSpeed() + " mps");
			windDeg.setText("" + weather.wind.getDeg() + "\u2103");
				
		}

  }
}
