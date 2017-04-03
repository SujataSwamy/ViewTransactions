package com.example.assignment.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.assignment.R;
import com.example.assignment.activity.MainActivity;
import com.example.assignment.fragments.DetailsFragment;
import com.example.assignment.restclient.model.CityJson;
import com.example.assignment.restclient.model.Merchants;
import com.example.assignment.restclient.model.StateJson;

import org.json.JSONObject;
import java.util.List;

/**
 * Created by sujata on 02/04/17.
 */

public class ListItemsAdapter extends RecyclerView.Adapter<ListItemsAdapter.ViewHolder> {

    private final StateJson[] mStateJson;
    private CityJson[] mCityJson;
    private Context mContext;
    public List<Merchants> mDataset;
    public JSONObject jsonObjectPlaceOrder;
    public  int totalItem ;

    public ListItemsAdapter(List<Merchants> datas, Context context, CityJson[] cityJson, StateJson[] stateJson) {
        mDataset = datas;
        mContext = context;
        mCityJson = cityJson;
        mStateJson = stateJson;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView cityNameTV;
        private TextView stateNameTV;
        private CardView card_view;

        public ViewHolder(View v) {
            super(v);
            stateNameTV = (TextView) v.findViewById(R.id.stateNameTV);
            cityNameTV = (TextView) v.findViewById(R.id.cityNameTV);
            card_view = (CardView) v.findViewById(R.id.card_view);
        }
    }

    public void add(int position, Merchants item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(String item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public void update(int position){
        notifyItemChanged(position);
    }


    @Override
    public ListItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                        int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_state, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Merchants data= mDataset.get(position);
        holder.stateNameTV.setText(data.getMerchant_city() + "");
        int size = mCityJson.length;
       one: for(int c = 0; c < size ; c++ ){
            if(data.getMerchant_city().equalsIgnoreCase(mCityJson[c].getId())){
                holder.cityNameTV.setText(mCityJson[c].getName() + "");
                break one;
            }
        }

        two: for(int c = 0; c < size ; c++ ){
            if(data.getMerchant_city().equalsIgnoreCase(mStateJson[c].getId())){
                holder.stateNameTV.setText(mStateJson[c].getState()+ "");
                break two;
            }
        }

        holder.card_view.setOnClickListener((View v) -> {
            Merchants data1 = mDataset.get(position);
            String id = data1.getMid();
            Bundle bundle = new Bundle();
            bundle.putString("id",id);

            DetailsFragment detailsFragment = new DetailsFragment();
            detailsFragment.setArguments(bundle);
            ((MainActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.details, detailsFragment).commit();

        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
