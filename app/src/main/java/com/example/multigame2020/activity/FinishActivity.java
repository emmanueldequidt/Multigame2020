package com.example.multigame2020.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.multigame2020.R;
import com.example.multigame2020.utils.ActivityUtils;


public class FinishActivity extends AppCompatActivity {

    public static final String NAME = "name";
    public static final String SCORE = "score";

    public static void newInstance(Activity activity, String name, int score){
        Intent intent = new Intent(activity, FinishActivity.class);
        intent.putExtra(FinishActivity.NAME, name);
        intent.putExtra(FinishActivity.SCORE, score);
        ActivityUtils.launchActivity(activity, intent, ActivityUtils.TYPE_FADE, false);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        ((TextView)findViewById(R.id.finish_name)).setText(getIntent().getStringExtra(NAME));
        ((TextView)findViewById(R.id.finish_score)).setText(String.valueOf(getIntent().getIntExtra(SCORE, 0)));
        findViewById(R.id.finish_menu).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.finish_exit).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });


    }

}
