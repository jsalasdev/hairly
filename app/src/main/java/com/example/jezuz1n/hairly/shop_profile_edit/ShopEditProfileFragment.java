package com.example.jezuz1n.hairly.shop_profile_edit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.jezuz1n.hairly.jobs.PostImageJob;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.session.SessionManager;
import com.example.jezuz1n.hairly.utils.IGetResults;
import com.example.jezuz1n.hairly.utils.LocationUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class ShopEditProfileFragment extends Fragment implements ShopEditProfileView {

    ShopEditProfilePresenter presenter;

    @BindView(R.id.simpleDraweeView)
    SimpleDraweeView sdvProfile;

    @BindView(R.id.et_email_shop_profile)
    EditText etEmail;

    @BindView(R.id.et_nick_shop_profile)
    EditText etNick;

    @BindView(R.id.et_province_shop_profile)
    EditText etProvince;

    @BindView(R.id.et_address_shop_profile)
    EditText etAddress;

    @BindView(R.id.et_description_shop_profile)
    EditText etDescription;

    @BindView(R.id.et_phone_shop_profile)
    EditText etPhone;

    @BindView(R.id.pb_data_shop)
    ProgressBar pbShopProfile;

    @BindView(R.id.cv_data_shop)
    CardView cvData;

    private final int PICK_IMAGE_REQUEST = 201;

    public ShopEditProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        View view = inflater.inflate(R.layout.fragment_shop_edit_profile, container, false);
        ButterKnife.bind(this, view);
        presenter = new ShopEditProfilePresenterImpl();
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
        pbShopProfile.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pbShopProfile.setVisibility(View.GONE);
    }

    @Override
    public void navigateToIndex() {

    }

    @Override
    public Context getAppContext() {
        return getContext();
    }

    @Override
    public void setData(ShopDTO user) {
        etEmail.setText(user.getEmail());
        etNick.setText(user.getNick());
        etProvince.setText(user.getProvince());
        etPhone.setText(user.getPhone());
        etAddress.setText(user.getAddress());
        etDescription.setText(user.getDescription());
        if (user.getPhotoURL() != null) {
            sdvProfile.setImageURI(user.getPhotoURL());
        }
        hideProgressBar();
        cvData.setVisibility(View.VISIBLE);
    }

    @Optional
    @OnClick({R.id.simpleDraweeView, R.id.tv_change_photo})
    public void onClickPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen de perfil"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Bitmap getImage() {
        return sdvProfile.getDrawingCache();
    }

    @Override
    public void setProfileImg(String img) {

    }

    @OnClick(R.id.btn_shop_save_profile)
    public void onReadyClick() {
        presenter.updateData(getObject());
    }

    public ShopDTO getObject() {
        String nick = etNick.getText().toString();
        String address = etAddress.getText().toString();
        String phone = etPhone.getText().toString();
        String province = etProvince.getText().toString();
        String email = etEmail.getText().toString();
        String description = etDescription.getText().toString();
        ShopDTO shop = new ShopDTO(email, null, address, description, nick, phone, province);
        if (address != null & !address.equalsIgnoreCase("")) {
            Address a = LocationUtil.getLocationFromAddress(address, getAppContext());
            if (a != null) {
                shop.setLongitude(String.valueOf(a.getLongitude()));
                shop.setLatitude(String.valueOf(a.getLatitude()));
            }
        }
        return shop;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            try {
                PostImageJob postImageJob = new PostImageJob(data.getData(), getAppContext(), new IGetResults<Uri>() {
                    @Override
                    public void onSuccess(Uri object) {
                        sdvProfile.setImageURI(object);
                    }

                    @Override
                    public void onFailure(Uri object) {

                    }
                });

                postImageJob.onRun();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }


}
