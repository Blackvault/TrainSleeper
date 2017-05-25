package com.blackvault.trainsleeper.googleplaces;

import android.os.AsyncTask;
import android.util.Log;

import com.blackvault.trainsleeper.googleplaces.urlrequest.IURLRetriever;
import com.blackvault.trainsleeper.googleplaces.urlrequest.MockURLRetriever;

/**
 * Created by Blackvault on 24/05/2017.
 */

public class PlacesTask extends AsyncTask<String, Integer, String> {

    String mResponseData = null;
    IURLRetriever urlRetriever = new MockURLRetriever();

    @Override
    protected String doInBackground(String... url) {
        try {
            mResponseData = urlRetriever.downloadUrl(url[0]);
        } catch (Exception e) {
            Log.d("Background Task", e.toString());
        }
        return mResponseData;
    }

    @Override
    protected void onPostExecute(String aReponseResult) {
        ParseResponse parseResponse = new ParseResponse();

       parseResponse.execute(aReponseResult);
    }


}




