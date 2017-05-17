package com.example.jezuz1n.hairly.register;

import com.example.jezuz1n.hairly.R;
import com.example.jezuz1n.hairly.models.dto.UserDTO;
import com.example.jezuz1n.hairly.session.SessionManager;

/**
 * Created by jsalas on 28/4/17.
 */

public class RegisterPresenterImpl implements RegisterPresenter, RegisterInteractor.OnRegisterFinishedListener {

    RegisterView view;
    RegisterInteractor interactor;
    SessionManager sessionManager;

    public RegisterPresenterImpl(RegisterView view){
        this.view = view;
        interactor = new RegisterInteractorImpl();
        sessionManager = new SessionManager(view.getContext());
    }

    @Override
    public void validateAccount(UserDTO user) {
        if(interactor.validateEmail(user.getEmail())){
            if(interactor.validatePassword(user.getPassword())){
                interactor.createAccount(user,view.getActivity(),this);
            }else{
                onPasswordError(view.getActivity().getString(R.string.ERROR_LONG_PASSWORD));
            }
        }else{
            onEmailError(view.getActivity().getString(R.string.ERROR_EMAIL_PATTERN));
        }
    }

    @Override
    public void createSession(UserDTO user) {
        user.setUid(sessionManager.getUserDetails().get(SessionManager.KEY_UID));
        sessionManager.createSession(user);
        view.showDialogProgress();
        view.navigateToIndex();
    }

    @Override
    public void onEmailError(String msg) {
        view.setEmailError(msg);
    }

    @Override
    public void onSuccess() {
        view.onSuccessRegister();
    }

    @Override
    public void onPasswordError(String msg) {
        view.setPasswordError(msg);
    }

    @Override
    public void onFailure() {
        view.setEmailError(view.getActivity().getString(R.string.ERROR_EMAIL_REGISTERED));
    }
}
