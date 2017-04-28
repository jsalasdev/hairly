package com.example.jezuz1n.hairly.register;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.jezuz1n.hairly.R;
import com.example.jezuz1n.hairly.login.LoginActivity;
import com.example.jezuz1n.hairly.models.dto.UserDTO;
import com.example.jezuz1n.hairly.view.IndexActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    @BindView(R.id.tv_logo_register)
    TextView tvLogo;

    @BindView(R.id.tv_error_general)
    TextView tvError;

    @BindView(R.id.et_password_user_register)
    EditText etPassword;

    @BindView(R.id.et_password_user_register_repeat)
    EditText etRepeatPassword;

    @BindView(R.id.et_email_user_register)
    EditText etEmail;

    RegisterPresenter mPresenter;

    @BindView(R.id.pb_load)
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mPresenter = new RegisterPresenterImpl(this);
        initLogo();
    }

    public void initLogo() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Sketch.ttf");
        tvLogo.setTypeface(typeface);
        tvLogo.setText(getString(R.string.app_name));
    }

    @OnClick(R.id.et_goto_login)
    public void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    @Override
    public void setPasswordError(String msg) {
        etPassword.setError(msg);
        etRepeatPassword.setError(msg);
    }

    @Override
    public void setEmailError(String msg) {
        etEmail.setError(msg);
    }

    @Override
    public void showError() {
        tvError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        tvError.setVisibility(View.GONE);
    }

    @Override
    public void showDialogProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDialogProgress() {
        progress.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_register_activity)
    public void goToRegister(){
        validateData();
    }

    @Override
    public void validateData() {
        if(etPassword.getText().toString().equals(etRepeatPassword.getText().toString())){
            mPresenter.validateAccount(getData());
        }else{
            setPasswordError(getString(R.string.ERROR_PASSWORD));
            showError();
        }
    }

    @Override
    public void navigateToIndex() {
        Intent intent = new Intent(this, IndexActivity.class);
        startActivity(intent);
    }

    public UserDTO getData() {
        UserDTO user = new UserDTO();
        user.setEmail(etEmail.getText().toString());
        user.setType(getType());
        user.setPassword(etPassword.getText().toString());
        return user;
    }

    public String getType() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.user_type_group);
        String cad;
        int idSeleccionado = rg.getCheckedRadioButtonId();
        if (idSeleccionado == R.id.user_type_client) {
            cad = "client";
        } else {
            cad = "shop";
        }
        return cad;
    }

    @Override
    public void onSuccessRegister() {
        mPresenter.createSession(getData());
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
