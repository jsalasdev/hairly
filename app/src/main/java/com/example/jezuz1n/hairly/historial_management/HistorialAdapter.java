package com.example.jezuz1n.hairly.historial_management;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jezuz1n.hairly.R;
import com.example.jezuz1n.hairly.jobs.GetImageJob;
import com.example.jezuz1n.hairly.models.dto.CitaDTO;
import com.example.jezuz1n.hairly.models.dto.ClientDTO;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.utils.IGetResults;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jezuz1n on 31/05/2017.
 */

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.CitaViewHolder> {

    private ArrayList<CitaDTO> list;
    private HistorialManagementPresenter presenter;

    public HistorialAdapter(HistorialManagementPresenter presenter,ArrayList<CitaDTO> list) {
        this.list = list;
        Collections.reverse(list);
        this.presenter = presenter;
    }

    @Override
    public CitaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dating_management, parent, false);
        return new CitaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CitaViewHolder holder, int position) {
        final CitaDTO item = list.get(position);
        final String minutes;

        Log.i("ESTADO",item.getState());
        switch(item.getState()){
            case "confirmed":
                item.setState("Confirmada");
                break;
            case "deleted":
                item.setState("Rechazada");
                break;
            case "running":
                item.setState("En espera...");
                break;
        }

        if(String.valueOf(item.getMinute()).length()<2){
            minutes = "0"+String.valueOf(item.getMinute());
        }else{
            minutes = String.valueOf(item.getMinute());
        }
        presenter.getData(item.getUIDshop(), new IGetResults() {
            @Override
            public void onSuccess(Object object) {
                final ShopDTO user = (ShopDTO) object;
                try {
                    GetImageJob job = new GetImageJob(user.getUid(), presenter.getContext(), new IGetResults<Uri>() {
                        @Override
                        public void onSuccess(Uri object) {
                            user.setPhotoURL(object);
                            if (user.getNick() != null && user.getPhotoURL()!=null) {
                                holder.photo.setImageURI(user.getPhotoURL());
                                holder.nick.setText(user.getNick());
                                holder.date.setText(item.getDay()+"-"+item.getMonth()+"-"+item.getYear()+" "+item.getHour()+":"+minutes);
                                holder.state.setText(item.getState());
                            }
                        }

                        @Override
                        public void onFailure(Uri object) {
                            holder.nick.setText(user.getNick());
                            holder.date.setText(item.getDay()+"-"+item.getMonth()+"-"+item.getYear()+" "+item.getHour()+":"+minutes);
                            holder.state.setText(item.getState());
                        }
                    });
                    job.onRun();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }

            }
            @Override
            public void onFailure(Object object) {

            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CitaViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nick_item)
        public TextView nick;

        @BindView(R.id.date_item)
        public TextView date;

        @BindView(R.id.state_item)
        public TextView state;

        @BindView(R.id.sdvItem)
        SimpleDraweeView photo;


        public CitaViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

}
