package com.example.jezuz1n.hairly.shop_profile;

import com.example.jezuz1n.hairly.models.dto.CitaDTO;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.shop_profile_edit.ShopEditProfileInteractor;

/**
 * Created by jezuz1n on 24/05/2017.
 */

public interface ShopProfileInteractor {

    interface OnChargeDataFinishedListener {
        void onSuccess(ShopDTO user);
        void onFailure();
    }

    interface OnUploadCitaFinishedListener{
        void onSuccess(String msg);
        void onFailure();
    }

    void getData(OnChargeDataFinishedListener listener);
    void uploadCita(CitaDTO cita, OnUploadCitaFinishedListener listener);

}
