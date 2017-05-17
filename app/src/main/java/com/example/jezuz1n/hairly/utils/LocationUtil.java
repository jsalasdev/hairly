package com.example.jezuz1n.hairly.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.util.List;

public class LocationUtil {

    public static Address getLocationFromAddress(String strAddress, Context mContext) {
        Geocoder coder = new Geocoder(mContext);
        List<Address> address;
        Address location = null;
        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            location = address.get(0);
            location.getLatitude();
            location.getLongitude();
        } catch (Exception e) {

        }
        return location;
    }


}
