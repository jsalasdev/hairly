package com.example.jezuz1n.hairly.shop_profile;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jezuz1n.hairly.R;
import com.example.jezuz1n.hairly.models.dto.CitaDTO;
import com.example.jezuz1n.hairly.models.dto.ShopDTO;
import com.example.jezuz1n.hairly.session.SessionManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.storage.StorageException;

import java.sql.Timestamp;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jezuz1n on 24/05/2017.
 */

public class ShopProfileFragment extends Fragment implements ShopProfileView, View.OnClickListener {

    @BindView(R.id.layout_profile)
    LinearLayout mLinearLayout;

    @BindView(R.id.sdvProfileFragment)
    SimpleDraweeView sdvProfile;

    @BindView(R.id.tv_nick_shop_profile)
    TextView tvNick;

    @BindView(R.id.tv_description_shop_profile)
    TextView tvDescription;

    @BindView(R.id.tv_email_shop_profile)
    TextView tvEmail;

    @BindView(R.id.tv_province_shop_profile)
    TextView tvProvince;

    @BindView(R.id.tv_address_shop_profile)
    TextView tvAddress;

    @BindView(R.id.tv_phone_shop_profile)
    TextView tvPhone;

    ProgressDialog progressDialog;

    ShopProfilePresenter presenter;

    ShopDTO mShop;

    public ShopProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        View view = inflater.inflate(R.layout.fragment_shop_profile, container, false);
        ButterKnife.bind(this, view);
        presenter = new ShopProfilePresenterImpl(this);

        Bundle b = getArguments();

        if (b != null) {
            presenter.loadData(b.getString("uid"));
        } else {
            presenter.loadData();
        }

        if (new SessionManager(getContext()).getUserDetails().get(SessionManager.KEY_TYPE).equalsIgnoreCase("client")) {
            insertBtn();
        }

        return view;
    }

    public static ShopProfileFragment newInstance(Bundle b) {
        ShopProfileFragment fragment = new ShopProfileFragment();
        fragment.setArguments(b);
        return fragment;
    }

    public void insertBtn() {

        CardView card = new CardView(getContext());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10, 10, 10, 10);
        card.setContentPadding(25, 25, 25, 25);
        card.setCardBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimaryLight));
        card.setMaxCardElevation(15);

        AppCompatButton button = new AppCompatButton(getContext());
        button.setLayoutParams(params);
        button.setText("Pedir Cita");
        button.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimaryDark));
        button.setOnClickListener(this);

        card.addView(button);
        mLinearLayout.addView(card);
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
    public void setData(ShopDTO shop) {
        mShop = shop;

        if (shop.getPhotoURL() != null) {
            sdvProfile.setImageURI(shop.getPhotoURL());
        }

        if (shop.getNick() != null) {
            tvNick.setText(shop.getNick());
        }

        if (shop.getProvince() != null) {
            tvProvince.setText(shop.getProvince());
        }

        if (shop.getAddress() != null) {
            tvAddress.setText(shop.getAddress());
        }

        if (shop.getPhone() != null) {
            tvPhone.setText(shop.getPhone());
        }

        if (shop.getEmail() != null) {
            tvEmail.setText(shop.getEmail());
        }

        if (shop.getDescription() != null) {
            tvDescription.setText(shop.getDescription());
        }
        hideProgressBar();
    }

    @Override
    public void showMsg(String msg) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getAppContext() {
        return getContext();
    }

    @Override
    public void onClick(View view) {
        final int mYear, mMonth, mDay;

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH) + 1;
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.datepicker,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, final int year,
                                          final int monthOfYear, final int dayOfMonth) {

                        final int mHour, mMinute;

                        final Calendar c = Calendar.getInstance();
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);

                        // Launch Time Picker Dialog
                        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), R.style.datepicker,
                                new TimePickerDialog.OnTimeSetListener() {

                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {
                                       String uid = new SessionManager(getContext()).getUserDetails().get(SessionManager.KEY_UID);

                                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                                        CitaDTO cita = new CitaDTO(dayOfMonth, monthOfYear, year, hourOfDay, minute);
                                        cita.setUIDclient(uid);
                                        cita.setUIDshop(mShop.getUid());
                                        cita.setTimeStamp(timestamp.getTime());
                                        cita.setState("running");
                                        //enviar objeto al presenter
                                        presenter.setCita(cita);

                                    }
                                }, mHour, mMinute, false);
                        timePickerDialog.setTitle("Fecha: " + String.valueOf(dayOfMonth) + " - " + String.valueOf(monthOfYear) + " - " + String.valueOf(year));
                        timePickerDialog.show();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
