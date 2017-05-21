package com.blackvault.trainsleeper;

import android.Manifest;

import android.content.Context;

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
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager mLocationManager;
    String mLocationProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
            mLocationManager.requestLocationUpdates(mLocationProvider, 1500, 1, this);


            if (currentLocation != null)
                onLocationChanged(currentLocation);
            else
                Toast.makeText(getBaseContext(), "No Location Provider Found distanceBetweenTwoGPSPoints Your Code", Toast.LENGTH_SHORT).show();
        }

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
    public void onLocationChanged(Location location) {

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


        DistanceLocationService distanceLocationService = new DistanceLocationService();
        double distanceBetweenTwoGPSPoints =  distanceLocationService.distanceBetweenTwoGPSPoints(location,destinationLocation);
        boolean inRange =  distanceLocationService.inRange(distanceBetweenTwoGPSPoints,1000);

        //Prepare for formatted Text
        DecimalFormat df = new DecimalFormat("#.0000");
        String angleFormated = df.format(distanceBetweenTwoGPSPoints);

        TextView metersText = (TextView) findViewById(R.id.range);
        metersText.setText(angleFormated + " meters to Lisburn Station.");

        TextView inRangeText = (TextView) findViewById(R.id.inRange);
        inRangeText.setText("In range: " + String.valueOf(inRange));

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
