package com.example.jezuz1n.hairly.register;

import android.app.Activity;
import android.content.Context;

/**
 * Created by jsalas on 28/4/17.
 */

public interface RegisterView {

    void setPasswordError(String msg);
    void setEmailError(String msg);
    void showError();
    void hideError();
    void showDialogProgress();
    void hideDialogProgress();
    void validateData();
    void navigateToIndex();
    void onSuccessRegister();
    Context getContext();
    Activity getActivity();

}
