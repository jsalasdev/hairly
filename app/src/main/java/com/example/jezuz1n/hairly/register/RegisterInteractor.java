package com.example.jezuz1n.hairly.register;

import android.app.Activity;

import com.example.jezuz1n.hairly.models.dto.UserDTO;

/**
 * Created by jsalas on 28/4/17.
 */

public interface RegisterInteractor {
    interface OnRegisterFinishedListener{
        void onEmailError(String msg);
        void onSuccess();
        void onPasswordError(String msg);
        void onFailure();
    }
    boolean validateEmail(String email);
    boolean validatePassword(String pw);
    void createAccount(UserDTO user, Activity act, OnRegisterFinishedListener listener);
}
