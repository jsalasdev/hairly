package com.example.jezuz1n.hairly.login;

import android.app.Activity;
import android.content.Context;

/**
 * Created by jesus.salas on 19/04/2017.
 */

public interface LoginInteractor {

    interface OnLoginFinishedListener{
        void onEmailError();
        void onPasswordError();
        void onSuccess(String uid);
        void onFailure();
    }

    public void login(String email, String password, OnLoginFinishedListener listener, Activity act);

}
