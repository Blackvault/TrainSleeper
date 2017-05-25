package com.blackvault.trainsleeper.googleplaces.urlrequest;

import android.location.Location;
import android.support.annotation.NonNull;

/**
 * Created by Blackvault on 24/05/2017.
 */

public class URLRequestBuilder {

    public StringBuilder buildRequest(Location aCurrentLocation,String aSearchRaduis) {

        double latitude = aCurrentLocation.getLatitude();
        double longitude = aCurrentLocation.getLongitude();

        return buildString(latitude, longitude,aSearchRaduis);
    }

    @NonNull
    public StringBuilder buildString(double aLatitude, double aLongitude, String aSearchRaduis) {

        StringBuilder stringBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        stringBuilder.append("location=" + aLatitude + "," + aLongitude);
        stringBuilder.append("&radius=" + aSearchRaduis );
        stringBuilder.append("&types=train_station");
        stringBuilder.append("&sensor=true");
        stringBuilder.append("&key=GooglePlaceAPIKey");

        return stringBuilder;
    }
}