package com.example.jezuz1n.hairly.maps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jezuz1n.hairly.R;
import com.example.jezuz1n.hairly.login.SessionManager;
import com.example.jezuz1n.hairly.models.dto.UserDTO;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

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


        SessionManager sessionManager = new SessionManager(getContext());
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        final HashMap<String,String> user = sessionManager.getUserDetails();

        database.getRef().child(user.get("uid")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                Log.i("cant",String.valueOf(dataSnapshot.getChildrenCount()));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rootView;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    mMap.addMarker(new MarkerOptions().position(new LatLng(29.702182, -98.124561)).title("marker"));

    }
}

