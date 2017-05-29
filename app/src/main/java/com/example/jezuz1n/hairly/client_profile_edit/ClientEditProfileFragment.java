package com.example.jezuz1n.hairly.client_profile_edit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jezuz1n.hairly.R;
import com.example.jezuz1n.hairly.jobs.PostImageShopJob;
import com.example.jezuz1n.hairly.models.dto.ClientDTO;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.utils.IGetResults;
import com.example.jezuz1n.hairly.utils.LocationUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by jezuz1n on 08/05/2017.
 */

public class ClientEditProfileFragment extends Fragment implements ClientEditProfileView{

    ClientEditProfilePresenter presenter;

    @BindView(R.id.simpleDraweeView_client)
    SimpleDraweeView sdvProfile;

    @BindView(R.id.et_email_client_profile)
    EditText etEmail;

    @BindView(R.id.et_nick_client_profile)
    EditText etNick;

    @BindView(R.id.et_phone_client_profile)
    EditText etPhone;

    @BindView(R.id.pb_data_client)
    ProgressBar pbClientProfile;

    @BindView(R.id.cv_data_client)
    CardView cvData;

    private final int PICK_IMAGE_REQUEST = 201;

     public ClientEditProfileFragment(){

     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        View view = inflater.inflate(R.layout.fragment_client_edit_profile, container, false);
        ButterKnife.bind(this, view);
        presenter = new ClientEditProfilePresenterImpl();
        presenter.initializeView(this);
        showProgressBar();
        cvData.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showProgressBar() {
        pbClientProfile.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pbClientProfile.setVisibility(View.GONE);
    }

    @Override
    public void navigateToIndex() {

    }

    @Override
    public Context getAppContext() {
        return getContext();
    }

    @Override
    public void setData(ClientDTO user) {
        if(etNick.getText().toString()!=null){
            etNick.setText(user.getNick());
        }

        if(etEmail.getText().toString()!=null){
            etEmail.setText(user.getEmail());
        }

        if(etPhone.getText().toString()!=null){
            etPhone.setText(user.getPhone());
        }

        if(user.getPhotoURL()!=null){
            sdvProfile.setImageURI(user.getPhotoURL());
        }
            hideProgressBar();
        cvData.setVisibility(View.VISIBLE);

    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
    }

    @Optional
    @OnClick({R.id.simpleDraweeView_client, R.id.tv_change_photo_client})
    public void onClickPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen de perfil"), PICK_IMAGE_REQUEST);
    }

    public ClientDTO getObject() {
        ClientDTO client = new ClientDTO();
        client.setNick(etNick.getText().toString());
        client.setEmail(etEmail.getText().toString());
        client.setPhone(etPhone.getText().toString());
        return client;
    }

    @Override
    public Bitmap getImage() {
        return sdvProfile.getDrawingCache();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            try {
                PostImageShopJob postImageShopJob = new PostImageShopJob(data.getData(), getAppContext(), new IGetResults<Uri>() {
                    @Override
                    public void onSuccess(Uri object) {
                        sdvProfile.setImageURI(object);
                    }

                    @Override
                    public void onFailure(Uri object) {
                        Log.i("ERROR"," al subir la img");
                    }
                });

                postImageShopJob.onRun();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    @OnClick(R.id.btn_client_save_profile)
    public void onReadyClick() {
        presenter.updateData(getObject());
    }

    @Override
    public void setProfileImg(String img) {

    }
}
