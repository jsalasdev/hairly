package com.example.jezuz1n.hairly.jobs;

import android.content.Context;
import android.util.Log;

import com.example.jezuz1n.hairly.mappers.Mapper;
import com.example.jezuz1n.hairly.models.dto.CitaDTO;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.models.dto.ShopMapVO;
import com.example.jezuz1n.hairly.utils.IGetResults;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

import java.util.ArrayList;

/**
 * Created by jezuz1n on 31/05/2017.
 */

public class GetMarkersShopJob extends Job {

    IGetResults<ArrayList<ShopMapVO>> listener;
    Context mContext;

    public GetMarkersShopJob(Context c, IGetResults<ArrayList<ShopMapVO>> listener) {
        super(new Params(1).requireNetwork().persist());
        this.mContext = c;
        this.listener = listener;
    }

    @Override
    public void onAdded() {
        Log.i("AGREGADO","dasdas");
    }

    @Override
    public void onRun() throws Throwable {

        try {
            FirebaseDatabase.getInstance().getReference().child("shops")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            ArrayList<ShopMapVO> list = new ArrayList<>();
                            for (DataSnapshot cita : dataSnapshot.getChildren()) {
                                ShopDTO aux = cita.getValue(ShopDTO.class);
                                ShopMapVO vo = Mapper.mapToVOfromDTO(aux);
                                if(vo.getLatitude()!=null && vo.getLongitude()!=null){
                                    list.add(Mapper.mapToVOfromDTO(aux));
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
