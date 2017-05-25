package com.blackvault.trainsleeper.googleplaces.urlrequest;

import java.io.IOException;

/**
 * Created by Blackvault on 25/05/2017.
 */

public interface IURLRetriever {

    String downloadUrl(String aURL) throws IOException;
}
