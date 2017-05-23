package com.example.jezuz1n.hairly.models.dto;

/**
 * Created by jezuz1n on 17/05/2017.
 */

public class MarkerCustom {

    private String UID;
    private double longitude, latitude;

    public MarkerCustom() {
    }

    public MarkerCustom(String UID, double longitude, double latitude) {
        this.UID = UID;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
