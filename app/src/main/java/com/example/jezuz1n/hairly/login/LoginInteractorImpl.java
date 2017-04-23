package com.example.jezuz1n.hairly.login;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by jesus.salas on 19/04/2017.
 */

public class LoginInteractorImpl implements LoginInteractor{

    FirebaseAuth firebaseAuth;

    @Override
    public void login(final String email,final String password, final OnLoginFinishedListener listener, final Activity act) {

        firebaseAuth = FirebaseAuth.getInstance();

        boolean error = false;

        if(TextUtils.isEmpty(email)){
            listener.onEmailError();
            error = true;
        }

        if(TextUtils.isEmpty(password)){
            listener.onPasswordError();
            error = true;
        }

        if(!error) {
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(act, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //if the task is successfull
                                    if (task.isSuccessful()) {
                                        listener.onSuccess();
                                    }else{
                                        listener.onFailure();
                                    }
                                }
                            });


        }

    }
}
