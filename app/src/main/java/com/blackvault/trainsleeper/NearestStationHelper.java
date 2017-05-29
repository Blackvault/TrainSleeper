package com.blackvault.trainsleeper;

import android.location.Location;
import android.support.annotation.NonNull;

import com.blackvault.trainsleeper.googleplaces.ParseResponse;
import com.blackvault.trainsleeper.googleplaces.PlacesTask;
import com.blackvault.trainsleeper.googleplaces.urlrequest.URLRequestBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Blackvault on 28/05/2017.
 */

class NearestStationHelper {

    private int mSearchRadius;

    public NearestStationHelper(int aSearchRadius) {
        this.mSearchRadius = aSearchRadius;
    }

    public int getSearchRadius() {
        return mSearchRadius;
    }

    public void setSearchRadius(int aSearchRadius) {
        this.mSearchRadius = aSearchRadius;
    }

    public List<Station> retrieveStation(Location aCurrentLoaction) {

        List<Station> stations = new ArrayList<>();
        URLRequestBuilder urlRequestBuilder = new URLRequestBuilder();

        StringBuilder urlString = new StringBuilder(urlRequestBuilder.buildRequest(aCurrentLoaction, Integer.toString(mSearchRadius)));
        PlacesTask placesTask = new PlacesTask();

        try {
            String responseResult = placesTask.execute(urlString.toString()).get();

            ParseResponse parseResponse = new ParseResponse();

            List<HashMap<String, String>> responseList = parseResponse.execute(responseResult).get();

            for (int i = 0; i < responseList.size(); i++) {

                Station newStation = constructStation(responseList, i);

                stations.add(newStation);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return stations;
    }

    @NonNull
    private Station constructStation(List<HashMap<String, String>> aResponseParse, int i) {

        Station station = new Station();

        HashMap<String, String> stationHash = aResponseParse.get(i);

        double latitude = Double.parseDouble(stationHash.get("lat"));
        station.setLatitude(latitude);

        double longitude = Double.parseDouble(stationHash.get("lng"));
        station.setLongitude(longitude);

        station.setName(stationHash.get("place_name"));

        return station;
    }
}