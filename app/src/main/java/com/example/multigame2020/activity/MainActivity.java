package com.example.multigame2020.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.multigame2020.R;
import com.example.multigame2020.fragment.DragnDropFragment;
import com.example.multigame2020.fragment.IpacGameFragment;
import com.example.multigame2020.fragment.SettingsFragment;
import com.example.multigame2020.fragment.TapOrSwipeFragment;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        viewPager = findViewById(R.id.main_view_pager);
        tabLayout = findViewById(R.id.main_tab_layout);

        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new DragnDropFragment());
        fragments.add(TapOrSwipeFragment.newInstance(false));
        fragments.add(TapOrSwipeFragment.newInstance(true));
        fragments.add(new IpacGameFragment());
        fragments.add(new SettingsFragment());

        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Drag\nNDrop";
                    case 1:
                        return "Swipe";
                    case 2:
                        return "Fast\nTap";
                    case 3:
                        return "Ipac\nGame";
                    case 4:
                        return "Settings";
                    default:
                        return "";
                }
            }
        };

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
