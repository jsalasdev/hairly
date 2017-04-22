package com.example.jezuz1n.hairly.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jezuz1n.hairly.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView {
//prueba
    @BindView(R.id.et_email_user)
    EditText etEmail;

    @BindView(R.id.et_password_user)
    EditText etPassword;

    @BindView(R.id.btn_login)
    TextView tvLogin;

    @BindView(R.id.pb_login)
    ProgressBar pbLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
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
    public void navigateToIndex() {

    }
}
