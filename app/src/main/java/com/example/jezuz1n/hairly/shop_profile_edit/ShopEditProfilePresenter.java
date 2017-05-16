package com.example.jezuz1n.hairly.shop_profile_edit;

import android.app.Activity;

import com.example.jezuz1n.hairly.models.dto.ShopDTO;

/**
 * Created by jezuz1n on 15/05/2017.
 */

public interface ShopEditProfilePresenter {
    void initializeView(ShopEditProfileView act);
    void loadData();
    void updateData(ShopDTO user);
}
