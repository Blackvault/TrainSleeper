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
import android.widget.Toast;

/**
 * Created by Blackvault on 03/06/2017.
 */

class JourneyThing implements LocationListener {

    private LocationManager mLocationManager;
    private String mLocationProvider;
    private Context mContext;
    private Station aDestinationStation;

    DistanceLocationService mDistanceLocationService;
    Location mDestination;

    public JourneyThing(Context aContext, Station aDestinationStation) {
        this.aDestinationStation = aDestinationStation;
        this.mContext = aContext;

        mLocationManager = (LocationManager) aContext.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        mLocationProvider = mLocationManager.getBestProvider(criteria, false);

        if (mLocationProvider != null && !mLocationProvider.equals("")) {
            if (ActivityCompat.checkSelfPermission(aContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(aContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location currentLocation = mLocationManager.getLastKnownLocation(mLocationProvider);

            if (currentLocation != null)
                onLocationChanged(currentLocation);
            else
                Toast.makeText(aContext, "No Location Provider Found distanceBetweenTwoGPSPoints Your Code", Toast.LENGTH_SHORT).show();
        }

        mDestination = mLocationManager.getLastKnownLocation(mLocationProvider);
        mDestination.setLatitude(this.aDestinationStation.getLatitude());
        mDestination.setLongitude(this.aDestinationStation.getLongtide());
        mDistanceLocationService = new DistanceLocationServiceImpl();
    }

    @Override
    public void onLocationChanged(Location currentLocation) {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        boolean inRange = mDistanceLocationService.nearDestination(currentLocation, mDestination, 2500);

        if (inRange) {
            Toast.makeText(mContext, "In location", Toast.LENGTH_SHORT).show();
        }

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
