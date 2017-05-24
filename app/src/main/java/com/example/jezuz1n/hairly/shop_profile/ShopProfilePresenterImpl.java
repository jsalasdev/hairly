package com.example.jezuz1n.hairly.shop_profile;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.jezuz1n.hairly.models.dto.CitaDTO;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by jezuz1n on 24/05/2017.
 */

public class ShopProfilePresenterImpl implements ShopProfilePresenter {

    ShopProfileInteractor interactor;
    ShopProfileView view;

    public ShopProfilePresenterImpl(ShopProfileView act) {
        view = act;
        interactor = new ShopProfileInteractorImpl(view.getAppContext());
    }

    @Override
    public void loadData() {
            view.showProgressBar();
            interactor.getData(new ShopProfileInteractor.OnChargeDataFinishedListener() {
                @Override
                public void onSuccess(ShopDTO user) {
                    getPhoto(user);
                }

                @Override
                public void onFailure() {
                    view.showMsg("Error al cargar los datos.");
                }
            });
    }

    public void getPhoto(final ShopDTO user){
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
    public void setCita(CitaDTO cita) {
        interactor.uploadCita(cita, new ShopProfileInteractor.OnUploadCitaFinishedListener() {
            @Override
            public void onSuccess(String msg) {
                view.showMsg(msg);
            }

            @Override
            public void onFailure() {

            }
        });
    }



}
