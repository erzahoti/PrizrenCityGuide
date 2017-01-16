package com.erza.prizrencityguide.Busses;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.erza.prizrencityguide.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class BussesMap extends FragmentActivity implements OnMapReadyCallback {

    private static final LatLng JETA_E_RE = new LatLng(42.2254018, 20.7156577);
    private static final LatLng ARBANA = new LatLng(42.236080, 20.708664);
    private static final LatLng PETROVA = new LatLng(42.246300, 20.733218);
    private static final LatLng LUBIZHDA = new LatLng(42.236079, 20.762977);
    private static final LatLng DARDANIA = new LatLng(42.227690, 20.756797);
    private static final LatLng SPITALI = new LatLng(42.203763, 20.729368);

    private GoogleMap mMap;
    private Marker mJetaeRE;
    private Marker mArbana;
    private Marker mPetrova;
    private Marker mLubizhda;
    private Marker mDardania;
    private Marker mSpitali;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng prizren = new LatLng(42.222048, 20.736689);

        // Lokacionet e linjave
        mJetaeRE = mMap.addMarker(new MarkerOptions()
                .position(JETA_E_RE)
                .title("Jeta e Re"));
        mJetaeRE.setTag(0);

        mArbana = mMap.addMarker(new MarkerOptions()
                .position(ARBANA)
                .title("Arbana"));
        mArbana.setTag(0);

        mPetrova = mMap.addMarker(new MarkerOptions()
                .position(PETROVA)
                .title("Petrova"));
        mPetrova.setTag(0);

        mLubizhda = mMap.addMarker(new MarkerOptions()
                .position(LUBIZHDA)
                .title("Lubizhda"));
        mLubizhda.setTag(0);

        mDardania = mMap.addMarker(new MarkerOptions()
                .position(DARDANIA)
                .title("Dardania"));
        mDardania.setTag(0);

        mSpitali = mMap.addMarker(new MarkerOptions()
                .position(SPITALI)
                .title("Spitali"));
        mSpitali.setTag(0);
        mSpitali.showInfoWindow();

        Circle circle = mMap.addCircle(new CircleOptions()
                .center(prizren)
                .radius(4200)
                .strokeColor(Color.RED));

        // Ketu eshte bere zmadhimi ne harte tek lokacioni i kerkuar
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(prizren,13));
    }
}
