package com.example.jezuz1n.hairly.shop_profile_edit;

import com.example.jezuz1n.hairly.models.dto.ShopDTO;

/**
 * Created by jezuz1n on 15/05/2017.
 */

public interface ShopEditProfileInteractor {

    interface OnChargeDataFinishedListener{
        void onSuccess(ShopDTO user);
        void onFailure();
    }

    void getData();
    void setData();
}
