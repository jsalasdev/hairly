package com.example.jezuz1n.hairly.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jezuz1n.hairly.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by jezuz1n on 15/04/2017.
 */

public class GMapFragment extends Fragment implements OnMapReadyCallback {

    SupportMapFragment mFragment;
    private GoogleMap mMap;

    public GMapFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.fragment_google_map, container, false);
        mFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mFragment.getMapAsync(this);
        return rootView;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    mMap.addMarker(new MarkerOptions().position(new LatLng(29.702182, -98.124561)).title("marker"));

    }
}

