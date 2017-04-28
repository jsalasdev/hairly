package com.example.jezuz1n.hairly.register;

import android.app.Activity;
import android.util.Log;

import com.example.jezuz1n.hairly.models.dto.UserDTO;
import com.example.jezuz1n.hairly.utils.ValidatorUtil;

/**
 * Created by jsalas on 28/4/17.
 */

public class RegisterInteractorImpl implements RegisterInteractor {
    @Override
    public boolean validateEmail(String email) {
        return ValidatorUtil.validateEmail(email);
    }

    @Override
    public boolean validatePassword(String pw) {
        return ValidatorUtil.validatePassword(pw);
    }

    @Override
    public void createAccount(UserDTO user, Activity act, OnRegisterFinishedListener listener) {

        Log.i("ENTRA","Todo bien");

listener.onSuccess();
    }
}
