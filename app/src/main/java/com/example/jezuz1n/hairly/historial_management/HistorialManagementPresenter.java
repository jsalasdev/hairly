package com.example.jezuz1n.hairly.historial_management;

import android.content.Context;

import com.example.jezuz1n.hairly.models.dto.CitaDTO;
import com.example.jezuz1n.hairly.utils.IGetResults;

/**
 * Created by jezuz1n on 30/05/2017.
 */

public interface HistorialManagementPresenter {
    void getData(String uid, IGetResults listener);
    Context getContext();
}
