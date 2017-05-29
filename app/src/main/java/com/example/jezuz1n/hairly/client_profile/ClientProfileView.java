package com.example.jezuz1n.hairly.client_profile;

import android.content.Context;

import com.example.jezuz1n.hairly.models.dto.ClientDTO;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;

/**
 * Created by jezuz1n on 24/05/2017.
 */

public interface ClientProfileView {

    void showProgressBar();
    void hideProgressBar();
    void setData(ClientDTO shop);
    void showMsg(String msg);
    Context getAppContext();
}
