package com.example.jezuz1n.hairly.login;

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

}
