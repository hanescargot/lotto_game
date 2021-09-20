package com.pyrion.game.lotto_shopping;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.OverScroller;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAuction#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAuction extends Fragment {
    BottomSheetBehavior bottomSheetBehavior;

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View invisibleCurrentPrice = view.findViewById(R.id.invisible_current_price);
        View invisibleTimer = view.findViewById(R.id.invisible_timer);
        //pop up bottom sheet button
        ImageButton bsIvButton = view.findViewById(R.id.bs_button);
        TextView bsTvBar = view.findViewById(R.id.bs_text_bar);
        View bs= view.findViewById(R.id.bs);
        BottomSheetBehavior bottomSheetBehavior= BottomSheetBehavior.from(bs); //get BottomSheetBehavior
        bsIvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        bsTvBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });


        invisibleCurrentPrice.setAlpha(0);
        invisibleTimer.setAlpha(0);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if( newState == BottomSheetBehavior.STATE_EXPANDED ){
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
            }
        });
    }
}