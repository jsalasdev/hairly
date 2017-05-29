package com.example.jezuz1n.hairly.client_profile;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.jezuz1n.hairly.models.dto.CitaDTO;
import com.example.jezuz1n.hairly.models.dto.ClientDTO;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by jezuz1n on 24/05/2017.
 */

public class ClientProfilePresenterImpl implements ClientProfilePresenter {

    ClientProfileInteractor interactor;
    ClientProfileView view;

    public ClientProfilePresenterImpl(ClientProfileView act) {
        view = act;
        interactor = new ClientProfileInteractorImpl(view.getAppContext());
    }

    @Override
    public void loadData(String uid) {
        view.showProgressBar();
        interactor.getData(uid, new ClientProfileInteractor.OnChargeDataFinishedListener() {
            @Override
            public void onSuccess(ClientDTO user) {
                getPhoto(user);
            }

            @Override
            public void onFailure() {
                view.showMsg("Error al cargar los datos.");
            }
        });
    }

    @Override
    public void loadData() {
            view.showProgressBar();
            interactor.getData(new ClientProfileInteractor.OnChargeDataFinishedListener() {
                @Override
                public void onSuccess(ClientDTO user) {
                    getPhoto(user);
                }

                @Override
                public void onFailure() {
                    view.showMsg("Error al cargar los datos.");
                }
            });
    }

    public void getPhoto(final ClientDTO user){
        StorageReference storage = FirebaseStorage.getInstance().getReference();
        storage.child("clients").child("profiles").child(user.getUid()+".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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

}
