package com.example.jezuz1n.hairly.jobs;

import android.content.Context;

import com.example.jezuz1n.hairly.models.dto.CitaDTO;
import com.example.jezuz1n.hairly.utils.IGetResults;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

import java.util.ArrayList;

/**
 * Created by jezuz1n on 30/05/2017.
 */

public class GetCitasFromShopJob extends Job {

    IGetResults<ArrayList<CitaDTO>> listener;
    Context mContext;
    String uid;
    String type;

    public GetCitasFromShopJob(String uid, String type, Context c, IGetResults<ArrayList<CitaDTO>> listener) {
        super(new Params(1).requireNetwork());
        this.uid = uid;
        this.mContext = c;
        this.listener = listener;
        this.type = type;
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        try {
            FirebaseDatabase.getInstance().getReference().child("citas")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            ArrayList<CitaDTO> list = new ArrayList<>();
                            for (DataSnapshot cita : dataSnapshot.getChildren()) {
                                CitaDTO aux = cita.getValue(CitaDTO.class);
                                if (uid.equals(aux.getUIDshop())) {
                                    if (type.equals(CitaDTO.ALL_TYPES)) {
                                        list.add(aux);
                                    } else {
                                        if (aux.getState() != null) {
                                            if (aux.getState().equals(type)) {
                                                list.add(aux);
                                            }
                                        }
                                    }
                                }
                            }

                            if (list.size() > 0) {
                                listener.onSuccess(list);
                            } else {
                                listener.onFailure(null);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            listener.onFailure(null);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCancel() {

    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        return false;
    }
}
