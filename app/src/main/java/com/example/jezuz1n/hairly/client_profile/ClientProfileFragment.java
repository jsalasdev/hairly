package com.example.jezuz1n.hairly.client_profile;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jezuz1n.hairly.R;
import com.example.jezuz1n.hairly.models.dto.ClientDTO;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jezuz1n on 24/05/2017.
 */

public class ClientProfileFragment extends Fragment implements ClientProfileView {

    @BindView(R.id.layout_profile_client)
    LinearLayout mLinearLayout;

    @BindView(R.id.sdvProfileFragmentClient)
    SimpleDraweeView sdvProfile;

    @BindView(R.id.tv_nick_client_profile)
    TextView tvNick;

    @BindView(R.id.tv_email_client_profile)
    TextView tvEmail;

    @BindView(R.id.tv_phone_client_profile)
    TextView tvPhone;

    ProgressDialog progressDialog;

    ClientProfilePresenter presenter;

    ClientDTO mClient;

    public ClientProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        View view = inflater.inflate(R.layout.fragment_client_profile, container, false);
        ButterKnife.bind(this, view);
        presenter = new ClientProfilePresenterImpl(this);

        Bundle b = getArguments();

        if (b != null) {
            presenter.loadData(b.getString("uid"));
        } else {
            presenter.loadData();
        }

        return view;
    }

    public static ClientProfileFragment newInstance(Bundle b) {
        ClientProfileFragment fragment = new ClientProfileFragment();
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void showProgressBar() {
        progressDialog = ProgressDialog.show(getContext(), "Actualizando información", "Conectando...", true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                }
            }
        }).start();

    }

    @Override
    public void hideProgressBar() {
        progressDialog.dismiss();
    }

    @Override
    public void setData(ClientDTO client) {
        mClient = client;

        if (client.getNick() != null) {
            tvNick.setText(client.getNick());
        }

        if (client.getPhone() != null) {
            tvPhone.setText(client.getPhone());
        }

        if (client.getEmail() != null) {
            tvEmail.setText(client.getEmail());
        }

        if (client.getPhotoURL() != null) {
            sdvProfile.setImageURI(client.getPhotoURL());
        }

        hideProgressBar();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getAppContext() {
        return getContext();
    }


}
