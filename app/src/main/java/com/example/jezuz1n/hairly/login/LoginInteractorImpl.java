package com.example.jezuz1n.hairly.login;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.jezuz1n.hairly.models.dto.UserDTO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by jesus.salas on 19/04/2017.
 */

public class LoginInteractorImpl implements LoginInteractor {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String type;

    @Override
    public void login(final String email, final String password, final OnLoginFinishedListener listener, final Activity act) {

        firebaseAuth = FirebaseAuth.getInstance();

        boolean error = false;

        if (TextUtils.isEmpty(email)) {
            listener.onEmailError();
            error = true;
        }

        if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
            error = true;
        }

        if (!error) {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(act, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = firebaseUser.getUid();

                                final UserDTO user = new UserDTO();
                                user.setUid(uid);
                                user.setEmail(email);
                                user.setPassword(password);

                                queryType(uid, new IGetTypeResult() {
                                    @Override
                                    public void onSuccess(String type) {
                                        user.setType(type);
                                        listener.onSuccess(user);
                                    }

                                    @Override
                                    public void onFailure() {
                                        listener.onFailure();
                                    }
                                });
                            } else {
                                listener.onFailure();
                            }
                        }
                    });
        }
    }

    public void queryType(final String uid, final IGetTypeResult result){

        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference().child("clients").child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        UserDTO user = dataSnapshot.getValue(UserDTO.class);
                        if(user!=null){
                            type = user.getType();
                            result.onSuccess(type);
                        }else{
                            firebaseDatabase.getReference().child("shops").child(uid)
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            UserDTO user = dataSnapshot.getValue(UserDTO.class);
                                            type = user.getType();
                                            result.onSuccess(type);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        result.onFailure();
                    }
                });
    }

}
