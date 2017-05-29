package com.example.jezuz1n.hairly.jobs;

import android.content.Context;

import com.example.jezuz1n.hairly.models.dto.ClientDTO;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.models.dto.UserDTO;
import com.example.jezuz1n.hairly.session.SessionManager;
import com.example.jezuz1n.hairly.utils.IGetResults;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

/**
 * Created by jezuz1n on 25/05/2017.
 */

public class GetDataUserJob<T> extends Job {

    Context mContext;
    String uid;
    IGetResults<T> listener;

    public GetDataUserJob(Context c, String uid, IGetResults<T> listener) {
        super(new Params(1).requireNetwork().persist());
        this.mContext = c;
        this.uid = uid;
        this.listener = listener;
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        final String cad;

        if (new SessionManager(mContext).getUserDetails().get(SessionManager.KEY_TYPE).equalsIgnoreCase("shops")) {
            cad = "shops";
        } else {
            cad = "clients";
        }

        FirebaseDatabase.getInstance().getReference().child(cad).child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        UserDTO user;
                        if(cad.equalsIgnoreCase("shops")){
                            user = dataSnapshot.getValue(ShopDTO.class);
                        }else{
                            user = dataSnapshot.getValue(ClientDTO.class);
                        }
                        listener.onSuccess((T) user);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        listener.onFailure(null);
                    }
                });
    }

    @Override
    protected void onCancel() {

    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        return false;
    }
}
