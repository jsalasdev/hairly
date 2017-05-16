package com.example.jezuz1n.hairly.shop_profile_edit;

import android.content.Context;

import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.session.SessionManager;
import com.example.jezuz1n.hairly.utils.IGetResults;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * Created by jezuz1n on 15/05/2017.
 */

public class ShopEditProfileInteractorImpl implements ShopEditProfileInteractor {

    DatabaseReference mDatabase;

    @Override
    public void getData(Context mContext, final OnChargeDataFinishedListener listener) {
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
    public void setData(ShopDTO shop, final IGetResults results) {

        insertDatabase(shop, new IGetResults() {
            @Override
            public void onSuccess(Object object) {
                results.onSuccess(object);
            }

            @Override
            public void onFailure(Object object) {
                results.onFailure(object);
            }
        });

    }

    public void insertDatabase(ShopDTO user, IGetResults result) {
        try {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("shops").child(user.getUid()).setValue(user);
            result.onSuccess("Usuario actualizado");
        } catch (Exception e) {
            result.onFailure("Error al actualizar usuario");
        }
    }
}
