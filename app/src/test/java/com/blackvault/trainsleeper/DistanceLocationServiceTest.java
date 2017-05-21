package com.blackvault.trainsleeper;

import org.junit.Test;

import static org.junit.Assert.*;


public class DistanceLocationServiceTest {

    DistanceLocationService locationService = new DistanceLocationServiceImpl();
    int distanceRangeInMeters = 1000;

    @Test
    public void withinRangeIsTrue() throws Exception {
        double distanceBetweenTwoPoints = 500.1234;
        assertTrue("Distance is True", locationService.inRange(distanceBetweenTwoPoints, distanceRangeInMeters));
    }

    @Test
    public void withinRangeIsFalse() throws Exception {
        double distanceBetweenTwoPoints = 1500.1234;
        assertFalse("Distance is False", locationService.inRange(distanceBetweenTwoPoints, distanceRangeInMeters));
    }
}