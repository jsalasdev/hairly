package com.example.jezuz1n.hairly.client_profile_edit;

import android.net.Uri;

import com.example.jezuz1n.hairly.jobs.GetImageJob;
import com.example.jezuz1n.hairly.models.dto.ClientDTO;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.session.SessionManager;
import com.example.jezuz1n.hairly.shop_profile_edit.ShopEditProfileView;
import com.example.jezuz1n.hairly.utils.IGetResults;

import java.util.HashMap;

/**
 * Created by jezuz1n on 15/05/2017.
 */

public class ClientEditProfilePresenterImpl implements ClientEditProfilePresenter, ClientEditProfileInteractor.OnChargeDataFinishedListener {

    ClientEditProfileView view;
    ClientEditProfileInteractor interactor;
    SessionManager sessionManager;

    public ClientEditProfilePresenterImpl() {
    }

    @Override
    public void initializeView(ClientEditProfileView act) {
        view = act;
        interactor = new ClientEditProfileInteractorImpl(view.getAppContext());
        sessionManager = new SessionManager(view.getAppContext());
        loadData();
    }

    @Override
    public void loadData() {
        view.showProgressBar();
        interactor.getData(this);
    }

    @Override
    public void updateData(ClientDTO user) {

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
    public void onSuccess(final ClientDTO user) {
        try {
            GetImageJob job = new GetImageJob(user.getUid(), view.getAppContext(), new IGetResults<Uri>() {
                @Override
                public void onSuccess(Uri object) {
                    user.setPhotoURL(object);
                    view.setData(user);
                }

                @Override
                public void onFailure(Uri object) {
                    if(object==null){
                        view.setData(user);
                    }
                }
            });
            job.onRun();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Override
    public void onFailure() {

    }
}
