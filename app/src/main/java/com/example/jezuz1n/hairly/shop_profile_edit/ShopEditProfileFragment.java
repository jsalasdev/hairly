package com.example.jezuz1n.hairly.shop_profile_edit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jezuz1n.hairly.R;
import com.facebook.drawee.backends.pipeline.Fresco;

public class ShopEditProfileFragment extends Fragment implements ShopEditProfileView{

    ShopEditProfilePresenter presenter;

    public ShopEditProfileFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop_edit_profile, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fresco.initialize(getContext());
        presenter = new ShopEditProfilePresenterImpl(this);

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void navigateToIndex() {

    }
}
