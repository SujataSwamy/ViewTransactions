package com.example.assignment.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.assignment.restclient.Applib;

/**
 * Created by sujata on 03/04/17.
 */


public class SharedPreferencesData {

    private String LATITUDE = "LATITUDE";
    private String LONGITUDE = "LONGITUDE";

    public void setLatitude(String secretKey){
        SharedPreferences preferences = Applib.getContext().getSharedPreferences(LATITUDE, Context.MODE_PRIVATE);
        preferences.edit().putString(LATITUDE, secretKey).apply();
    }

    public String getLatitude(){
        SharedPreferences preferences = Applib.getContext().getSharedPreferences(LATITUDE, Context.MODE_PRIVATE);
        return preferences.getString(LATITUDE, null);
    }


    public void setLongitude(String userId){
        SharedPreferences preferences = Applib.getContext().getSharedPreferences(LONGITUDE, Context.MODE_PRIVATE);
        preferences.edit().putString(LONGITUDE, userId).apply();
    }

    public String getLongitude(){
        SharedPreferences preferences = Applib.getContext().getSharedPreferences(LONGITUDE, Context.MODE_PRIVATE);
        return preferences.getString(LONGITUDE, null);
    }
}
