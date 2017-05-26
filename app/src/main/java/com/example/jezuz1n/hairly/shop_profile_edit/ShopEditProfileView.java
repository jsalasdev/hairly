package com.example.jezuz1n.hairly.shop_profile_edit;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.jezuz1n.hairly.models.dto.ShopDTO;

/**
 * Created by jezuz1n on 15/05/2017.
 */

public interface ShopEditProfileView {

    void showProgressBar();

    void hideProgressBar();

    void navigateToIndex();

    Context getAppContext();

    void setData(ShopDTO user);

    void showMsg(String msg);

    Bitmap getImage();

    void setProfileImg(String img);

}
