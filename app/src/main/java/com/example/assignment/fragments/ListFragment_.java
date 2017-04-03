package com.example.assignment.fragments;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import com.example.assignment.R;
import com.example.assignment.adapter.ListItemsAdapter;
import com.example.assignment.restclient.Applib;
import com.example.assignment.restclient.ErrorCodes;
import com.example.assignment.restclient.INetworkInterface;
import com.example.assignment.restclient.model.CityJson;
import com.example.assignment.restclient.model.ListOfItems;
import com.example.assignment.restclient.model.Merchants;
import com.example.assignment.restclient.model.StateJson;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sujata on 02/04/17.
 */

public class ListFragment_ extends Fragment {

    boolean mDualPane;
    int mCurCheckPosition = 0;
    private Applib appLib;
    private INetworkInterface iNetworkClient;
    private List<Merchants> merchantses;
    private CityJson[] cityJson;
    private RecyclerView list_items_recyclerView;
    private LinearLayoutManager mLayoutManager;
    private View inflate;
    boolean isCityFetched = false;
    boolean isStateFetched = false;
    private StateJson[] stateJson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(inflate == null) {
            inflate = inflater.inflate(R.layout.list_items_frag, null);
        }
        return inflate;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        list_items_recyclerView = (RecyclerView) getView().findViewById(R.id.list_items_recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        list_items_recyclerView.setLayoutManager(mLayoutManager);
        appLib = Applib.getApplib(getActivity());

        iNetworkClient = appLib.getNetworkClient();

        iNetworkClient.getListOfItems(new com.example.assignment.restclient.Callback<ListOfItems>() {
            @Override
            public void onSuccess(ListOfItems listOfItems) {
                Merchants[] merchants = listOfItems.getMerchants();
                merchantses = Arrays.asList(merchants);
                new ReadRawFolderJsonData().execute();

            }

            @Override
            public void onError(String error, ErrorCodes code) {
                super.onError(error, code);
            }
        });


        View detailsFrame = getActivity().findViewById(R.id.details);


        // Check that a view exists and is visible
        // A view is visible (0) on the screen; the default value.
        // It can also be invisible and hidden, as if the view had not been
        // added.
        //
        mDualPane = detailsFrame != null
                && detailsFrame.getVisibility() == View.VISIBLE;


        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        outState.putInt("curChoice", mCurCheckPosition);
    }


    private class ReadRawFolderJsonData extends AsyncTask<String, Void, String> {
        private InputStream is;

        @Override
        protected String doInBackground(String... urls) {
            if(!isCityFetched){
               is = getResources().openRawResource(R.raw.cityjson);
            }else{
                 is = getResources().openRawResource(R.raw.statejson);
            }

            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                try {
                    while ((n = reader.read(buffer)) != -1) {
                        writer.write(buffer, 0, n);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            String jsonString = writer.toString();
            return jsonString;

        }

        @Override
        protected void onPostExecute(String result) {
            isCityFetched = true;
            Gson gson = new Gson();

            if(!isStateFetched){
                new ReadRawFolderJsonData().execute();
                cityJson  = gson.fromJson(result,CityJson[].class);
                isStateFetched = true;
            }else{
                stateJson  = gson.fromJson(result,StateJson[].class);

                ListItemsAdapter listItemsAdapter = new ListItemsAdapter(merchantses,getActivity(),cityJson,stateJson);
                list_items_recyclerView.setAdapter(listItemsAdapter);
            }

        }


    }

}
