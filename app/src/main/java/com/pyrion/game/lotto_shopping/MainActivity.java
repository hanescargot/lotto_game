package com.pyrion.game.lotto_shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

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
        viewPager.setAdapter( new MainPagerAdapter(this));

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                bottomNavigationView.setSelectedItemId( getItemId(viewPager.getCurrentItem()) );
            }
        });

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_buy:
                        viewPager.setCurrentItem(0);
                        bottomIconColor( Color.parseColor("#F26565"));
                        break;
                    case R.id.page_history:
                        viewPager.setCurrentItem(1);
                        bottomIconColor( Color.parseColor("#FFC107"));
                        break;

                    case R.id.page_auction:
                        viewPager.setCurrentItem(2);
                        bottomIconColor( Color.parseColor("#3F51B5"));
                        break;

                    case R.id.page_research:
                        viewPager.setCurrentItem(3);
                        bottomIconColor( Color.parseColor("#673AB7") );
                        break;
                }
                return true;
            }
        });


    }


    public void bottomIconColor(int color){
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_enabled  }
        };
        int[] colors = new int[] {
                color
        };
        bottomNavigationView.setItemTextColor(new ColorStateList(states, colors));
    }
}