package com.pyrion.game.lotto_shopping;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class PagerAdapter extends FragmentStateAdapter {

    Fragment[] fragments = new Fragment[4];

    public PagerAdapter(FragmentActivity fragmentActivity){
        super(fragmentActivity);

        fragments[0] = new FragmentBuy();
        fragments[1] = new FragmentHistory();
        fragments[2] = new FragmentAuction();
        fragments[3] = new FragmentResearch();

    }
    @Override
    public Fragment createFragment(int position) {
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }

}
