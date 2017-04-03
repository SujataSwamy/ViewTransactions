package com.example.assignment.restclient.model;

import java.io.Serializable;

/**
 * Created by sujata on 02/04/17.
 */
public class Transactions implements Serializable
{
    private String amount;

    private String reference_number;

    private String card_number;

    private String auth_code;

    private String tid;

    private String mid;

    private String customerMobileNumber;

    private String date;

    private String creditOrDebit;

    private String transaction_id;

    public String getAmount ()
    {
        return amount;
    }

    public void setAmount (String amount)
    {
        this.amount = amount;
    }

    public String getReference_number ()
    {
        return reference_number;
    }

    public void setReference_number (String reference_number)
    {
        this.reference_number = reference_number;
    }

    public String getCard_number ()
    {
        return card_number;
    }

    public void setCard_number (String card_number)
    {
        this.card_number = card_number;
    }

    public String getAuth_code ()
    {
        return auth_code;
    }

    public void setAuth_code (String auth_code)
    {
        this.auth_code = auth_code;
    }

    public String getTid ()
    {
        return tid;
    }

    public void setTid (String tid)
    {
        this.tid = tid;
    }

    public String getMid ()
    {
        return mid;
    }

    public void setMid (String mid)
    {
        this.mid = mid;
    }

    public String getCustomerMobileNumber ()
    {
        return customerMobileNumber;
    }

    public void setCustomerMobileNumber (String customerMobileNumber)
    {
        this.customerMobileNumber = customerMobileNumber;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public String getCreditOrDebit ()
    {
        return creditOrDebit;
    }

    public void setCreditOrDebit (String creditOrDebit)
    {
        this.creditOrDebit = creditOrDebit;
    }

    public String getTransaction_id ()
    {
        return transaction_id;
    }

    public void setTransaction_id (String transaction_id)
    {
        this.transaction_id = transaction_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [amount = "+amount+", reference_number = "+reference_number+", card_number = "+card_number+", auth_code = "+auth_code+", tid = "+tid+", mid = "+mid+", customerMobileNumber = "+customerMobileNumber+", date = "+date+", creditOrDebit = "+creditOrDebit+", transaction_id = "+transaction_id+"]";
    }
}
