package com.example.jezuz1n.hairly.shop_profile_edit;

import android.app.Activity;

import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.session.SessionManager;
import com.example.jezuz1n.hairly.utils.IGetResults;

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
    public void onSuccess(ShopDTO user) {
        view.setData(user);
    }

    @Override
    public void onFailure() {

    }
}
