package com.example.multigame2020.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.multigame2020.R;
import com.example.multigame2020.utils.ActivityUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                ActivityUtils.launchActivity(SplashActivity.this, CreatePlayerActivity.class, ActivityUtils.TYPE_FADE);
            }
        },3000);
    }
}