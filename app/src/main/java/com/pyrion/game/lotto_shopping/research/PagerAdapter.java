package com.pyrion.game.lotto_shopping.research;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class PagerAdapter extends FragmentStateAdapter {

    Fragment[] fragments = new Fragment[3];

    public PagerAdapter(FragmentActivity fragmentActivity){
        super(fragmentActivity);

        fragments[0] = new DialogFragmentBingo();
        fragments[1] = new DialogFragmentRanking();
        fragments[2] = new DialogFragmentSearch();

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
