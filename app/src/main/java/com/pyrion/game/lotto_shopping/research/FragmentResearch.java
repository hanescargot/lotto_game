package com.pyrion.game.lotto_shopping.research;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pyrion.game.lotto_shopping.R;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentResearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentResearch extends Fragment {

//    ImageView ivBingo, ivRanking , ivSearch;
    HashMap<ImageView, Integer> map = new HashMap<>();
    ImageView[] ivArray =  new ImageView[3];
    ViewPager2 viewPager;
    View fragmentContainer;
    Animation popupAnimation, floatAnimation;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentResearch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentResearch.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentResearch newInstance(String param1, String param2) {
        FragmentResearch fragment = new FragmentResearch();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_research, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        setBottomIcon(ivArray[viewPager.getCurrentItem()]);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        popupAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.research_popup);
        floatAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.seleted_float);
        fragmentContainer = view.findViewById(R.id.view_pager);

        viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new PagerAdapter( getActivity() ));

        ivArray[0] = view.findViewById(R.id.iv_bingo);
        map.put(view.findViewById(R.id.iv_bingo), 0);

        ivArray[1] = view.findViewById(R.id.iv_ranking);
        map.put(view.findViewById(R.id.iv_ranking), 1);

        ivArray[2] = view.findViewById(R.id.iv_search);
        map.put(view.findViewById(R.id.iv_search), 2);

        for(ImageView iv : ivArray){
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO add animation
                    setBottomIcon((ImageView) v);
                    viewPager.setCurrentItem( map.get(v), false );
                    fragmentContainer.startAnimation(popupAnimation);
                }
            });
        }
    }

    public void setBottomIcon(ImageView v){
        for(ImageView iv : ivArray){
            if( v.equals(iv) ){
                //selected view
                iv.setColorFilter(null);
                LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) iv.getLayoutParams();
                layoutParams.gravity=Gravity.TOP;
                iv.setLayoutParams(layoutParams);

                iv.startAnimation(floatAnimation);
            }else{
                iv.setColorFilter(R.color.grey);
                LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) iv.getLayoutParams();
                layoutParams.gravity=Gravity.BOTTOM;
                iv.setLayoutParams(layoutParams);

                iv.clearAnimation();
            }

        }

    }
}
