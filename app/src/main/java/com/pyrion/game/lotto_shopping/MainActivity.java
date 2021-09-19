package com.pyrion.game.lotto_shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = this.getSupportFragmentManager();

//        Default fragment
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentBuy.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("bottom_navigation_backstack") // name can be null
                .commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_buy:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, FragmentBuy.class, null)
                                .setReorderingAllowed(true)
                                .addToBackStack("bottom_navigation_backstack") // name can be null
                                .commitAllowingStateLoss();
                        break;
                    case R.id.page_history:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, FragmentHistory.class, null)
                                .setReorderingAllowed(true)
                                .addToBackStack("bottom_navigation_backstack") // name can be null
                                .commitAllowingStateLoss();
                        break;

                    case R.id.page_auction:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, FragmentAuction.class, null)
                                .setReorderingAllowed(true)
                                .addToBackStack("bottom_navigation_backstack") // name can be null
                                .commitAllowingStateLoss();
                        break;
                    case R.id.page_research:
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, FragmentResearch.class, null)
                                .setReorderingAllowed(true)
                                .addToBackStack("bottom_navigation_backstack") // name can be null
                                .commitAllowingStateLoss();

                        break;
                }
                return true;
            }
        });
    }
}