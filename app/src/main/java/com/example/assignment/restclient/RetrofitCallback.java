package com.example.assignment.restclient;

import android.util.Log;

import com.example.assignment.R;
import com.google.gson.JsonParseException;
import com.squareup.okhttp.Headers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import com.example.assignment.restclient.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public abstract class RetrofitCallback<T> implements retrofit.Callback {
    final Callback callback;
    private String baseURL;
    private String url;

    public RetrofitCallback(Callback<T> callback) {
        this(callback, null);
    }

    private RetrofitCallback(Callback<T> callback, Class t) {
        this.callback = callback;
    }

    @Override
    public void onResponse(Response response, Retrofit retrofit)
    {
        baseURL = retrofit.baseUrl().url().toString();
        Log.e("BaseURL",baseURL);
        Log.d("RESPONSE",response.message());
        url = response.raw().request().httpUrl().toString();
        if (!response.isSuccess()) {
            switch (response.code()) {
                case 400:
                    parseData(response);
                    return;
                case 404:
                    parseData(response);
                    return;
            }

            callback.onError("", ErrorCodes.UNKNOWN_ERROR);
            return;
        }
        callback.onSuccess(response.body());
    }

    void parseData(Response response) {
        try {
            String responseJSON = response.errorBody().string();
            JSONObject jsonObject = new JSONObject(responseJSON);
            JSONObject message = jsonObject.optJSONObject("message");
            if (message != null) {
                String token_expired_str = jsonObject.getString("message");

            } else {
                try {
                    JSONArray message1 = jsonObject.optJSONArray("message");
                    if (message1 != null) {
                        String o = (String) message1.get(0);
                        if(o != null){
                            callback.onError(o,ErrorCodes.BAD_INPUT);
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    callback.onError(jsonObject.getString("message"), ErrorCodes.BAD_INPUT);
                }
            }

        } catch (IOException e) {
            callback.onError(R.string.INTERNAL_SERVER_ERROR, ErrorCodes.INTERNAL_SERVER_ERROR);
            Log.d("IOException", "parseData: "+e.toString());
        } catch (JSONException e) {
            Log.d("JSONException", "parseData: "+e.toString());

            callback.onError(R.string.INTERNAL_SERVER_ERROR, ErrorCodes.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (t instanceof IOException) {
            Log.d("ON FAILURE",t.toString());
            callback.onError(R.string.NO_INTERNET, ErrorCodes.NO_NETWORK);
            return;
        } else if (t instanceof JsonParseException) {
            Log.d("JsonParseException", "onFailure: "+t.toString());
            callback.onError(R.string.INTERNAL_SERVER_ERROR, ErrorCodes.INTERNAL_SERVER_ERROR);
            return;
        }
        callback.onError(t.getMessage(), ErrorCodes.UNKNOWN_ERROR);
    }
}