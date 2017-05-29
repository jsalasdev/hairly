package com.example.jezuz1n.hairly.client_profile_edit;

import com.example.jezuz1n.hairly.models.dto.ClientDTO;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.utils.IGetResults;

/**
 * Created by jezuz1n on 15/05/2017.
 */

public interface ClientEditProfileInteractor {

    interface OnChargeDataFinishedListener{
        void onSuccess(ClientDTO user);
        void onFailure();
    }

    void getData(OnChargeDataFinishedListener listener);
    void setData(ClientDTO client, IGetResults result);
}
