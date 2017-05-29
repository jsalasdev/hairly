package com.example.jezuz1n.hairly.shop_profile_edit;

import android.content.Context;
import android.location.Address;

import com.example.jezuz1n.hairly.jobs.GetDataUserJob;
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

public class ShopEditProfileInteractorImpl implements ShopEditProfileInteractor {

    DatabaseReference mDatabase;
    Context mContext;

    public ShopEditProfileInteractorImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getData(final OnChargeDataFinishedListener listener) {
        HashMap<String, String> ops = new SessionManager(mContext).getUserDetails();
        try {
            GetDataUserJob job = new GetDataUserJob(mContext, ops.get("uid"), new IGetResults<ShopDTO>() {
                @Override
                public void onSuccess(ShopDTO object) {
                    listener.onSuccess(object);
                }

                @Override
                public void onFailure(ShopDTO object) {
                    listener.onFailure();
                }
            });
            job.onRun();
        } catch (Throwable e) {

        }
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
            Address a = LocationUtil.getLocationFromAddress(user.getAddress() + ", " + user.getProvince(), mContext);
            if (a != null) {
                user.setLatitude(String.valueOf(a.getLatitude()));
                user.setLongitude(String.valueOf(a.getLongitude()));
            }
            mDatabase.child("shops").child(user.getUid()).setValue(user);
            result.onSuccess("Usuario actualizado");
        } catch (Exception e) {
            result.onFailure("Error al actualizar usuario");
        }
    }
}
