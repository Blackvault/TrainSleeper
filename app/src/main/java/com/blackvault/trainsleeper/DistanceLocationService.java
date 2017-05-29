package com.blackvault.trainsleeper;

import android.location.Location;

/**
 * Created by Blackvault on 21/05/2017.
 */

interface DistanceLocationService {

    boolean nearDestination(Location currentLocation, Location destinationLocation, int distanceRange);

    boolean inRange(double distanceBetweenTwoPoints, int distanceRangeInMeters);

    double distanceBetweenTwoGPSPoints(Location currentLocation, Location destinationLocation);
}
