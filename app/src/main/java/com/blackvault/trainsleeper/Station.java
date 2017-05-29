package com.blackvault.trainsleeper;

import android.location.Location;

/**
 * Created by Blackvault on 28/05/2017.
 */

class Station {

    private double mLatitude;
    private double mLongitude;
    private String mName;
    private double mDistanceFromCurrentLocation;


    public Station(String aName, double aLatitude, double aLongitude, double aDistanceFromCurrentLocation) {
        this.mLatitude = aLatitude;
        this.mLongitude = aLongitude;
        this.mName = aName;
        this.mDistanceFromCurrentLocation = aDistanceFromCurrentLocation;
    }

    public Station(String aName, double aLatitude, double aLongitude) {
        this.mLatitude = aLatitude;
        this.mLongitude = aLongitude;
        this.mName = aName;
    }

    public Station() {
    }

    public double getDistanceFromCurrentLocation() {
        return mDistanceFromCurrentLocation;
    }

    public void setLongitude(double aLongitude) {
        this.mLongitude = aLongitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double aLatitude) {
        this.mLatitude = aLatitude;
    }

    public double getLongtide() {
        return mLongitude;
    }

    public String getName() {
        return mName;
    }

    public void setName(String aName) {
        this.mName = aName;
    }

}
