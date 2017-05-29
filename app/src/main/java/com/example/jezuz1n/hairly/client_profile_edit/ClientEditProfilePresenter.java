package com.example.jezuz1n.hairly.client_profile_edit;

import com.example.jezuz1n.hairly.models.dto.ClientDTO;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.shop_profile_edit.ShopEditProfileView;

/**
 * Created by jezuz1n on 15/05/2017.
 */

public interface ClientEditProfilePresenter {
    void initializeView(ClientEditProfileView act);
    void loadData();
    void updateData(ClientDTO user);
}
