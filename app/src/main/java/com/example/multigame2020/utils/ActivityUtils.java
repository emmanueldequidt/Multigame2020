package com.example.multigame2020.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.multigame2020.R;
import com.example.multigame2020.activity.CreatePlayerActivity;
import com.example.multigame2020.activity.SplashActivity;

public class ActivityUtils {

    public static final int TYPE_FADE = 0;
    public static final int TYPE_SLIDE = 1;

    public static void launchActivity(Activity activity, Intent intent, int type) {
        launchActivity(activity, intent, type, true);
    }

    public static void launchActivity(Activity activity, Class activityClass, int type) {
        Intent intent = new Intent(activity, activityClass);
        launchActivity(activity, intent, type, true);
    }

    public static void launchActivity(Activity activity, Class activityClass, int type, boolean isFinish) {
        launchActivity(activity, new Intent(activity, activityClass), type, isFinish);
    }

    public static void launchActivity(Activity activity, Intent intent, int type, boolean isFinish) {
        activity.startActivity(intent);
        if (type == TYPE_SLIDE) {
            activity.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_right);
        } else {
            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
        if (isFinish) {
            activity.finish();
        }
    }


    //FRAGMENT
    public static void addFragmentToActivity(Fragment parentFragment, @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction;
        transaction = parentFragment.getChildFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.add(frameId, fragment, "tag");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}