package com.capstoneglass.capstoneprojectv2.Models;

/**
 * Created by DasCruel on 11/1/2014.
 */
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DasCruel on 11/1/2014.
 */
public class World {
    public long Id;
    public String Name;
    public List<PointOfInterest> PointsOfInterest = new ArrayList<PointOfInterest>();

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public World fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, World.class);
    }
}
