package com.example.jezuz1n.hairly.dating_management;

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

public class DatingManagementFragment extends Fragment implements DatingManagementFragmentView, EventClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView rv;

    @BindView(R.id.tv_error_data_management)
    TextView tvError;

    @BindView(R.id.pb_load_data_management)
    ProgressBar pbLoad;

    Adapter adapter;

    static DatingManagementPresenter presenter;

    static final float OPTIONS_AREA_PROPORTION = 0.5f;
    static final float REMOVE_ITEM_THRESHOLD = 0.6f;

    public DatingManagementFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        View view = inflater.inflate(R.layout.fragment_dating_management, container, false);
        ButterKnife.bind(this, view);
        presenter = new DatingManagementPresenterImpl(this);
        showProgressBar();

        String uid = new SessionManager(getContext()).getUserDetails().get(SessionManager.KEY_UID);
        try {
            GetCitasFromShopJob job = new GetCitasFromShopJob(uid, getContext(), new IGetResults<ArrayList<CitaDTO>>() {
                @Override
                public void onSuccess(ArrayList<CitaDTO> object) {
                    createRv(object);
                }

                @Override
                public void onFailure(ArrayList<CitaDTO> object) {
                    Log.i("Error","No hay citas");
                    tvError.setVisibility(View.VISIBLE);
                    hideProgressBar();
                }
            });
            job.onRun();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return view;
    }

    public void createRv(ArrayList<CitaDTO> object){
        RecyclerViewSwipeManager swipeMgr = new RecyclerViewSwipeManager();
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new Adapter(getContext(),object,this);
        rv.setAdapter(swipeMgr.createWrappedAdapter(adapter));
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

    @Override
    public void onClick(View v, int position, int type) {
       CitaDTO aux;
        switch(type){
            case 2:
                Log.i("ACCION","aceptar");
                aux = adapter.getList().get(position);
                aux.setState("confirmed");
                presenter.changeState(aux);
                break;
            case 3:
                Log.i("ACCION","borrar");
                aux = adapter.getList().get(position);
                aux.setState("deleted");
                presenter.changeState(aux);
                break;
        }
        adapter.getList().remove(position);
        adapter.notifyItemRemoved(position);
    }


    static class ViewHolder extends AbstractSwipeableItemViewHolder {

        @BindView(R.id.swipeable_container)
        View swipeableContainer;
        @BindView(R.id.option_view_2)
        View optionView2;
        @BindView(R.id.option_view_3)
        View optionView3;
        @BindView(R.id.nick_item)
        TextView tvNick;
        @BindView(R.id.date_item)
        TextView tvDate;
        @BindView(R.id.sdvItem)
        SimpleDraweeView sdvItem;
        float lastSwipeAmount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public View getSwipeableContainerView() {
            return swipeableContainer;
        }

        @Override
        public void onSlideAmountUpdated(float horizontalAmount, float verticalAmount, boolean isSwiping) {
            int itemWidth = itemView.getWidth();
            float optionItemWidth = itemWidth * OPTIONS_AREA_PROPORTION / 2;
            int offset = (int) (optionItemWidth + 0.5f);
            float p = Math.max(0, Math.min(OPTIONS_AREA_PROPORTION, -horizontalAmount)) / OPTIONS_AREA_PROPORTION;

            if (optionView2.getWidth() == 0) {
                setLayoutWidth(optionView2, (int) (optionItemWidth + 0.5f));
                setLayoutWidth(optionView3, (int) (optionItemWidth + 0.5f));
            }

            optionView2.setTranslationX(-(int) (p * optionItemWidth * 2 + 0.5f) + offset);
            optionView3.setTranslationX(-(int) (p * optionItemWidth * 1 + 0.5f) + offset);

            if (horizontalAmount < (-REMOVE_ITEM_THRESHOLD)) {
                swipeableContainer.setVisibility(View.INVISIBLE);
                optionView2.setVisibility(View.INVISIBLE);
                optionView3.setVisibility(View.INVISIBLE);
            } else {
                swipeableContainer.setVisibility(View.VISIBLE);
                optionView2.setVisibility(View.VISIBLE);
                optionView3.setVisibility(View.VISIBLE);
            }

            lastSwipeAmount = horizontalAmount;
        }

        private static void setLayoutWidth(View v, int width) {
            ViewGroup.LayoutParams lp = v.getLayoutParams();
            lp.width = width;
            v.setLayoutParams(lp);
        }
    }

    static class Adapter extends RecyclerView.Adapter<ViewHolder> implements SwipeableItemAdapter<ViewHolder> {

        interface Swipeable extends SwipeableItemConstants {
        }

        ArrayList<CitaDTO> mItems;
        EventClickListener listener;
        Context mContext;

        public Adapter(Context c,ArrayList<CitaDTO> list, EventClickListener listener) {
            setHasStableIds(true);
            this.mContext = c;
            mItems = list;
            this.listener = listener;
        }

        public ArrayList<CitaDTO> getList(){
            return this.mItems;
        }

        @Override
        public long getItemId(int position) {
            return mItems.get(position).getTimeStamp();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dating_management, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            //holder.tvNick.setText("item " + position);

            holder.optionView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view,position,2);
                }
            });

            holder.optionView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view,position,3);
                }
            });

            CitaDTO item = mItems.get(position);

            presenter.getData(item.getUIDclient(), new IGetResults() {
                @Override
                public void onSuccess(Object object) {
                    final ClientDTO user = (ClientDTO) object;
                    try {
                        GetImageJob job = new GetImageJob(user.getUid(), presenter.getContext(), new IGetResults<Uri>() {
                            @Override
                            public void onSuccess(Uri object) {
                                user.setPhotoURL(object);

                                if(user.getNick()!=null){
                                    holder.sdvItem.setImageURI(user.getPhotoURL());
                                    holder.tvNick.setText(user.getNick());
                                }

                            }

                            @Override
                            public void onFailure(Uri object) {
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

            holder.setMaxLeftSwipeAmount(-OPTIONS_AREA_PROPORTION);
            holder.setMaxRightSwipeAmount(0);
            holder.setSwipeItemHorizontalSlideAmount(
                    item.pinned ? -OPTIONS_AREA_PROPORTION : 0);
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        @Override
        public SwipeResultAction onSwipeItem(ViewHolder holder, int position, int result) {
            if (result == Swipeable.RESULT_SWIPED_LEFT) {
                if (holder.lastSwipeAmount < (-REMOVE_ITEM_THRESHOLD)) {
                    return new SwipeLeftRemoveAction(this, position);
                } else {
                    return new SwipeLeftPinningAction(this, position);
                }
            } else {
                return new SwipeCancelAction(this, position);
            }
        }

        @Override
        public int onGetSwipeReactionType(ViewHolder holder, int position, int x, int y) {
            return Swipeable.REACTION_CAN_SWIPE_LEFT;
        }

        @Override
        public void onSetSwipeBackground(ViewHolder holder, int position, int type) {
            if (type == Swipeable.DRAWABLE_SWIPE_LEFT_BACKGROUND) {
                holder.itemView.setBackgroundColor(Color.parseColor("#ff3300"));
            }
        }
    }

    static class SwipeLeftRemoveAction extends SwipeResultActionRemoveItem {
        Adapter adapter;
        int position;

        public SwipeLeftRemoveAction(Adapter adapter, int position) {
            this.adapter = adapter;
            this.position = position;
        }

        @Override
        protected void onPerformAction() {
            adapter.mItems.remove(position);
            adapter.notifyItemRemoved(position);
            Log.i("ACCION","accion eliminar");
        }
    }

    static class SwipeLeftPinningAction extends SwipeResultActionMoveToSwipedDirection {
        Adapter adapter;
        int position;

        public SwipeLeftPinningAction(Adapter adapter, int position) {
            this.adapter = adapter;
            this.position = position;
        }

        @Override
        protected void onPerformAction() {
            adapter.mItems.get(position).pinned = true;
            adapter.notifyItemChanged(position);
            Log.i("ACCION","accion izquierda");
        }
    }


    static class SwipeCancelAction extends SwipeResultActionDefault {
        Adapter adapter;
        int position;

        public SwipeCancelAction(Adapter adapter, int position) {
            this.adapter = adapter;
            this.position = position;
        }

        @Override
        protected void onPerformAction() {
            adapter.mItems.get(position).pinned = false;
            adapter.notifyItemChanged(position);
            Log.i("ACCION","accion cancelar");
        }
    }
}
