package com.blackvault.trainsleeper.locationstuff;

import android.location.Location;

/**
 * Created by Blackvault on 21/05/2017.
 */

public interface DistanceLocationService {

    boolean nearDestination(Location currentLocation, Location destinationLocation, int distanceRange);

    boolean inRange(double distanceBetweenTwoPoints, int distanceRangeInMeters);

    double distanceBetweenTwoGPSPoints(Location currentLocation, Location destinationLocation);
}
