package com.pyrion.game.lotto_shopping;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.pyrion.game.lotto_shopping.data.Lotto;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHistory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHistory extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHistory() {
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
    public static FragmentHistory newInstance(String param1, String param2) {
        FragmentHistory fragment = new FragmentHistory();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    Gson gson;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gson = new Gson();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    int thisDrwNo = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        thisDrwNo = Lotto.currentDrwNo;
        return inflater.inflate(R.layout.fragment_histroy, container, false);
    }

    TextView[] tvBallNum = new TextView[7];
    TextView tvDrwNo;
    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for(int i=0; i<tvBallNum.length; i++){
            tvBallNum[i] = view.findViewById(R.id.tv_ball_1+i);
        }

        tvDrwNo = view.findViewById(R.id.drw_no);
        tvDrwNo.setText(thisDrwNo+" 회");
        setLottoBall(thisDrwNo);

        ImageButton btnLeft = view.findViewById(R.id.btn_left);
        ImageButton btnRight = view.findViewById(R.id.btn_right);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thisDrwNo -= 1;
                tvDrwNo.setText(thisDrwNo+" 회");
                setLottoBall(thisDrwNo);
                if( thisDrwNo != Lotto.currentDrwNo ){
                    btnRight.setVisibility(View.VISIBLE);
                }
                if( thisDrwNo == 1 ){
                    btnLeft.setVisibility(View.INVISIBLE);
                }
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thisDrwNo += 1;
                tvDrwNo.setText(thisDrwNo+" 회");
                setLottoBall(thisDrwNo);
                if( thisDrwNo == Lotto.currentDrwNo ){
                    btnRight.setVisibility(View.INVISIBLE);
                }
                if( thisDrwNo != 1 ){
                    btnLeft.setVisibility(View.VISIBLE);
                }
            }
        });

        if( thisDrwNo == Lotto.currentDrwNo ){
            btnRight.setVisibility(View.INVISIBLE);
        }
        if( thisDrwNo == 1 ){
            btnLeft.setVisibility(View.INVISIBLE);
        }

    }

    RequestQueue requestQueue;
    String[] lotto_key = {"drwtNo1", "drwtNo2", "drwtNo3", "drwtNo4", "drwtNo5", "drwtNo6", "bnusNo"};
    public void setLottoBall(int drwNo){
        if(Lotto.lottoNumberHash.containsKey(drwNo)){
            int[] nums = Lotto.lottoNumberHash.get(drwNo);
            for (int i=0; i<nums.length; i++){
                int num = nums[i];
                tvBallNum[i].setText(num+"");
                tvBallNum[i].setBackgroundResource( getBgSrc(num) );
            }
            Log.i("memory_t", nums+"");
            return;
        }

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        String url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo="+drwNo;
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        HashMap<String,Double> data = gson.fromJson(response, HashMap.class);
                        int[] numbers = new int[7];
                        for (int i=0; i<lotto_key.length; i++){
                            int num = data.get(lotto_key[i]).intValue();
                            tvBallNum[i].setText(num+"");
                            tvBallNum[i].setBackgroundResource( getBgSrc(num) );
                            numbers[i] = num;
                        }
                        Lotto.lottoNumberHash.put(drwNo, numbers);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("response", "response_err"+error);
                    }
                }
        );

        request.setShouldCache(false);
        requestQueue.add(request);
    }



    public int getBgSrc(int num){
        int colorNum = num/10;
        Log.i("cor", colorNum+"");
        int bgSrc;
        switch (colorNum){
            case 0:
                bgSrc=R.drawable.ic_lotto_ball_yellow;
                break;
            case 1:
                bgSrc=R.drawable.ic_lotto_ball_blue;
                break;
            case 2:
                bgSrc=R.drawable.ic_lotto_ball_purple;
                break;
            case 3:
                bgSrc=R.drawable.ic_lotto_ball_green;
                break;
            case 4:
                bgSrc=R.drawable.ic_lotto_ball_red;
                break;
            default:
                bgSrc=R.drawable.ic_lotto_ball_yellow;
        }
        return bgSrc;
    }

}