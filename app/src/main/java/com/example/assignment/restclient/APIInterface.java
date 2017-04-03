package com.example.assignment.restclient;

import com.example.assignment.Utils.GoogleAPILocation;
import com.example.assignment.restclient.model.DetailsListOfItems;
import com.example.assignment.restclient.model.ListOfItems;
import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;


/**
 * Created by sujata on 02/04/17.
 */

public interface APIInterface {

    @GET("/Android_test/rest/hello")
    Call<ListOfItems> getListOfItems();

    @GET("/Android_test/rest/hello/{id}")
    Call<DetailsListOfItems> getDetailsOfListItems(@Path("id") String id);

    @GET("http://maps.googleapis.com/maps/api/geocode/json")
    Call<GoogleAPILocation> getLocationFromApi(@Query("latlng") String latLong, @Query("sensor") String sensor);


}
