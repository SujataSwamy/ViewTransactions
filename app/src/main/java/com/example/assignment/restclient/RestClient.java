package com.example.assignment.restclient;

/**
 * Created by sujata on 02/04/17.
 */
import com.example.assignment.Utils.GoogleAPILocation;
import com.example.assignment.restclient.model.DetailsListOfItems;
import com.example.assignment.restclient.model.ListOfItems;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import com.squareup.okhttp.*;
import retrofit.*;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by sujata on 25/02/17.
 */

public  abstract class RestClient implements INetworkInterface {

    private APIInterface networkService;

    public RestClient() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getServerBaseURL())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        networkService = retrofit.create(APIInterface.class);
    }

    protected abstract String getServerBaseURL();

    @Override
    public void getListOfItems(com.example.assignment.restclient.Callback<ListOfItems> callBack) {
        Call<ListOfItems> listOfItems = networkService.getListOfItems();
        listOfItems.enqueue(new RetrofitCallback<ListOfItems>(callBack) {
        });
    }

    @Override
    public void getDetailsItemsList(String id, com.example.assignment.restclient.Callback<DetailsListOfItems> callBack) {
        Call<DetailsListOfItems> listOfItems = networkService.getDetailsOfListItems(id);
        listOfItems.enqueue(new RetrofitCallback<DetailsListOfItems>(callBack) {
        });
    }

    @Override
    public void getLocationFromApi(final String ltLong, final String sensor, final Callback<GoogleAPILocation> geoLocationCallback) {
        Call<GoogleAPILocation> geoLocationCall = networkService.getLocationFromApi(ltLong, sensor);
        geoLocationCall.enqueue(new RetrofitCallback<GoogleAPILocation>(geoLocationCallback) {


            @Override
            public void onResponse(Response response, Retrofit retrofit) {
                super.onResponse(response, retrofit);
            }
        });
    }
}
