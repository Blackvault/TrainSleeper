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
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.blackvault.trainsleeper.googleplaces.ParseResponse;
import com.blackvault.trainsleeper.googleplaces.urlrequest.URLRequestBuilder;
import com.blackvault.trainsleeper.googleplaces.PlacesTask;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private LocationManager mLocationManager;
    private String mLocationProvider;
    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


         AutoCompleteTextView actv;
        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);

        mDetector = new GestureDetector(this, new SwipeNavigationGesture(getBaseContext()));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        mLocationProvider = mLocationManager.getBestProvider(criteria, false);

        if (mLocationProvider != null && !mLocationProvider.equals("")) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location currentLocation = mLocationManager.getLastKnownLocation(mLocationProvider);

            TextView inRangeText = (TextView) findViewById(R.id.inRange);

            NearestStationHelper stationHelper = new NearestStationHelper();

            String txtToScreen = "";

            List<Station> aResponseParse = stationHelper.Nearest(currentLocation);

            for (int i = 0; i < aResponseParse.size(); i++) {

                Station currentPlace = aResponseParse.get(i);

                double lat = currentPlace.getLatitude();

                double lng = currentPlace.getLongtide();

                String stationName = currentPlace.getName();

                txtToScreen = txtToScreen + "\nStation: " + stationName + "\nLat: " + lat + "\nLong: " + lng;

            }


            inRangeText.setText(txtToScreen);
            mLocationManager.requestLocationUpdates(mLocationProvider, 1500, 1, this);


            String[] languages={"Android ","java","IOS","SQL","JDBC","Web services"};

            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, languages);
            actv.setAdapter(adapter);

            if (currentLocation != null)
                onLocationChanged(currentLocation);
            else
                Toast.makeText(getBaseContext(), "No Location Provider Found distanceBetweenTwoGPSPoints Your Code", Toast.LENGTH_SHORT).show();
        }

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
