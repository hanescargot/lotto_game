package com.pyrion.game.lotto_shopping;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAuction#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAuction extends Fragment {
    BottomSheetBehavior bottomSheetBehavior;
    ImageButton bsFoldButton;

    Animation floatAnimation;
    View center, ribbon; //floating animation
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentAuction() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAuction.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAuction newInstance(String param1, String param2) {
        FragmentAuction fragment = new FragmentAuction();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        floatAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.seleted_float);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auction, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        center.startAnimation(floatAnimation);
        ribbon.startAnimation(floatAnimation);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv = view.findViewById(R.id.noti_tv);
        tv.setSelected(true);

        View invisibleCurrentPrice = view.findViewById(R.id.invisible_current_price);
        View invisibleTimer = view.findViewById(R.id.invisible_timer);
        View all = view.findViewById(R.id.all);

        center = view.findViewById(R.id.center);
        ribbon = view.findViewById(R.id.ribbon);

        //pop up bottom sheet button
        ImageButton bsIvButton = view.findViewById(R.id.bs_button);
        bsFoldButton = view.findViewById(R.id.bs_fold_button);

        TextView bsTvBar = view.findViewById(R.id.bs_top_bar_text);
        View bs= view.findViewById(R.id.bs);
        bottomSheetBehavior= BottomSheetBehavior.from(bs); //get BottomSheetBehavior
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bsIvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBottomSheetState();
            }
        });
        bsFoldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBottomSheetState();
            }
        });
        bsTvBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBottomSheetState();
            }
        });


        invisibleCurrentPrice.setAlpha(0);
        invisibleTimer.setAlpha(0);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if( newState == BottomSheetBehavior.STATE_EXPANDED ){
//                    사르륵 나타나기
//                    Animation animation = new AlphaAnimation(0, 1);
//                    animation.setDuration(200);

//                    invisibleCurrentPrice.setVisib ility(View.VISIBLE);
////                    invisibleCurrentPrice.setAnimation(animation);
//
//                    invisibleTimer.setVisibility(View.VISIBLE);
////                    invisibleTimer.setAnimation(animation);
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
                invisibleCurrentPrice.setAlpha(slideOffset);
                invisibleTimer.setAlpha(slideOffset);
                all.setAlpha(1-slideOffset);

                //TODO : add rotation arrow
            }
        });

        ;
    }

    public void setBottomSheetState(){
        if( bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            bsFoldButton.setRotation(0);
        }else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            bsFoldButton.setRotation(270);
        }
    }

}