package com.example.jezuz1n.hairly.splash;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jezuz1n.hairly.R;
import com.example.jezuz1n.hairly.index.IndexShopActivity;
import com.example.jezuz1n.hairly.login.LoginActivity;
import com.example.jezuz1n.hairly.register.RegisterActivity;
import com.example.jezuz1n.hairly.session.SessionManager;
import com.example.jezuz1n.hairly.index.IndexClientActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.tv_logo_splash)
    TextView tvLogoSplash;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(getApplicationContext());

        //quitar
        //sessionManager.logoutUser();

        initLogo();
        load();
    }

    public void initLogo(){
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Sketch.ttf");
        tvLogoSplash.setTypeface(typeface);
        tvLogoSplash.setText(getString(R.string.app_name));
    }

    public void load(){

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = null;
                    if(sessionManager.isLogged()){
                        if(sessionManager.getUserDetails().get("type").equalsIgnoreCase("client")){
                            intent = new Intent(SplashActivity.this, IndexClientActivity.class);
                        }else{
                            intent = new Intent(SplashActivity.this, IndexShopActivity.class);
                        }
                    }else {
                        intent = new Intent(SplashActivity.this, LoginActivity.class);
                    }
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
