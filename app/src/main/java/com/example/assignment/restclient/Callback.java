package com.example.assignment.restclient;

/**
 * Created by sujata on 02/04/17.
 */


public abstract class Callback<T>
{
    public abstract void onSuccess(T t);

    public void onCache(T t){
    }

    public void onError(String error, ErrorCodes code){

    }

    public void onError(int id, ErrorCodes code){
        this.onError(Applib.getContext().getString(id),code);
    }
}


