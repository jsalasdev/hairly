package com.example.jezuz1n.hairly.dating_management;

import android.content.Context;

import com.example.jezuz1n.hairly.jobs.GetDataUserJob;
import com.example.jezuz1n.hairly.models.dto.CitaDTO;
import com.example.jezuz1n.hairly.models.dto.ClientDTO;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.models.dto.UserDTO;
import com.example.jezuz1n.hairly.session.SessionManager;
import com.example.jezuz1n.hairly.utils.IGetResults;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * Created by jezuz1n on 30/05/2017.
 */

public class DatingManagementPresenterImpl implements DatingManagementPresenter{

    DatingManagementFragmentView view;

    public DatingManagementPresenterImpl(DatingManagementFragmentView view){
        this.view = view;
    }

    @Override
    public void getData(String uid, final IGetResults listener) {
        FirebaseDatabase.getInstance().getReference().child("clients").child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        UserDTO user = dataSnapshot.getValue(ClientDTO.class);
                        listener.onSuccess(user);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        listener.onFailure(null);
                    }
                });
    }

    @Override
    public void changeState(CitaDTO cita) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        if (cita != null) {
            mDatabase.child("shops").child(cita.getUIDshop()).child("citas").child(Long.toString(cita.getTimeStamp())).setValue(cita);
            mDatabase.child("clients").child(cita.getUIDclient()).child("citas").child(Long.toString(cita.getTimeStamp())).setValue(cita);
        }
    }

    @Override
    public Context getContext() {
        return view.getAppContext();
    }

}
