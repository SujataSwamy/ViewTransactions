package com.example.assignment.interfaces;


import com.example.assignment.restclient.ErrorCodes;
import com.example.assignment.restclient.model.Location;

/**
 * Created by sujata on 03/04/17.
 */


public interface LocationCallBack {

//    public void getLocation(Location location);
    public void getLocation(String latitude,String longitude);

    public void onError(String message, ErrorCodes code);
}
