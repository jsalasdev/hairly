package com.example.jezuz1n.hairly.shop_profile_edit;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.session.SessionManager;
import com.example.jezuz1n.hairly.utils.IGetResults;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

/**
 * Created by jezuz1n on 15/05/2017.
 */

public class ShopEditProfilePresenterImpl implements ShopEditProfilePresenter, ShopEditProfileInteractor.OnChargeDataFinishedListener {

    ShopEditProfileView view;
    ShopEditProfileInteractor interactor;
    SessionManager sessionManager;

    public ShopEditProfilePresenterImpl(){
    }

    @Override
    public void initializeView(ShopEditProfileView act) {
        view = act;
        interactor = new ShopEditProfileInteractorImpl(view.getAppContext());
        sessionManager = new SessionManager(view.getAppContext());
        loadData();
    }

    @Override
    public void loadData() {
        view.showProgressBar();
        interactor.getData(this);
    }

    @Override
    public void updateData(ShopDTO user) {

        HashMap<String, String> session = sessionManager.getUserDetails();
        user.setUid(session.get("uid"));
        user.setPassword(session.get("password"));
        user.setType(session.get("type"));
        user.setEmail(session.get("email"));
        user.setFirstConnection(Boolean.parseBoolean(session.get("firstConnection")));

        interactor.setData(user, new IGetResults() {
            @Override
            public void onSuccess(Object object) {
                view.showMsg(object.toString());
            }

            @Override
            public void onFailure(Object object) {
                view.showMsg(object.toString());
            }
        });
    }

    @Override
    public void onSuccess(final ShopDTO user) {
        StorageReference storage = FirebaseStorage.getInstance().getReference();
        storage.child("shops").child("profiles").child(user.getUid()+".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                user.setPhotoURL(uri);
                view.setData(user);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                view.setData(user);
            }
        });
    }

    @Override
    public void onFailure() {

    }
}
