package com.hcumbicusr.platzigram.View;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.hcumbicusr.platzigram.R;
import com.hcumbicusr.platzigram.View.fragment.HomeFragment;
import com.hcumbicusr.platzigram.View.fragment.ProfileFragment;
import com.hcumbicusr.platzigram.View.fragment.SearchFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class ContainerActivity extends AppCompatActivity {

    FrameLayout container;
    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        bottomBar = (BottomBar) findViewById(R.id.bottombar);
        bottomBar.setDefaultTab(R.id.home);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.home:
                        HomeFragment homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container,homeFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.profile:
                        ProfileFragment profileFragment= new ProfileFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container,profileFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.search:
                        SearchFragment searchFragment= new SearchFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container,searchFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                }
            }
        });
    }
}
