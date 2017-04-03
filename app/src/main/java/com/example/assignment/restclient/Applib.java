package com.example.assignment.restclient;

import android.content.Context;

/**
 * Created by sujata on 02/04/17.
 */

public final class Applib {
    private static Applib appLib;
    private static INetworkInterface client;
    private static Context appContext;

    private Applib(Context context) {
        appContext = context;
    }

    public static Applib getApplib(Context context) {
        if (appLib == null) {
            appLib = new Applib(context);
        }
        return appLib;
    }


    public static Applib getApplib() {
        return appLib;
    }

    public INetworkInterface getNetworkClient() {
        if (appContext == null) {
            return null;
        }
        if (client == null) {
//            client = new ProductionRestClient();
            client = new DevRestClient();
        }
        return client;
    }

    public static Context getContext() {
        return appContext;
    }

}