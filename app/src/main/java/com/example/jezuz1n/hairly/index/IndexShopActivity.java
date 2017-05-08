package com.example.jezuz1n.hairly.index;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.jezuz1n.hairly.R;
import com.example.jezuz1n.hairly.login.LoginActivity;
import com.example.jezuz1n.hairly.maps.GMapFragment;
import com.example.jezuz1n.hairly.session.SessionManager;
import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndexShopActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.appbar)
    Toolbar toolbar;

    @BindView(R.id.navview_shop)
    NavigationView navigationView;

    Fragment frag;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_shop);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(getApplicationContext());

        View nav_header = LayoutInflater.from(this).inflate(R.layout.header_navview, null);

        ((TextView) nav_header.findViewById(R.id.usuario)).setText(sessionManager.getUserDetails().get("email"));
        navigationView.addHeaderView(nav_header);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean fragmentTransition = false;

                switch(item.getItemId()){
                    case R.id.menu_inicio:
                        break;
                    case R.id.menu_gestion_citas:
                        break;
                    case R.id.menu_historial_citas:
                        break;
                    case R.id.menu_mapa:
                        frag = new GMapFragment();
                        fragmentTransition = true;
                        break;
                    case R.id.menu_perfil:
                        break;
                    case R.id.logout_user:
                        SessionManager sessionManager = new SessionManager(getApplicationContext());
                        sessionManager.logoutUser();
                        Intent i = new Intent(IndexShopActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                        break;
                }

                if(fragmentTransition){
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,frag,null).commit();
                    item.setChecked(true);
                    getSupportActionBar().setTitle(item.getTitle());
                }

                drawerLayout.closeDrawers();

                return true;
            }
        });

        initToolbar();
    }

    public void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
