package com.example.assignment.restclient.model;

/**
 * Created by sujata on 02/04/17.
 */

public class DetailsListOfItems
{
    private Transactions[] transactions;

    private String status;

    public Transactions[] getTransactions ()
    {
        return transactions;
    }

    public void setTransactions (Transactions[] transactions)
    {
        this.transactions = transactions;
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
        return "ClassPojo [transactions = "+transactions+", status = "+status+"]";
    }
}

