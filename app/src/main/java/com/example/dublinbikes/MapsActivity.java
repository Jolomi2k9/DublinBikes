package com.example.dublinbikes;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng mLatLng;
    String mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);

        //Get the data sent from DetailsActivity
        Intent intent = getIntent();
        mLatLng = intent.getParcelableExtra("latLon");
        mDescription = intent.getStringExtra("desc");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (mLatLng != null) {
            // Add a marker for location and description/Station name sent from DetailsActivity
            mMap.addMarker(new MarkerOptions().position(mLatLng).title(mDescription));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng,14));
        }
    }
}
