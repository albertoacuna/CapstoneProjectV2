package com.capstoneglass.backend.Models;

/**
 * Created by DasCruel on 11/3/2014.
 */
import com.google.gson.Gson;
import java.awt.Point;

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

