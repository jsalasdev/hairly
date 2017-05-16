package com.example.jezuz1n.hairly.shop_profile_edit;

import android.content.Context;

import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.utils.IGetResults;

/**
 * Created by jezuz1n on 15/05/2017.
 */

public interface ShopEditProfileInteractor {

    interface OnChargeDataFinishedListener{
        void onSuccess(ShopDTO user);
        void onFailure();
    }

    void getData(Context mContext, OnChargeDataFinishedListener listener);
    void setData(ShopDTO shop, IGetResults result);
}
