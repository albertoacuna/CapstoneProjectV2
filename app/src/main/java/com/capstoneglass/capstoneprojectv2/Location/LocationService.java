package com.capstoneglass.capstoneprojectv2.Location;

/**
 * Created by DasCruel on 11/1/2014.
 */

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.IBinder;

import com.capstoneglass.capstoneprojectv2.Models.*;

import java.util.ArrayList;
import java.util.List;

public class LocationService extends Service {
    private final IBinder mBinder = new LocalBinder();
    private Position positionManager;
    private World world;
    private int radius;
    private List<PointOfInterest> localPOIs;
    private LocationsThread mLocationsThread;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        mLocationsThread = new LocationsThread();

        return START_STICKY;
    }

    public void startLocationService(){
        mLocationsThread.start();
    }
    public void setVariables(Position positionManager, World world, int radius){
        this.positionManager = positionManager;
        this.world = world;
        this.radius = radius;
    }

    @Override
    public void onDestroy(){
        if(mLocationsThread != null){
            mLocationsThread.quit();
        }
        super.onDestroy();
    }

    public class LocalBinder extends Binder {
        LocationService getService(){
            return LocationService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    public List<PointOfInterest> getNearbyPOIs(){
        return localPOIs;
    }
    private void NearbyLocations(){
        localPOIs = new ArrayList<PointOfInterest>();
        Location currentLocation = positionManager.getLocation();

        for(int i =0; i < localPOIs.size(); i++){
            PointOfInterest poi = localPOIs.get(i);
            Location poiLocation = poi.location;
            if(isNearby(currentLocation, poiLocation)){
                localPOIs.add(poi);
            }
        }
    }

    private boolean isNearby(Location currentLocation, Location poiLocation) {
        boolean isNearby;

        double distance = distance(currentLocation, poiLocation);

        if(distance <= radius){
            isNearby = true;
        }
        else{
            isNearby = false;
        }

        return isNearby;
    }

    private double distance(Location currentLocation, Location poiLocation) {
        double lat1 = currentLocation.getLatitude();
        double lon1 = currentLocation.getLongitude();
        double lat2 = poiLocation.getLatitude();
        double lon2 = poiLocation.getLongitude();


        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    private class LocationsThread extends Thread{
        private boolean mRun = false;

        public LocationsThread(){
            mRun = true;
        }

        private synchronized boolean shouldRun(){
            return mRun;
        }

        public synchronized void quit(){
            mRun = false;
        }

        @Override
        public void run(){
            while(shouldRun()){
                NearbyLocations();
            }
        }
    }
}
