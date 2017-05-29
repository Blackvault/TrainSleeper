package com.blackvault.trainsleeper;

import android.location.Location;

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

    public List<Station> Nearest(Location aCurrentLoaction) {

        List<Station> stations = new ArrayList<>();
        URLRequestBuilder urlRequestBuilder = new URLRequestBuilder();
        StringBuilder urlString = new StringBuilder(urlRequestBuilder.buildRequest(aCurrentLoaction, "5000"));
        PlacesTask placesTask = new PlacesTask();

        try {
            String aReponseResult = placesTask.execute(urlString.toString()).get();

            ParseResponse parseResponse = new ParseResponse();

            List<HashMap<String, String>> aResponseParse = parseResponse.execute(aReponseResult).get();

            for (int i = 0; i < aResponseParse.size(); i++) {

                Station station = new Station();

                HashMap<String, String> currentPlace = aResponseParse.get(i);

                double lat = Double.parseDouble(currentPlace.get("lat"));
                station.setLatitude(lat);

                double lng = Double.parseDouble(currentPlace.get("lng"));
                station.setLongitdue(lng);

                String stationName = currentPlace.get("place_name");
                station.setName(stationName);

                stations.add(station);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return stations;
    }
}