package com.example.jezuz1n.hairly.shop_profile;

import android.content.Context;

import com.example.jezuz1n.hairly.models.dto.ShopDTO;

/**
 * Created by jezuz1n on 24/05/2017.
 */

public interface ShopProfileView {

    void showProgressBar();
    void hideProgressBar();
    void setData(ShopDTO shop);
    void showMsg(String msg);
    Context getAppContext();
}
