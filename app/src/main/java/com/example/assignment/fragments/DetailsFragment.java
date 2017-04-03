package com.example.assignment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.assignment.R;
import com.example.assignment.Utils.CacheDataOnDisk;
import com.example.assignment.Utils.Constants;
import com.example.assignment.Utils.SharedPreferencesData;
import com.example.assignment.activity.MainActivity;
import com.example.assignment.adapter.DetailsItemAdapter;
import com.example.assignment.restclient.Applib;
import com.example.assignment.restclient.Callback;
import com.example.assignment.restclient.ErrorCodes;
import com.example.assignment.restclient.INetworkInterface;
import com.example.assignment.restclient.model.DetailsListOfItems;
import com.example.assignment.restclient.model.Transactions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sujata on 02/04/17.
 */

public class DetailsFragment extends Fragment {

    private static Context mCOntext;
    private String mid;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView recyclerView;
    private List<Transactions> transactionses;
    private DetailsItemAdapter detailsItemAdapter;
    private TextView no_data_TV;
    private TextView latitude;
    private TextView longitude;

    public static DetailsFragment newInstance(int id, Context activity) {
         mCOntext = activity;
         DetailsFragment f = new DetailsFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("id", id);
        f.setArguments(args);

        return f;
    }

    public int getShownIndex() {
        return getArguments().getInt("id", 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.details, null);
        return inflate;    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) getView().findViewById(R.id.details_recyclerView);
        no_data_TV = (TextView) getView().findViewById(R.id.no_data_TV);
        latitude = (TextView) getView().findViewById(R.id.latitude);
        longitude = (TextView) getView().findViewById(R.id.longitude);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        Bundle arguments = getArguments();
        if(arguments != null){
           mid = (String) arguments.get("id");
        }

        ((MainActivity) getActivity()).searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Transactions> transactionsData = CacheDataOnDisk.getTransactionsData(getActivity(), Constants.TRANSACTIONS_FILE_NAME, Constants.TRANSACTIONS_MODEL_NAME);
                if(newText != null && newText.trim().length() == 0){
                    detailsItemAdapter = new DetailsItemAdapter(transactionsData, getActivity());
                    recyclerView.setAdapter(detailsItemAdapter);
                }else {
                    ArrayList<Transactions> filteredList = new ArrayList<Transactions>();

                    for (int i = 0; i < transactionsData.size(); i++) {

                        final String text = transactionsData.get(i).getCustomerMobileNumber();
                        if (text.startsWith(newText)) {

                            filteredList.add(transactionsData.get(i));
                        }
                    }
                    detailsItemAdapter = new DetailsItemAdapter(filteredList, getActivity());
                    recyclerView.setAdapter(detailsItemAdapter);
                }

                return false;
            }
        });

        SharedPreferencesData sharedPreferencesData = new SharedPreferencesData();
        if(sharedPreferencesData.getLatitude() != null){
            latitude.setText(sharedPreferencesData.getLatitude());
        }

        if(sharedPreferencesData.getLongitude() != null){
            longitude.setText(sharedPreferencesData.getLongitude());
        }

        Applib applib = Applib.getApplib();
        INetworkInterface networkClient = applib.getNetworkClient();
        networkClient.getDetailsItemsList(mid, new Callback<DetailsListOfItems>() {
            @Override
            public void onSuccess(DetailsListOfItems detailsListOfItems) {
                Transactions[] transactions = detailsListOfItems.getTransactions();
                transactionses = new LinkedList(Arrays.asList(transactions));
                CacheDataOnDisk.saveTransactionsData(transactionses,getActivity(),Constants.TRANSACTIONS_FILE_NAME, Constants.TRANSACTIONS_MODEL_NAME);
                detailsItemAdapter = new DetailsItemAdapter(transactionses,getActivity());
                recyclerView.setAdapter(detailsItemAdapter);

                no_data_TV.setVisibility(View.GONE);

            }

            @Override
            public void onError(String error, ErrorCodes code) {
                super.onError(error, code);
                no_data_TV.setVisibility(View.VISIBLE);
            }
        });
    }
}
