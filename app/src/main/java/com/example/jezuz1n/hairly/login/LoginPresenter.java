package com.example.jezuz1n.hairly.login;

/**
 * Created by jesus.salas on 19/04/2017.
 */

public interface LoginPresenter {

    void validateCredentials(String email, String password);
    void onDestroy();

}
