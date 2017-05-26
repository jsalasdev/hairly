package com.example.jezuz1n.hairly.shop_profile;

import android.content.Context;

import com.example.jezuz1n.hairly.models.dto.CitaDTO;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.session.SessionManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * Created by jezuz1n on 24/05/2017.
 */

public class ShopProfileInteractorImpl implements ShopProfileInteractor {


    DatabaseReference mDatabase;
    Context mContext;

    public ShopProfileInteractorImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getData(final ShopProfileInteractor.OnChargeDataFinishedListener listener) {
        HashMap<String, String> ops = new SessionManager(mContext).getUserDetails();
        FirebaseDatabase.getInstance().getReference().child("shops").child(ops.get("uid"))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ShopDTO user = dataSnapshot.getValue(ShopDTO.class);
                        listener.onSuccess(user);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        listener.onFailure();
                    }
                });
    }

    @Override
    public void getData(String uid,final OnChargeDataFinishedListener listener) {
        FirebaseDatabase.getInstance().getReference().child("shops").child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ShopDTO user = dataSnapshot.getValue(ShopDTO.class);
                        listener.onSuccess(user);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        listener.onFailure();
                    }
                });
    }

    @Override
    public void uploadCita(CitaDTO cita, OnUploadCitaFinishedListener listener) {
        try {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            if (cita != null) {
                mDatabase.child("citas").setValue(cita);
                listener.onSuccess("Su cita fue enviada correctamente.");
            } else {
                listener.onFailure();
            }
        } catch (Exception e) {
            listener.onFailure();
        }
    }
}
