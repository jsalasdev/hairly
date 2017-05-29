package com.example.jezuz1n.hairly.client_profile;

import com.example.jezuz1n.hairly.models.dto.CitaDTO;
import com.example.jezuz1n.hairly.models.dto.ClientDTO;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;

/**
 * Created by jezuz1n on 24/05/2017.
 */

public interface ClientProfileInteractor {

    interface OnChargeDataFinishedListener {
        void onSuccess(ClientDTO user);
        void onFailure();
    }

    void getData(OnChargeDataFinishedListener listener);
    void getData(String uid, OnChargeDataFinishedListener listener);

}
