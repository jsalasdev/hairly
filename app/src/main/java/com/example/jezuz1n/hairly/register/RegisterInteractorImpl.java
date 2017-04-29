package com.example.jezuz1n.hairly.register;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.jezuz1n.hairly.models.dto.UserDTO;
import com.example.jezuz1n.hairly.utils.ValidatorUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jsalas on 28/4/17.
 */

public class RegisterInteractorImpl implements RegisterInteractor {

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    public boolean validateEmail(String email) {
        return ValidatorUtil.validateEmail(email);
    }

    @Override
    public boolean validatePassword(String pw) {
        return ValidatorUtil.validatePassword(pw);
    }

    @Override
    public void createAccount(final UserDTO user, Activity act, final OnRegisterFinishedListener listener) {

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(act, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            UserDTO mUser = user;
                            mUser.setUid(task.getResult().getUser().getUid());

                            insertDatabase(mUser, new IGetInsertResult() {
                                @Override
                                public void onSuccess() {
                                    listener.onSuccess();
                                }

                                @Override
                                public void onFailure() {

                                }
                            });

                        } else {
                            listener.onFailure();
                        }
                    }
                });
    }

    public void insertDatabase(UserDTO user, IGetInsertResult result){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("clients").child(user.getUid()).setValue(user);
        result.onSuccess();
    }

}
