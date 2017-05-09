package com.example.jezuz1n.hairly.shop_profile_edit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jezuz1n.hairly.R;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by jezuz1n on 09/05/2017.
 */

public class ShopEditProfileFragment extends Fragment {

    public ShopEditProfileFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        return inflater.inflate(R.layout.fragment_shop_edit_profile, container, false);
    }

}
