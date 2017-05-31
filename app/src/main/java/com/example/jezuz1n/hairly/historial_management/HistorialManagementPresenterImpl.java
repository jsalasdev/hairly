package com.example.jezuz1n.hairly.historial_management;

import android.content.Context;

import com.example.jezuz1n.hairly.models.dto.CitaDTO;
import com.example.jezuz1n.hairly.models.dto.ClientDTO;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.models.dto.UserDTO;
import com.example.jezuz1n.hairly.utils.IGetResults;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by jezuz1n on 30/05/2017.
 */

public class HistorialManagementPresenterImpl implements HistorialManagementPresenter {

    HistorialManagementFragmentView view;

    public HistorialManagementPresenterImpl(HistorialManagementFragmentView view){
        this.view = view;
    }

    @Override
    public void getData(String uid, final IGetResults listener) {
        FirebaseDatabase.getInstance().getReference().child("shops").child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        UserDTO user = dataSnapshot.getValue(ShopDTO.class);
                        listener.onSuccess(user);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        listener.onFailure(null);
                    }
                });
    }

    @Override
    public Context getContext() {
        return view.getAppContext();
    }

}
