package com.example.assignment.restclient;

import com.example.assignment.Utils.GoogleAPILocation;
import com.example.assignment.restclient.model.DetailsListOfItems;
import com.example.assignment.restclient.model.ListOfItems;


/**
 * Created by sujata on 02/04/17.
 */

public interface INetworkInterface {


        void getListOfItems(Callback<ListOfItems> callBack);

        void getDetailsItemsList(String id, Callback<DetailsListOfItems> callBack);

        void getLocationFromApi(String ltLong, String sensor, Callback<GoogleAPILocation> geoLocationCallback);


}
