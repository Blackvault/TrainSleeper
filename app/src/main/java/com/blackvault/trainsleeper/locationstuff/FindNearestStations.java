package com.blackvault.trainsleeper.locationstuff;

import android.location.Location;
import android.support.annotation.NonNull;

import com.blackvault.trainsleeper.Station;
import com.blackvault.trainsleeper.locationstuff.googleplaces.ParseResponse;
import com.blackvault.trainsleeper.locationstuff.googleplaces.PlacesTask;
import com.blackvault.trainsleeper.locationstuff.googleplaces.urlrequest.URLRequestBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Blackvault on 28/05/2017.
 */

public class FindNearestStations {

    private static final int SEARCHRADIUSDEFAULT = 1000;
    private int mSearchRadius;
    private ParseResponse mParseResponse;
    private URLRequestBuilder mURLRequestBuilder;

    public FindNearestStations() {
        this.mSearchRadius = SEARCHRADIUSDEFAULT;
        this.mParseResponse = new ParseResponse();
        this.mURLRequestBuilder = new URLRequestBuilder();
    }

    public int getSearchRadius() {
        return mSearchRadius;
    }

    public void setSearchRadius(int aSearchRadius) {
        this.mSearchRadius = aSearchRadius;
    }

    public List<Station> retrieveStations(Location aCurrentLocation) {

        List<Station> nearestStations = new ArrayList<>();
        PlacesTask placesTask = new PlacesTask();
        StringBuilder request = mURLRequestBuilder.buildRequest(aCurrentLocation, Integer.toString(mSearchRadius));

        try {
            String responseResult = placesTask.execute(request.toString()).get();

            List<HashMap<String, String>> responseList = mParseResponse.execute(responseResult).get();

            for (int i = 0; i < responseList.size(); i++) {

                Station foundStation = constructNewStation(responseList, i);

                nearestStations.add(foundStation);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nearestStations;
    }

    @NonNull
    private Station constructNewStation(List<HashMap<String, String>> aResponseParse, int i) {

        HashMap<String, String> stationHash = aResponseParse.get(i);

        Station station = new Station();
        station.setName(stationHash.get("place_name"));
        station.setLatitude(Double.parseDouble(stationHash.get("lat")));
        station.setLongitude(Double.parseDouble(stationHash.get("lng")));

        return station;
    }
}