package com.example.jezuz1n.hairly.login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.jezuz1n.hairly.models.dto.UserDTO;

/**
 * Created by jesus.salas on 19/04/2017.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;
    private SessionManager sessionManager;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView;
        //Inyectar con Dagger
        loginInteractor = new LoginInteractorImpl();
        initSessionManager();

    }

    public void initSessionManager(){
        sessionManager = new SessionManager(loginView.getContext());
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
    public void onSuccess(UserDTO user) {
        if(loginView!=null){
            createSession(user);
            loginView.navigateToIndex();
            loginView.hideProgressBar();
            loginView.hideError();
        }
    }

    public void createSession(UserDTO user) {
        sessionManager.createSession(user);
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
