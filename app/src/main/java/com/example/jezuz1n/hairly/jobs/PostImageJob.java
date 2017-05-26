package com.example.jezuz1n.hairly.jobs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

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

        Uri imageUri = uri;
        Bitmap b = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(),imageUri);

        Bitmap bitmap = PostImageJob.getScaledBitmap(b,150,150);

        UploadTask uploadTask = pathReference.putFile(getImageUri(mContext,bitmap));

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

    public static Bitmap getScaledBitmap(Bitmap bitmap, int width, int height) {
        float bitmapScaleRatioV = (float) bitmap.getHeight() / bitmap.getWidth();
        float bitmapScaleRatioH = (float) bitmap.getWidth() / bitmap.getHeight();

        int maxWidth = width;
        int maxHeight = height;
        int scaledBitmapWidth, scaledBitmapHeight;
        if (bitmap.getHeight() >= bitmap.getWidth()) {
            scaledBitmapWidth = (int) (maxWidth / bitmapScaleRatioV);
            scaledBitmapHeight = maxHeight;
        } else {
            scaledBitmapWidth = maxWidth;
            scaledBitmapHeight = (int) (maxHeight / bitmapScaleRatioH);
        }

        Bitmap bitmapComp = Bitmap.createScaledBitmap(bitmap, scaledBitmapWidth, scaledBitmapHeight, false);

        return bitmapComp;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}
