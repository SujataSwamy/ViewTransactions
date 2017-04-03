package com.example.assignment.restclient.model;

import com.example.assignment.interfaces.ILocation;

import java.util.Date;

/**
 * Created by sujata on 03/04/17.
 */

public class Location  implements ILocation,java.io.Serializable,Comparable<Location>{

    String id;
    String name;
    String slug;
    String cityId;
    String city;
    String citySlug;
    String pincode;
    String latitude;
    String longitude;
    Date date;
    private String address_str;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String getCitySlug() {
        return citySlug;
    }

    @Override
    public String getSlug() {
        return slug;
    }

    @Override
    public String getCityId() {
        return cityId;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getPincode() {
        return pincode;
    }

    @Override
    public String getLatitude() {
        return latitude;
    }

    @Override
    public String getLongitude() {
        return longitude;
    }

    @Override
    public String getAddress() {
        return address_str;
    }

    @Override
    public String toString() {
        if(city== null || (id!=null && id.contentEquals("1113")))
            return name;
        else
            return name + ","+" " +city;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCitySlug(String citySlug) {
        this.citySlug = citySlug;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public int compareTo(Location location) {
        return location.getDate().compareTo(getDate());
    }

    public void setAddress(String address_str) {

        this.address_str = address_str;
    }
}
