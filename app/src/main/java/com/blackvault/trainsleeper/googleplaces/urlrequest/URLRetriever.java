package com.blackvault.trainsleeper.googleplaces.urlrequest;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Blackvault on 25/05/2017.
 */

public class URLRetriever implements IURLRetriever {


    public String downloadUrl(String aURL) throws IOException {

        String responseData = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            URL requestURL = new URL(aURL);

            httpURLConnection = (HttpURLConnection) requestURL.openConnection();

            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer stringBuffer = new StringBuffer();

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            responseData = stringBuffer.toString();

            bufferedReader.close();

        } catch (Exception e) {
            Log.d("Exc downloading url", e.toString());
        } finally {
            inputStream.close();
            httpURLConnection.disconnect();
        }
        return responseData;
    }
}
