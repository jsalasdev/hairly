package com.example.jezuz1n.hairly.login;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jezuz1n.hairly.R;
import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.et_email_user)
    EditText etEmail;

    @BindView(R.id.btn_login_activity)
    AppCompatButton btnLogin;

    @BindView(R.id.et_password_user)
    EditText etPassword;

    @BindView(R.id.pb_login)
    ProgressBar pbLogin;

    @BindView(R.id.tv_logo)
    TextView etLogo;

    @BindView(R.id.tv_error_credentials)
    TextView etError;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initLogo();
        presenter = new LoginPresenterImpl(this);
    }

    public void initLogo(){
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Sketch.ttf");
        etLogo.setTypeface(typeface);
        etLogo.setText(getString(R.string.app_name));
    }

    @Override
    public void showProgressBar() {
        pbLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pbLogin.setVisibility(View.GONE);
    }

    @Override
    public void setEmailError() {
        etEmail.setError(getString(R.string.error_email));
    }

    @Override
    public void setPasswordError() {
        etPassword.setError(getString(R.string.error_password));
    }

    @Override
    public void setError() {
        etError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        etError.setVisibility(View.GONE);
    }

    @Override
    public void navigateToIndex() {
        Log.i("LOGIN","Se ha logueado bien.");
    }

    public void onClick(View v){
        presenter.validateCredentials(etEmail.getText().toString(),etPassword.getText().toString(),this);
    }

}
