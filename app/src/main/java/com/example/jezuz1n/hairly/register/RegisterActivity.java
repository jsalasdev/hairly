package com.example.jezuz1n.hairly.register;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jezuz1n.hairly.R;
import com.example.jezuz1n.hairly.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.tv_logo_register)
    TextView tvLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initLogo();
    }

    public void initLogo(){
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Sketch.ttf");
        tvLogo.setTypeface(typeface);
        tvLogo.setText(getString(R.string.app_name));
    }

    @OnClick(R.id.et_goto_login)
    public void navigateToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}
