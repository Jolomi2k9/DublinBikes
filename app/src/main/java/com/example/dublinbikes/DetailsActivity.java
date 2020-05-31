

/*
Name: Oritsejolomi Sillo
Student Number: 20091
*/

package com.example.dublinbikes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import static com.example.dublinbikes.MainActivity.EXTRA_ADDRESS;
import static com.example.dublinbikes.MainActivity.EXTRA_AVAILABLE_BIKES;
import static com.example.dublinbikes.MainActivity.EXTRA_AVAILABLE_BIKE_STANDS;
import static com.example.dublinbikes.MainActivity.EXTRA_BIKE_STANDS;
import static com.example.dublinbikes.MainActivity.EXTRA_NAME;
import static com.example.dublinbikes.MainActivity.EXTRA_POSITION_LAT;
import static com.example.dublinbikes.MainActivity.EXTRA_POSITION_LNG;
import static com.example.dublinbikes.MainActivity.EXTRA_STATUS;

public class DetailsActivity extends AppCompatActivity {
    private Button buttonMap;;

    double positionLat;
    double positionLng;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //gets clicked data from main activity and assign to variables
        Intent intent = getIntent();
        name = intent.getStringExtra(EXTRA_NAME);
        String address = intent.getStringExtra(EXTRA_ADDRESS);
        positionLat = intent.getDoubleExtra(EXTRA_POSITION_LAT,0);
        positionLng = intent.getDoubleExtra(EXTRA_POSITION_LNG,0);
        int bikeStands = intent.getIntExtra(EXTRA_BIKE_STANDS,0);
        int availableBikeStands = intent.getIntExtra(EXTRA_AVAILABLE_BIKE_STANDS,0);
        int availableBikes = intent.getIntExtra(EXTRA_AVAILABLE_BIKES,0);
        String status = intent.getStringExtra(EXTRA_STATUS);


        TextView textViewName = findViewById(R.id.text_name_details);
        TextView textViewAddress = findViewById(R.id.text_address_details);
        TextView textViewBikeStands = findViewById(R.id.text_bike_stands_details);
        TextView textViewAvailableBikeStands = findViewById(R.id.text_available_bike_stand_details);
        TextView textViewAvailableBikes = findViewById(R.id.text_available_bike_details);
        TextView textViewStatus = findViewById(R.id.text_status_details);
        buttonMap = findViewById(R.id.buttonMap);

        //sets the extra values to our text view
        textViewName.setText(name);
        textViewAddress.setText(address);
        textViewBikeStands.setText("Bike Stands: "+bikeStands);
        textViewAvailableBikeStands.setText("Available bike stands: "+availableBikeStands);
        textViewAvailableBikes.setText("Available bikes: "+availableBikes);
        textViewStatus.setText("Status: "+status);

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLocation();
            }
        });

    }

    public void gotoLocation(){

        
        LatLng goToLocation = new LatLng(positionLat, positionLng);

        //passes latitude and logitude data to map activity
        Bundle args = new Bundle();
        args.putParcelable("latLon", goToLocation);
        args.putString("desc", name);

        Intent i = new Intent(this, MapsActivity.class);
        i.putExtras(args);
        startActivity(i);
    }
}
