package com.example.jezuz1n.hairly.login;

/**
 * Created by jesus.salas on 19/04/2017.
 */

public interface LoginInteractor {

    interface OnLoginFinishedListener{
        void onEmailError();
        void onPasswordError();
        void onSuccess();
    }

    public void login(String email, String password, OnLoginFinishedListener listener);

}
