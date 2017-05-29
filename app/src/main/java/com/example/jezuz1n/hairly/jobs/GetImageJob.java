package com.example.jezuz1n.hairly.jobs;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.jezuz1n.hairly.utils.IGetResults;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

/**
 * Created by jezuz1n on 25/05/2017.
 */

public class GetImageJob extends Job {

    IGetResults<Uri> listener;
    Context mContext;
    String uid;

    public GetImageJob(String uid, Context c, IGetResults<Uri> listener) {
        super(new Params(1).requireNetwork().persist());
        this.mContext = c;
        this.listener = listener;
        this.uid = uid;
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        StorageReference storage = FirebaseStorage.getInstance().getReference();
        storage.child("shops").child("profiles").child(uid+".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                listener.onSuccess(uri);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
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
