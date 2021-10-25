package com.pyrion.game.lotto_shopping.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.pyrion.game.lotto_shopping.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Lotto {
    public static Context context;
    public static int latestDrwNo = 1;
    public static int selectedDrwNo = 1; //on history

    public static HashMap<Integer, HistoryResultNumberDB> hashHistoryResults;  //회차:[번호]

    private static RequestQueue requestQueue;

    public Lotto(Context context) {
        this.context = context;
        Lotto.latestDrwNo = Lotto.getLatestDrwNo();
    }

    public static int getLatestDrwNo() {
        String startDate = "2002-12-07 23:59:59";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date cDate = new Date();
        Date sDate = null;
        try {
            sDate = dateFormat.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = cDate.getTime() - sDate.getTime();
        long nextEpi = (diff / (86400 * 1000 * 7))+1;
        return (int) nextEpi;
    }

    public static int getBgSrc(int num){
        int colorNum = num/10;
        int bgSrc;
        switch (colorNum){
            case 0:
                bgSrc= R.drawable.ic_lotto_ball_yellow;
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


    public static String getResultRankingString(ArrayList<Integer>userBalls, ArrayList<Integer>resultBalls, int resultBnusNo){
        int correct = 0;
        Boolean bBallCorrect = false;
        for(int userBall : userBalls){
            if(resultBalls.contains(userBall)) correct+=1;
            if(userBall == resultBnusNo) bBallCorrect = true;
        }
        if(correct == 5 && bBallCorrect) {
            // 보너스 넘버로 2등
            correct = 7; //2등
        }
        switch (correct){
            case 3 : return "5등 당첨"; //3개
            case 4 : return "4등 당첨"; //4개
            case 5 : return "3등 당첨"; //5개
            case 6 : return "1등 당첨"; //6개
            case 7 : return "2등 당첨"; //5개와 보너스번호
            default: return "꽝";
        }
    }



    public static void getAllHistoryNumberComparedResults(ArrayList<Integer> userNumbers){
        String rankingString;
        ArrayList<ResultDataSet> resultDataSetArrayList = new ArrayList<>();

        for(int drwNo=0; drwNo<latestDrwNo; drwNo++){
            HistoryResultNumberDB resultNumberDB = getHistoryResultNumber(drwNo);
            rankingString = getResultRankingString(userNumbers, resultNumberDB.getNumbers(), resultNumberDB.getbNum());
            resultDataSetArrayList.add(new ResultDataSet(drwNo, rankingString, 0));
        }


        //result set => ArrayList<당첨회차, 등수, 상금 >
    }

    static HistoryResultNumberDB lottoResultNumber;
    public static HistoryResultNumberDB getHistoryResultNumber(int drwNo){

        if(hashHistoryResults.containsKey(drwNo)) {
            //1.Static, Spref에 데이터가 있을 때
            lottoResultNumber = hashHistoryResults.get(drwNo);
        }else {
            //3.Server에서 데이터 찾아서 ->Shared, Static 으로 넘기기
            //서버에서 가져오기
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
            String[] key = {"drwtNo1", "drwtNo2", "drwtNo3", "drwtNo4", "drwtNo5", "drwtNo6"};
            String url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo="+drwNo;
            StringRequest request = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            HashMap<String,Double> data = new Gson().fromJson(response, HashMap.class);
                            if(data.isEmpty()) return;
                            ArrayList<Integer> numbers = new ArrayList<>();
                            for (int i = 0; i< key.length; i++){
                                //일반 번호 추가
                                int num = data.get(key[i]).intValue();
                                numbers.add(num);
                            }
                            int bNum =data.get("bnusNo").intValue();
                            HistoryResultNumberDB newDB = new HistoryResultNumberDB(numbers,bNum);
                            lottoResultNumber = newDB;

                            //종료하기전에 Spref에 저장해줌
                            Lotto.hashHistoryResults.put(drwNo, newDB);
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
        return lottoResultNumber;
    }

//    Class
    public static class ResultDataSet {
        int drwNo; String resultRanking; long resultMoney;
        public ResultDataSet(int drwNo, String resultRanking, long resultMoney) {
            this.drwNo = drwNo;
            this.resultRanking = resultRanking;
            this.resultMoney = resultMoney;
        }
    }

    public static class HistoryResultNumberDB {
        ArrayList<Integer> numbers;
        int bNum;

        public HistoryResultNumberDB(ArrayList<Integer> numbers, int bNum) {
            this.numbers = numbers;
            this.bNum = bNum;
        }

        public void setNumbers(ArrayList<Integer> numbers) {
            this.numbers = numbers;
        }
        public void setbNum(int bNum) {
            this.bNum = bNum;
        }

        public ArrayList<Integer> getNumbers() {
            return numbers;
        }
        public int getbNum() {
            return bNum;
        }

    }
}



