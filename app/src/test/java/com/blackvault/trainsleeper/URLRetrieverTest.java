package com.blackvault.trainsleeper;

import com.blackvault.trainsleeper.locationstuff.googleplaces.urlrequest.MockURLRetriever;
import com.blackvault.trainsleeper.locationstuff.googleplaces.urlrequest.IURLRetriever;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by Blackvault on 25/05/2017.
 */

public class URLRetrieverTest {

    IURLRetriever mock = new MockURLRetriever();

    @Test
    public void TestThatTheURLReturnsResponse() throws IOException {
        String URLTest = "http://test.com";
        String expectedResult = "{\"html_attributions\":[],\"results\":[{\"geometry\":{\"location\":{\"lat\":54.5139466,\"lng\":-6.0453406},\"viewport\":{\"northeast\":{\"lat\":54.5155285302915,\"lng\":-6.043874069708497},\"southwest\":{\"lat\":54.5128305697085,\"lng\":-6.046572030291502}}},\"icon\":\"https://maps.gstatic.com/mapfiles/place_api/icons/train-71.png\",\"id\":\"d90112adcde327a664e58ff56b14ff62c58d8d08\",\"name\":\"Lisburn\",\"photos\":[{\"height\":720,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/109737577458910259557/photos\\\">David Hanlon</a>\"],\"photo_reference\":\"CmRXAAAAeEnrexe50NJh-euH8Vb2QXJQRFUZ989UmcIzrT1ocKw5MWubmDSNwwGJ36CFDTjDw94G0I18EJS5z6eQpODOato-T9UD_tJPbq5Of_U4YpLggjc9lao3vMyNxAaH_30VEhC3PRQ5WwP_rIVyEfCb5twiGhQvDMCn7fFi7FFob1P7nq3U69IbVQ\",\"width\":1280}],\"place_id\":\"ChIJSQKssUIEYUgRPowNEDQxuxE\",\"rating\":4.7,\"reference\":\"CmRRAAAAjOMHMOktaB6S87qtHZbx4b-IIY4QphCEF79ePekDKKEqsY87KQMDu9Rlu5J08NAkQHag0VXM1ZSDywBx_Cg1hdwBuki_J7N3Hlhf0UhAoGYtSR2npWnGuNovF4D3civ8EhBG9I73wcpU58F1OHsQ3W-VGhTMlgbbJCpi5ON_ESQTm0FGwpu75Q\",\"scope\":\"GOOGLE\",\"types\":[\"train_station\",\"transit_station\",\"point_of_interest\",\"establishment\"],\"vicinity\":\"United Kingdom\"},{\"geometry\":{\"location\":{\"lat\":54.5221308,\"lng\":-6.0294829},\"viewport\":{\"northeast\":{\"lat\":54.5234797802915,\"lng\":-6.028133919708498},\"southwest\":{\"lat\":54.5207818197085,\"lng\":-6.030831880291502}}},\"icon\":\"https://maps.gstatic.com/mapfiles/place_api/icons/train-71.png\",\"id\":\"c814d6ed11ccef7a2b8146486c5ffa6eadcd3cbd\",\"name\":\"Hilden Train Station\",\"place_id\":\"ChIJLZPPKEkEYUgRNDPwDQJY2NU\",\"rating\":3,\"reference\":\"CmRSAAAANpbwpcEBfYUhmfAGFhB9iywmDdnyICZEz75kg3jGs2V23NbkTho95JTJr8gVgwYJFguD1KccblOoxrXSKE-kkNIx9GEKc-MGsBkKqq03V3VObZ2tcGa7sUeLfAjYjlQFEhBQJmpiNdJPTQgqhMN-V4rAGhQBtEY0Xd4eJLaCHiRHovXuOeY3Rw\",\"scope\":\"GOOGLE\",\"types\":[\"train_station\",\"transit_station\",\"point_of_interest\",\"establishment\"],\"vicinity\":\"Lambeg\"},{\"geometry\":{\"location\":{\"lat\":54.53006999999999,\"lng\":-6.029420000000001},\"viewport\":{\"northeast\":{\"lat\":54.53141898029149,\"lng\":-6.028071019708499},\"southwest\":{\"lat\":54.52872101970849,\"lng\":-6.030768980291503}}},\"icon\":\"https://maps.gstatic.com/mapfiles/place_api/icons/train-71.png\",\"id\":\"70fb9ed48d33abeb8eeac269c11e8374f2d9d0fb\",\"name\":\"Lambeg\",\"place_id\":\"ChIJ_U_0rzYEYUgRbeTTgJmBjEY\",\"rating\":4,\"reference\":\"CmRRAAAAmv15t1CkqJA-CSbMoR3MfpbEcs4E07Br6fnYAD__4OUbYnOgH-2nhC46atkDr3jPxdxN6Z0-9ym7GPDQalYALuQLpDBwOZxwIe2kYlLPdcILcZ4Kum3ogzwNl2iTbofaEhAGbPodz5xlKhxDZmjUyDt8GhSk6zSkfzr8P4cC1xblJc-PwWXEyw\",\"scope\":\"GOOGLE\",\"types\":[\"train_station\",\"transit_station\",\"point_of_interest\",\"establishment\"],\"vicinity\":\"United Kingdom\"},{\"geometry\":{\"location\":{\"lat\":54.5416584,\"lng\":-6.0183011},\"viewport\":{\"northeast\":{\"lat\":54.5430073802915,\"lng\":-6.016952119708497},\"southwest\":{\"lat\":54.5403094197085,\"lng\":-6.019650080291502}}},\"icon\":\"https://maps.gstatic.com/mapfiles/place_api/icons/train-71.png\",\"id\":\"7c528ddb3cc06fc502b806aecffe695671bb4c88\",\"name\":\"Derriaghy Station\",\"place_id\":\"ChIJUzRm8dMFYUgRICuF2yFjHyU\",\"rating\":3.3,\"reference\":\"CmRRAAAAIXRFT0kQgccgzVerg00fG-rqPFe9q7y7mavgYDeIboJ0Mkyzy91XLCrUP1PsRWb4OmFHFzeNg_5U7IUEkkEQIEIXOAj-WoC9MLyoZWAaJfoeNvCXFYDUVb9cX1hWvv3gEhBgvwimD0oel9Q68KXqGq9cGhS95YKhjf50pr90ZycezGr-TVL9NA\",\"scope\":\"GOOGLE\",\"types\":[\"train_station\",\"transit_station\",\"point_of_interest\",\"establishment\"],\"vicinity\":\"United Kingdom\"}],\"status\":\"OK\"}";
        Assert.assertEquals(expectedResult,mock.downloadUrl(URLTest));
    }
}
