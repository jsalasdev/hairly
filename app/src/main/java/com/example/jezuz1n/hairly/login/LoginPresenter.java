package com.example.jezuz1n.hairly.login;

import android.app.Activity;

/**
 * Created by jesus.salas on 19/04/2017.
 */

public interface LoginPresenter {

    void validateCredentials(String email, String password, Activity act);
    void onDestroy();

}
