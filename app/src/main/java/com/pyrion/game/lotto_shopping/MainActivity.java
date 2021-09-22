package com.pyrion.game.lotto_shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    BottomSheetBehavior bottomSheetBehavior;
    public int getItemId(int itemNum){
        switch (itemNum){
            case 0:
                return R.id.page_buy;
            case 1:
                return R.id.page_history;
            case 2:
                return R.id.page_auction;
            case 3:
                return R.id.page_research;
        }
        return R.id.page_buy;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager2 viewPager = findViewById(R.id.fragment_container);
        viewPager.setAdapter( new PagerAdapter(this));

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                bottomNavigationView.setSelectedItemId( getItemId(viewPager.getCurrentItem()) );
            }
        });

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);

        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_buy:
                        viewPager.setCurrentItem(0);
//                        fragmentManager.beginTransaction()
//                                .replace(R.id.fragment_container,FragmentBuy.newInstance("hi","huunju") , null)
//                                .setReorderingAllowed(true)
//                                .addToBackStack("bottom_navigation_backstack") // name can be null
//                                .commitAllowingStateLoss();
                        break;
                    case R.id.page_history:
                        viewPager.setCurrentItem(1);
//                        fragmentManager.beginTransaction()
//                                .replace(R.id.fragment_container, FragmentHistory.class, null)
//                                .setReorderingAllowed(true)
//                                .addToBackStack("bottom_navigation_backstack") // name can be null
//                                .commitAllowingStateLoss();
                        break;

                    case R.id.page_auction:
                        viewPager.setCurrentItem(2);
//                        fragmentManager.beginTransaction()
//                                .replace(R.id.fragment_container, FragmentAuction.class, null)
//                                .setReorderingAllowed(true)
//                                .addToBackStack("bottom_navigation_backstack") // name can be null
//                                .commitAllowingStateLoss();
                        break;
                    case R.id.page_research:
                        viewPager.setCurrentItem(3);
//                        fragmentManager.beginTransaction()
//                                .replace(R.id.fragment_container, FragmentResearch.class, null)
//                                .setReorderingAllowed(true)
//                                .addToBackStack("bottom_navigation_backstack") // name can be null
//                                .commitAllowingStateLoss();

                        break;
                }
                return true;
            }
        });


    }


    public void bidButton(View view) {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}