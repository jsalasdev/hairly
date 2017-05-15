package com.example.jezuz1n.hairly.shop_profile_edit;

import android.app.Activity;

import com.example.jezuz1n.hairly.models.dto.ShopDTO;

/**
 * Created by jezuz1n on 15/05/2017.
 */

public class ShopEditProfilePresenterImpl implements ShopEditProfilePresenter, ShopEditProfileInteractor.OnChargeDataFinishedListener {

    ShopEditProfileView view;

    public ShopEditProfilePresenterImpl(ShopEditProfileView view){
        this.view = view;
    }

    @Override
    public void initializeView(Activity act) {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void onSuccess(ShopDTO user) {

    }

    @Override
    public void onFailure() {

    }
}
