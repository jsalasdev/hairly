package com.example.jezuz1n.hairly.client_profile_edit;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.jezuz1n.hairly.models.dto.ClientDTO;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;

/**
 * Created by jezuz1n on 15/05/2017.
 */

public interface ClientEditProfileView {

    void showProgressBar();

    void hideProgressBar();

    void navigateToIndex();

    Context getAppContext();

    void setData(ClientDTO user);

    void showMsg(String msg);

    Bitmap getImage();

    void setProfileImg(String img);

}
