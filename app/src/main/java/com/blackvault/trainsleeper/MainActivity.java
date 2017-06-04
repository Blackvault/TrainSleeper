package com.blackvault.trainsleeper;

import android.Manifest;
import android.content.Context;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.blackvault.trainsleeper.locationstuff.DistanceLocationService;
import com.blackvault.trainsleeper.locationstuff.DistanceLocationServiceImpl;
import com.blackvault.trainsleeper.locationstuff.FindNearestStations;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private LocationManager mLocationManager;
    private String mLocationProvider;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
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
        mLocationManager.requestLocationUpdates(mLocationProvider, 1000, 10, this);

        if (mLocationProvider != null && !mLocationProvider.equals("")) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location currentLocation = mLocationManager.getLastKnownLocation(mLocationProvider);

            FindNearestStations stationHelper = new FindNearestStations();
            stationHelper.setSearchRadius(5000);

            aResponseParse = stationHelper.retrieveStations(currentLocation);

            mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
            mRecyclerView.setHasFixedSize(false);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new MyRecyclerViewAdapter(getDataSet(aResponseParse, currentLocation));


            mRecyclerView.setAdapter(mAdapter);





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
