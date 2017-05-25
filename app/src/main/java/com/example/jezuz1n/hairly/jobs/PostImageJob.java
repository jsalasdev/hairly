package com.example.jezuz1n.hairly.jobs;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.jezuz1n.hairly.session.SessionManager;
import com.example.jezuz1n.hairly.utils.IGetResults;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

/**
 * Created by jezuz1n on 25/05/2017.
 */

public class PostImageJob extends Job {

    IGetResults<Uri> listener;
    Context mContext;
    Uri uri;

    public PostImageJob(Uri uri, Context c, IGetResults<Uri> listener){
        super(new Params(1).requireNetwork().persist());
        this.uri = uri;
        this.mContext = c;
        this.listener = listener;
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        String uid = new SessionManager(mContext).getUserDetails().get(SessionManager.KEY_UID);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference pathReference = storage.getReferenceFromUrl("gs://hairly-99fc1.appspot.com").child("shops").child("profiles").child(uid + ".png");

        UploadTask uploadTask = pathReference.putFile(uri);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                listener.onSuccess(taskSnapshot.getDownloadUrl());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("dasda","dasdas");
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
