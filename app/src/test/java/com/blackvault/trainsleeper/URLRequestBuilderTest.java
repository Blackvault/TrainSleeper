package com.blackvault.trainsleeper;

import com.blackvault.trainsleeper.locationstuff.googleplaces.urlrequest.URLRequestBuilder;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Blackvault on 25/05/2017.
 */

public class URLRequestBuilderTest {

    URLRequestBuilder urlRequestBuilder = new URLRequestBuilder();

    @Test
    public void URLRequestBuilderToGoogle_IsTrue() {
        String URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=10.0,20.0&radius=5000&types=train_station&sensor=true&key=GooglePlaceAPIKey";
        String aSearchRaduis = "5000";

        String output = urlRequestBuilder.buildString(10.00, 20.00, aSearchRaduis).toString();
        Assert.assertEquals(URL, output);
    }
}