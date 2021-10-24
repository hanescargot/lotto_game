package com.pyrion.game.lotto_shopping;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.pyrion.game.lotto_shopping.histroy.Fragment;
import com.pyrion.game.lotto_shopping.research.FragmentResearch;


public class MainPagerAdapter extends FragmentStateAdapter {

    androidx.fragment.app.Fragment[] fragments = new androidx.fragment.app.Fragment[4];

    public MainPagerAdapter(FragmentActivity fragmentActivity){
        super(fragmentActivity);

        fragments[0] = new FragmentBuy();
        fragments[1] = new Fragment();
        fragments[2] = new FragmentAuction();
        fragments[3] = new FragmentResearch();

    }
    @Override
    public androidx.fragment.app.Fragment createFragment(int position) {
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }

}
