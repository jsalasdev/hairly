package com.example.jezuz1n.hairly.historial_management;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jezuz1n.hairly.R;
import com.example.jezuz1n.hairly.dating_management.EventClickListener;
import com.example.jezuz1n.hairly.jobs.GetCitasFromClientJob;
import com.example.jezuz1n.hairly.jobs.GetCitasFromShopJob;
import com.example.jezuz1n.hairly.jobs.GetImageJob;
import com.example.jezuz1n.hairly.models.dto.CitaDTO;
import com.example.jezuz1n.hairly.models.dto.ClientDTO;
import com.example.jezuz1n.hairly.session.SessionManager;
import com.example.jezuz1n.hairly.utils.IGetResults;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.h6ah4i.android.widget.advrecyclerview.animator.SwipeDismissItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.SimpleListDividerDecorator;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionDefault;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionMoveToSwipedDirection;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionRemoveItem;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractSwipeableItemViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jezuz1n on 30/05/2017.
 */

public class HistorialManagementFragment extends Fragment implements HistorialManagementFragmentView {

    @BindView(R.id.recycler_view)
    RecyclerView rv;

    @BindView(R.id.tv_error_data_management)
    TextView tvError;

    @BindView(R.id.pb_load_data_management)
    ProgressBar pbLoad;

    HistorialAdapter adapter;

    static HistorialManagementPresenter presenter;

    public HistorialManagementFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        View view = inflater.inflate(R.layout.fragment_dating_management, container, false);
        ButterKnife.bind(this, view);
        presenter = new HistorialManagementPresenterImpl(this);
        showProgressBar();

        try {
            GetCitasFromClientJob job = new GetCitasFromClientJob(new SessionManager(getAppContext()).getUserDetails().get(SessionManager.KEY_UID)
                    , getAppContext()
                    , new IGetResults<ArrayList<CitaDTO>>() {
                @Override
                public void onSuccess(ArrayList<CitaDTO> object) {
                    createRv(object);
                }

                @Override
                public void onFailure(ArrayList<CitaDTO> object) {
                    Log.i("ERROR","no se trae citas");
                }
            });
            job.onRun();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return view;
    }

    public void createRv(ArrayList<CitaDTO> object) {
        RecyclerViewSwipeManager swipeMgr = new RecyclerViewSwipeManager();
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new HistorialAdapter(presenter, object);
        rv.setAdapter(adapter);
        rv.setItemAnimator(new SwipeDismissItemAnimator());
        swipeMgr.attachRecyclerView(rv);
        rv.addItemDecoration(new SimpleListDividerDecorator(ContextCompat.getDrawable(getContext(), R.drawable.list_divider), true));
        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        pbLoad.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pbLoad.setVisibility(View.GONE);
    }

    @Override
    public Context getAppContext() {
        return this.getContext();
    }

}
