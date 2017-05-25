package com.blackvault.trainsleeper.googleplaces;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Blackvault on 24/05/2017.
 */
public class ParseResponse extends AsyncTask<String, Integer, List<HashMap<String, String>>> {

    JSONObject mJObject;

    // Invoked by execute() method of this object
    @Override
    protected List<HashMap<String, String>> doInBackground(String... aJSONData) {

        return parse(aJSONData);
    }

    @Nullable
    public List<HashMap<String, String>> parse(String[] aJSONData) {
        List<HashMap<String, String>> places = null;
        PlaceToJSONParser placeJson = new PlaceToJSONParser();

        try {
            mJObject = new JSONObject(aJSONData[0]);

            places = placeJson.parse(mJObject);

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        }
        return places;
    }
}