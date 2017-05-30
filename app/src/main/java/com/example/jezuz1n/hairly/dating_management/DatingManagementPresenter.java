package com.example.jezuz1n.hairly.dating_management;

import android.content.Context;

import com.example.jezuz1n.hairly.models.dto.CitaDTO;
import com.example.jezuz1n.hairly.utils.IGetResults;

/**
 * Created by jezuz1n on 30/05/2017.
 */

public interface DatingManagementPresenter {
    void getData(String uid, IGetResults listener);
    void changeState(CitaDTO cita);
    Context getContext();
}
