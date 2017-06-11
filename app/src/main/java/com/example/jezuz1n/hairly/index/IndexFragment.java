package com.example.jezuz1n.hairly.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jezuz1n.hairly.R;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by jezuz1n on 11/06/2017.
 */

public class IndexFragment extends Fragment {

    public IndexFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        return view;
    }
}
