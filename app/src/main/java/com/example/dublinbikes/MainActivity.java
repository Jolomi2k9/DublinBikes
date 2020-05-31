

/*
Name: Oritsejolomi Sillo
Student Number: 20091
*/

package com.example.dublinbikes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements dublin_bikes_adapter.onItemClickListener {

    //constants used in onItemClick to pass data via intent
    public static final String EXTRA_NAME  = "name";
    public static final String EXTRA_ADDRESS  = "address";
    public static final String EXTRA_POSITION_LAT  = "position_lat";
    public static final String EXTRA_POSITION_LNG  = "position_lng";
    public static final String EXTRA_BIKE_STANDS  = "bike_stands";
    public static final String EXTRA_AVAILABLE_BIKE_STANDS  = "available_bike_stands";
    public static final String EXTRA_AVAILABLE_BIKES  = "available_bikes";
    public static final String EXTRA_STATUS  = "status";



    private RecyclerView mRecyclerView;
    private dublin_bikes_adapter mDublin_bikes_adapter;
    private ArrayList<dublin_bikes_item> mDublin_bikes_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDublin_bikes_list = new ArrayList<>();

        //Calls DownloadTasks and passes url to it
        DownloadTask task = new DownloadTask();
        task.execute("https://api.jcdecaux.com/vls/v1/stations?contract=dublin&apiKey=edf2c39aed4c6e06ba1cf8a607bf817847617b75");
    }

    @Override
    //starts the details activity and passes the value of clicked items
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this,DetailsActivity.class);
        dublin_bikes_item clickedItem = mDublin_bikes_list.get(position);

        detailIntent.putExtra(EXTRA_NAME,clickedItem.getmName());
        detailIntent.putExtra(EXTRA_ADDRESS,clickedItem.getmAddress());
        detailIntent.putExtra(EXTRA_POSITION_LAT,clickedItem.getmPositionLat());
        detailIntent.putExtra(EXTRA_POSITION_LNG,clickedItem.getmPositionLng());
        detailIntent.putExtra(EXTRA_BIKE_STANDS,clickedItem.getmBike_stands());
        detailIntent.putExtra(EXTRA_AVAILABLE_BIKE_STANDS,clickedItem.getmAvailable_bike_stands());
        detailIntent.putExtra(EXTRA_AVAILABLE_BIKES,clickedItem.getmAvailable_bikes());
        detailIntent.putExtra(EXTRA_STATUS,clickedItem.getmStatus());

        startActivity(detailIntent);
    }

    //downloads data from supplied url, returns a string
    public class DownloadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //Takes downloaded string api data and converts it to JSONArray
            try {
                JSONArray arr = new JSONArray(s);

            //Create JSON objects from individual array items then loops through them to
            //extract specific data with keys.
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jsonObject1 = arr.getJSONObject(i);
                    JSONObject posi = jsonObject1.getJSONObject("position");
                    String city = jsonObject1.getString("contract_name");
                    String name = jsonObject1.getString("name");
                    String address = jsonObject1.getString("address");
                    double positionLat = posi.getDouble("lat");
                    double positionLng = posi.getDouble("lng");
                    int bike_stands = jsonObject1.getInt("bike_stands");
                    int available_bike_stands = jsonObject1.getInt("available_bike_stands");
                    int available_bikes = jsonObject1.getInt("available_bikes");
                    String status = jsonObject1.getString("status");

                    //add retrieved data to mDublin_bikes array list using dublin_bikes_item class constructor
                    mDublin_bikes_list.add(new dublin_bikes_item(city, name, address, positionLat, positionLng, bike_stands, available_bike_stands, available_bikes, status));
                }

                //use mDublin_bikes_adapter to fill recyclerview with data gotten form JSON array above
                mDublin_bikes_adapter = new dublin_bikes_adapter(MainActivity.this, mDublin_bikes_list);
                mRecyclerView.setAdapter(mDublin_bikes_adapter);
                mDublin_bikes_adapter.setOnItemClickListener(MainActivity.this);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
