package com.example.jezuz1n.hairly.login;

import android.content.Context;

/**
 * Created by jesus.salas on 19/04/2017.
 */

public interface LoginView {

    void showProgressBar();
    void hideProgressBar();
    void setEmailError();
    void setPasswordError();
    void setError();
    void hideError();
    void navigateToIndex();
    Context getContext();

}
