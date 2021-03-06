package com.capstoneglass.capstoneprojectv2.Models;

/**
 * Created by DasCruel on 11/1/2014.
 */
import android.location.Location;

import com.google.gson.Gson;

/**
 * Created by DasCruel on 11/1/2014.
 */
public class PointOfInterest {
    public long Id;
    public String Name;
    public double Latitude;
    public double Longitude;

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public PointOfInterest fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, PointOfInterest.class);
    }
}
