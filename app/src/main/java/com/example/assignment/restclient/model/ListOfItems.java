package com.example.assignment.restclient.model;

/**
 * Created by sujata on 02/04/17.
 */

public class ListOfItems
{
    private Merchants[] merchants;

    private String status;

    public Merchants[] getMerchants ()
    {
        return merchants;
    }

    public void setMerchants (Merchants[] merchants)
    {
        this.merchants = merchants;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [merchants = "+merchants+", status = "+status+"]";
    }
}