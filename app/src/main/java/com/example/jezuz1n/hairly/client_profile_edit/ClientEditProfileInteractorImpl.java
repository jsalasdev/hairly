package com.example.jezuz1n.hairly.client_profile_edit;

import android.content.Context;
import android.location.Address;

import com.example.jezuz1n.hairly.jobs.GetDataUserJob;
import com.example.jezuz1n.hairly.models.dto.ClientDTO;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.session.SessionManager;
import com.example.jezuz1n.hairly.utils.IGetResults;
import com.example.jezuz1n.hairly.utils.LocationUtil;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * Created by jezuz1n on 15/05/2017.
 */

public class ClientEditProfileInteractorImpl<T> implements ClientEditProfileInteractor{

    DatabaseReference mDatabase;
    Context mContext;

    public ClientEditProfileInteractorImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getData(final OnChargeDataFinishedListener listener) {
        HashMap<String, String> ops = new SessionManager(mContext).getUserDetails();
        try {
            GetDataUserJob job = new GetDataUserJob(mContext, ops.get("uid"), new IGetResults<T>() {
                @Override
                public void onSuccess(T object) {
                    listener.onSuccess((ClientDTO)object);
                }

                @Override
                public void onFailure(T object) {
                    listener.onFailure();
                }
            });
            job.onRun();
        } catch (Throwable e) {

        }
    }

    @Override
    public void setData(ClientDTO client, final IGetResults results) {

        insertDatabase(client, new IGetResults() {
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

    public void insertDatabase(ClientDTO user, IGetResults result) {
        try {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("clients").child(user.getUid()).setValue(user);
            result.onSuccess("Usuario actualizado");
        } catch (Exception e) {
            result.onFailure("Error al actualizar usuario");
        }
    }

}
