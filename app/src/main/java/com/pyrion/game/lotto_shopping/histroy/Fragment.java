package com.pyrion.game.lotto_shopping.histroy;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.pyrion.game.lotto_shopping.R;
import com.pyrion.game.lotto_shopping.data.Auction;
import com.pyrion.game.lotto_shopping.data.Lotto;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 * Use the {@link Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment extends androidx.fragment.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment() {
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
    public static Fragment newInstance(String param1, String param2) {
        Fragment fragment = new Fragment();
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
        Lotto.selectedDrwNo = Lotto.latestDrwNo+1;
        return inflater.inflate(R.layout.fragment_histroy, container, false);
    }

    TextView[] tvBallNum = new TextView[7];
    TextView tvDrwNo, tvNoTickets;
    TextView tvTimer;
    View resultBalls;
    AdapterBoughtTickets adapter;
    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for(int i=0; i<tvBallNum.length; i++){
            tvBallNum[i] = view.findViewById(R.id.tv_ball_1+i);
        }
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        tvTimer = view.findViewById(R.id.tv_timer);
        resultBalls = view.findViewById(R.id.result_balls);
        tvNoTickets = view.findViewById(R.id.tv_no_tickets);
        adapter = new AdapterBoughtTickets(getActivity(), tvNoTickets);//SharedPref.getData("week_bought_tickets", )
        recyclerView.setAdapter( adapter );


        tvDrwNo = view.findViewById(R.id.drw_no);
        tvDrwNo.setText(Lotto.selectedDrwNo+" 회");
        setLottoBall(Lotto.selectedDrwNo);

        ImageButton btnLeft = view.findViewById(R.id.btn_left);
        ImageButton btnRight = view.findViewById(R.id.btn_right);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lotto.selectedDrwNo -= 1;
                tvDrwNo.setText(Lotto.selectedDrwNo+" 회");
                setLottoBall(Lotto.selectedDrwNo);
                if(  Lotto.selectedDrwNo != Lotto.latestDrwNo +1 ){
                    btnRight.setVisibility(View.VISIBLE);
                }
                if( Lotto.selectedDrwNo == 1 ){
                    btnLeft.setVisibility(View.INVISIBLE);
                }
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lotto.selectedDrwNo += 1;
                tvDrwNo.setText(Lotto.selectedDrwNo+" 회");
                setLottoBall(Lotto.selectedDrwNo);
                if( Lotto.selectedDrwNo == Lotto.latestDrwNo +1 ){
                    btnRight.setVisibility(View.INVISIBLE);
                }
                if( Lotto.selectedDrwNo != 1 ){
                    btnLeft.setVisibility(View.VISIBLE);
                }
            }
        });

        if( Lotto.selectedDrwNo == Lotto.latestDrwNo +1 ){
            btnRight.setVisibility(View.INVISIBLE);
        }
        if( Lotto.selectedDrwNo == 1 ){
            btnLeft.setVisibility(View.INVISIBLE);
        }


        Handler handler = new Handler(){
            public void handleMessage(Message msg) {
                String endTimeString = "2021-10-30 23:59:59";//todo change
                tvTimer.setText("당첨 결과까지 "+ Auction.getLeftTime(endTimeString)+" 남음");
            }
        };

        TimerTask task = new TimerTask(){
            public void run(){
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            }
        };
        Timer historyTimer = new Timer();
        historyTimer.scheduleAtFixedRate(task, 0, 1000); //1000ms = 1sec

    }

    RequestQueue requestQueue;
    String[] key = {"drwtNo1", "drwtNo2", "drwtNo3", "drwtNo4", "drwtNo5", "drwtNo6", "bnusNo"};
    public void setLottoBall(int drwNo){
        adapter.notifyDataSetChanged();
        if(drwNo == (Lotto.latestDrwNo+1)){
            //아직 당첨 결과가 없는 주
            tvTimer.setVisibility(View.VISIBLE);
            resultBalls.setVisibility(View.INVISIBLE);
            return;
        }else{
            //당첨결과 있을 때
            tvTimer.setVisibility(View.INVISIBLE);
            resultBalls.setVisibility(View.VISIBLE);
        }


        if(Lotto.lottoNumberHash.containsKey(drwNo)){
            //이미 서버에서 가져온 DB가 있을 때
            int[] nums = Lotto.lottoNumberHash.get(drwNo);
            for (int i=0; i<nums.length; i++){
                int num = nums[i];
                tvBallNum[i].setText(num+"");
                tvBallNum[i].setBackgroundResource( Lotto.getBgSrc(num) );
            }
            Log.i("memory_t", nums+"");
            return;
        }
        //서버에서 가져오기
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        String url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo="+drwNo;
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        HashMap<String,Double> data = new Gson().fromJson(response, HashMap.class);
                        if(data.isEmpty()) return;
                        int[] numbers = new int[7];
                        for (int i = 0; i< key.length; i++){
                            int num = data.get(key[i]).intValue();
                            tvBallNum[i].setText(num+"");
                            tvBallNum[i].setBackgroundResource( Lotto.getBgSrc(num) );
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





}