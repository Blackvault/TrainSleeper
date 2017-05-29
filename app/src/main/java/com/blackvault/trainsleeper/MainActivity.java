package com.blackvault.trainsleeper;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private LocationManager mLocationManager;
    private String mLocationProvider;
    private GestureDetector mDetector;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private static String LOG_TAG = "CardViewActivity";
    private RecyclerView.LayoutManager mLayoutManager;
    List<Station> aResponseParse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        mLocationProvider = mLocationManager.getBestProvider(criteria, false);

        if (mLocationProvider != null && !mLocationProvider.equals("")) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location currentLocation = mLocationManager.getLastKnownLocation(mLocationProvider);

            NearestStationHelper stationHelper = new NearestStationHelper(1000);
            stationHelper.setSearchRadius(5000);

            aResponseParse = stationHelper.retrieveStation(currentLocation);

            mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
            mRecyclerView.setHasFixedSize(false);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new MyRecyclerViewAdapter(getDataSet(aResponseParse, currentLocation));


            mRecyclerView.setAdapter(mAdapter);


            mLocationManager.requestLocationUpdates(mLocationProvider, 1500, 1, this);


            if (currentLocation != null)
                onLocationChanged(currentLocation);
            else
                Toast.makeText(getBaseContext(), "No Location Provider Found distanceBetweenTwoGPSPoints Your Code", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }

    private ArrayList<Station> getDataSet(List<Station> aResponseParse, Location aCurrentLocation) {
        ArrayList stations = new ArrayList<>();

        DistanceLocationService distanceLocationService = new DistanceLocationServiceImpl();

        for (int i = 0; i < aResponseParse.size(); i++) {

            Location destinationLocation = new Location("");
            destinationLocation.setLatitude(aResponseParse.get(i).getLatitude());
            destinationLocation.setLongitude(aResponseParse.get(i).getLongtide());
            double distanceBetweenLocations = distanceLocationService.distanceBetweenTwoGPSPoints(aCurrentLocation, destinationLocation);

            Station station = new Station(aResponseParse.get(i).getName(), aResponseParse.get(i).getLatitude(), aResponseParse.get(i).getLongtide(), distanceBetweenLocations);


            stations.add(i, station);
        }
        return stations;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);

        if (true) {
            Intent i = new Intent(getApplicationContext(), SecondActivity.class);
            startActivity(i);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location currentLocation) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location destinationLocation = mLocationManager.getLastKnownLocation(mLocationProvider);
        destinationLocation.setLatitude(54.514054);
        destinationLocation.setLongitude(-6.045811);


        DistanceLocationService distanceLocationService = new DistanceLocationServiceImpl();
        boolean inRange = distanceLocationService.nearDestination(currentLocation, destinationLocation, 1000);

        //   TextView inRangeText = (TextView) findViewById(R.id.inRange);

        //   inRangeText.setText(inRange ? "In range of Lisburn Station" : "Not in range of Lisburn Station");

    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}
