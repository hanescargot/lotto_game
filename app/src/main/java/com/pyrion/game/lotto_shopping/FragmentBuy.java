package com.pyrion.game.lotto_shopping;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pyrion.game.lotto_shopping.data.NumberPad;
import com.pyrion.game.lotto_shopping.data.Ticket;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBuy#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBuy extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView tv;
    ImageView[] arrayIv;

    GridView gridView;
    NumberPadAdapter gridAdapter;

    public FragmentBuy() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment buy.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBuy newInstance(String param1, String param2) {
        FragmentBuy fragment = new FragmentBuy();
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
            Toast.makeText(getActivity(), ""+mParam1+mParam2, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buy, container, false);
    }
ImageView buyBtn;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buyBtn = view.findViewById(R.id.button);
        //set number check pad
        gridView = view.findViewById(R.id.number_pad_gridview);
        gridAdapter = new NumberPadAdapter(getActivity(), buyBtn);
        gridView.setAdapter(gridAdapter);

        View resetBtn = view.findViewById(R.id.reset);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //레트로핏 초기화
                showToast("초기화 완료");
                NumberPad.buyNumbers.clear();

                gridAdapter.notifyDataSetChanged();
                buyBtn.setImageResource(R.drawable.ic_red_button_grey);
            }
        });

        View randomBtn = view.findViewById(R.id.random);
        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //레트로핏에 아무 값이나 넣기

                Random random = new Random();
                int newNumCount = NumberPad.buyNumbers.size();
                if(newNumCount == 6){
                    showToast("이미 모든 번호가 선택되어 있습니다");
                }else{
                    showToast("자동 번호 생성");
                    for (int i=0; i<(6-newNumCount); i++){
                        NumberPad.buyNumbers.add( random.nextInt(45)+1 );
                    }
                    gridAdapter.notifyDataSetChanged();
                    buyBtn.setImageResource(R.drawable.ic_red_button);
                }

            }
        });

        View buyBtn = view.findViewById(R.id.button);
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                서버로 보내기
//                가격 계산하기
//                롤백 기능
//
            }
        });

        tv = view.findViewById(R.id.number_pad_text);
        tv.setText(Ticket.getNumPadText());

        arrayIv = new ImageView[]{
                view.findViewById(R.id.buy_coin),
                view.findViewById(R.id.buy_ad),
                view.findViewById(R.id.buy_time)
        };
        for(ImageView iv : arrayIv){
            int ivTagNum = Integer.parseInt(iv.getTag().toString());
            if ( ivTagNum==(Ticket.buyTicket) ){
                iv.setBackground( getResources().getDrawable(R.drawable.buy_empty_round_box) );
            }
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(ImageView iv : arrayIv){
//                        iv.setImageTintList( ColorStateList.valueOf(Color.parseColor("#777777")) );
//                        iv.setBackground( getResources().getDrawable(R.drawable.buy_round_box) );
                        iv.setBackground( null );
                    }
                    Ticket.buyTicket = Integer.parseInt(v.getTag().toString());
                    tv.setText(Ticket.getNumPadText());
//                    ((ImageView)v).setImageTintList( null );
                    ((ImageView)v).setBackground( getResources().getDrawable(R.drawable.buy_empty_round_box) );
                }
            });
        }

        TextView tv = view.findViewById(R.id.noti_tv);
        tv.setSelected(true);


    }



    Toast toast;
    public void showToast( String str ){
        if(toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(
                getActivity(),
                str,
                Toast.LENGTH_SHORT
        );
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();
    }
}