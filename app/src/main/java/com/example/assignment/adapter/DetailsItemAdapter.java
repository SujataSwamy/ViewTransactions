package com.example.assignment.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.assignment.R;
import com.example.assignment.restclient.model.Merchants;
import com.example.assignment.restclient.model.Transactions;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by sujata on 03/04/17.
 */

public class DetailsItemAdapter extends RecyclerView.Adapter<DetailsItemAdapter.ViewHolder> {

    private Context mContext;
    public List<Transactions> mDataset;
    public JSONObject jsonObjectPlaceOrder;
    public  int totalItem ;

    public DetailsItemAdapter(List<Transactions> datas, Context context) {
        mDataset = datas;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mobileNoValueTV;
        private TextView cardNoValue;
        private TextView cardNoTV;
        private TextView refAmtValueTV;
        private TextView date_value_textview;
        private TextView withdrawOrDepositValue;
        private TextView withdrawOrDepositTV;
        private TextView transactionIdValue;
        private CardView card_view;

        public ViewHolder(View v) {
            super(v);
            card_view = (CardView) v.findViewById(R.id.card_view);
            transactionIdValue = (TextView) v.findViewById(R.id.transactionIdValue);
            withdrawOrDepositTV = (TextView) v.findViewById(R.id.withdrawOrDepositTV);
            withdrawOrDepositValue = (TextView) v.findViewById(R.id.withdrawOrDepositValue);
            date_value_textview = (TextView) v.findViewById(R.id.date_value_textview);
            refAmtValueTV = (TextView) v.findViewById(R.id.refAmtValueTV);
            mobileNoValueTV = (TextView) v.findViewById(R.id.mobileNoValueTV);
            cardNoTV = (TextView) v.findViewById(R.id.cardNoTV);
            cardNoValue = (TextView) v.findViewById(R.id.cardNoValue);
        }
    }

    public void add(int position, Transactions item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Transactions item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public void update(int position){
        notifyItemChanged(position);
    }


    @Override
    public DetailsItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_details, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Transactions data= mDataset.get(position);
        holder.transactionIdValue.setText(data.getTransaction_id()+"");
        if(data.getCreditOrDebit() != null && data.getCreditOrDebit().equalsIgnoreCase("credit")){
            holder.withdrawOrDepositTV.setText(mContext.getResources().getString(R.string.withdraw_text));
        }else{
            holder.withdrawOrDepositTV.setText(mContext.getResources().getString(R.string.deposit_text));
        }

        holder.withdrawOrDepositValue.setText(data.getAmount());

        holder.refAmtValueTV.setText(data.getReference_number()+"");

        holder.mobileNoValueTV.setText(data.getCustomerMobileNumber()+"");
        holder.cardNoValue.setText(data.getCard_number()+"");

        SimpleDateFormat readFormat = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat writeFormat = new SimpleDateFormat("dd MMM yyyy");

        java.util.Date date = null;

        try {
            date = readFormat.parse(data.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.date_value_textview.setText(writeFormat.format(date));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}