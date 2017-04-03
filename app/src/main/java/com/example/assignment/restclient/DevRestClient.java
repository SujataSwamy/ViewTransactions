package com.example.assignment.restclient;

import com.example.assignment.restclient.RestClient;

/**
 * Created by sujata on 02/04/17.
 */
public final class DevRestClient extends RestClient {

    @Override
    protected String getServerBaseURL() {
        return "http://115.113.36.187:8080";
    }


}
