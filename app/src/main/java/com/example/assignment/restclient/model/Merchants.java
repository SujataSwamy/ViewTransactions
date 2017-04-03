package com.example.assignment.restclient.model;

/**
 * Created by sujata on 02/04/17.
 */
public class Merchants
{
    private String merchant_name;

    private String mid;

    private String merchant_city;

    private String merchant_state;

    public String getMerchant_name ()
    {
        return merchant_name;
    }

    public void setMerchant_name (String merchant_name)
    {
        this.merchant_name = merchant_name;
    }

    public String getMid ()
    {
        return mid;
    }

    public void setMid (String mid)
    {
        this.mid = mid;
    }

    public String getMerchant_city ()
    {
        return merchant_city;
    }

    public void setMerchant_city (String merchant_city)
    {
        this.merchant_city = merchant_city;
    }

    public String getMerchant_state ()
    {
        return merchant_state;
    }

    public void setMerchant_state (String merchant_state)
    {
        this.merchant_state = merchant_state;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [merchant_name = "+merchant_name+", mid = "+mid+", merchant_city = "+merchant_city+", merchant_state = "+merchant_state+"]";
    }
}
