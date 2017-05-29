package com.blackvault.trainsleeper;

/**
 * Created by Blackvault on 28/05/2017.
 */

class Station {

    private double latitude;
    private double longitdue;
    private String name;

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitdue) {
        this.longitdue = longitdue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongtide() {
        return longitdue;
    }

    public String getName() {
        return name;
    }
}
