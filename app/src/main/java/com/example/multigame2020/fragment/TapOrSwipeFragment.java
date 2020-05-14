package com.example.multigame2020.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.multigame2020.R;
import com.example.multigame2020.utils.ActivityUtils;

public class TapOrSwipeFragment extends Fragment {

    public static final String IS_TAP = "isTap";

    private RelativeLayout mainContainer;
    private TextView start;

    public static TapOrSwipeFragment newInstance(boolean isTap) {
        TapOrSwipeFragment fragment = new TapOrSwipeFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_TAP, isTap);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fast_tap, container, false);
        mainContainer = view.findViewById(R.id.fast_tap_container);


        ((TextView) view.findViewById(R.id.fast_tap_title)).setText(getString(
                getArguments().getBoolean(IS_TAP, false) ? R.string.fast_tap_name : R.string.swipe_name));

        view.findViewById(R.id.fast_tap_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.addFragmentToActivity(TapOrSwipeFragment.this,
                        TapOrSwipeGameFragment.newInstance(getArguments().getBoolean(IS_TAP, false)), mainContainer.getId());
            }
        });
        return view;
    }
}
