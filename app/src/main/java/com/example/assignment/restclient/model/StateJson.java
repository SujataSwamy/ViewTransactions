package com.example.assignment.restclient.model;

/**
 * Created by sujata on 03/04/17.
 */

public class StateJson
{
    private String id;

    private String state;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", state = "+state+"]";
    }
}
