package com.example.jezuz1n.hairly.login;

import android.app.Activity;
import android.util.Log;

/**
 * Created by jesus.salas on 19/04/2017.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView;
        //Inyectar LoginInteractor con Dagger
        loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void onEmailError() {
        if(loginView!=null){
            loginView.setEmailError();
            loginView.hideProgressBar();
        }
    }

    @Override
    public void onPasswordError() {
        if(loginView!=null){
            loginView.setPasswordError();
            loginView.hideProgressBar();
        }
    }

    @Override
    public void onSuccess() {
        if(loginView!=null){
            loginView.navigateToIndex();
            loginView.hideProgressBar();
            loginView.hideError();
        }
    }

    @Override
    public void onFailure() {
        loginView.setError();
        loginView.hideProgressBar();
    }

    @Override
    public void validateCredentials(String email, String password,Activity act) {
        if(loginView!=null){
            loginView.showProgressBar();
        }
        loginInteractor.login(email,password,this,act);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }
}
