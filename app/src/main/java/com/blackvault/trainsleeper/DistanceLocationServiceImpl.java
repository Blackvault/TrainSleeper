package com.blackvault.trainsleeper;

import android.location.Location;

/**
 * Created by Blackvault on 16/05/2017.
 */

class DistanceLocationServiceImpl implements DistanceLocationService{

    public double distanceBetweenTwoGPSPoints(Location currentLocation, Location destinationLocation) {

        return destinationLocation.distanceTo(currentLocation);
    }

    public boolean inRange(double distanceBetweenLocations, int distanceRange) {

        return distanceBetweenLocations <= distanceRange;
    }

    public boolean nearDestination(Location currentLocation, Location destinationLocation, int distanceRange) {

        double distance = distanceBetweenTwoGPSPoints(currentLocation, destinationLocation);

        return inRange(distance, distanceRange);
    }
}
