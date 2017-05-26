package com.example.jezuz1n.hairly.jobs;

import android.content.Context;

import com.example.jezuz1n.hairly.models.dto.ShopDTO;
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

public class GetDataUserJob extends Job {

    Context mContext;
    String uid;
    IGetResults<ShopDTO> listener;

    public GetDataUserJob(Context c, String uid, IGetResults<ShopDTO> listener) {
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

        FirebaseDatabase.getInstance().getReference().child("shops").child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ShopDTO user = dataSnapshot.getValue(ShopDTO.class);
                        listener.onSuccess(user);
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
