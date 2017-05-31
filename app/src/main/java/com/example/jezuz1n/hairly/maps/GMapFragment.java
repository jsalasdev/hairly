package com.example.jezuz1n.hairly.maps;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jezuz1n.hairly.R;
import com.example.jezuz1n.hairly.models.dto.ShopMapVO;
import com.example.jezuz1n.hairly.session.SessionManager;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jezuz1n on 15/04/2017.
 */

public class GMapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    SupportMapFragment mFragment;
    private GoogleMap mMap;
    private Bundle b;
    ArrayList<ShopMapVO> list;
    Marker userMark;
    HashMap<Marker, ShopMapVO> diccionario;
    EventClickMap listener;

    public GMapFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_google_map, container, false);

        b = getArguments();

        mFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mFragment.getMapAsync(this);


//        SessionManager sessionManager = new SessionManager(getContext());
//        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
//
//        final HashMap<String, String> user = sessionManager.getUserDetails();

//        database.getRef().child(user.get("uid")).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//                Log.i("cant", String.valueOf(dataSnapshot.getChildrenCount()));
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
        return rootView;
    }

    public void setListener(EventClickMap listener) {
        this.listener = listener;
    }

    public void setupMap() {
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.setOnInfoWindowClickListener(this);

        diccionario = new HashMap<>();

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                ShopMapVO shop = list.get(i);
                LatLng pos = new LatLng(Double.parseDouble(shop.getLatitude()), Double.parseDouble(shop.getLongitude()));
                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_shop);
                userMark = mMap.addMarker(new MarkerOptions().title(shop.getNick()).position(pos).icon(icon).visible(true));
                diccionario.put(userMark, shop);
            }
        }
    }

    public static GMapFragment newInstance(Bundle b) {
        GMapFragment fragment = new GMapFragment();
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (b != null) {
            list = b.getParcelableArrayList("lista");
            setupMap();
        } else {
            mMap.addMarker(new MarkerOptions().position(new LatLng(29.702182, -98.124561)).title("marker"));
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (EventClickMap) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement LogoutUser");
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        ShopMapVO shop = diccionario.get(marker);

//        if (shop != null) {
//            ArrayList<ShopMapVO> listaProv = new ArrayList<>(diccionario.values());
//            int i = 0;
//
//            ShopMapVO aux;
//            do {
//                aux = listaProv.get(i);
//                i++;
//            } while (!(aux.getUid().equals(shop.getUid())) && i < listaProv.size());
//
//        }

        if(shop!=null){
            listener.OnClickItem(shop);
        }


    }
}

