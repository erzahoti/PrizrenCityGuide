package com.erza.prizrencityguide;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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

        // Merret qyteti i Prizrenit me koordinatat e saj
        LatLng prizren = new LatLng(42.214896, 20.738030);

        // Ketu vendoset mundesia per zmadhim e zvogelim
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Ketu behet zmadhimi ne lokacionin e deshiruar
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(prizren,14));
    }
}
