package com.blackvault.trainsleeper;

import android.location.Location;

/**
 * Created by Blackvault on 16/05/2017.
 */

class DistanceLocationService {

    public double distanceBetweenTwoGPSPoints(Location currentGPSLocation, Location currentLocation) {

      return currentLocation.distanceTo(currentGPSLocation );
    }

    public boolean inRange(double distanceBetweenTwoPoints, int distanceRange) {

        return distanceBetweenTwoPoints <= distanceRange;
    }
}
