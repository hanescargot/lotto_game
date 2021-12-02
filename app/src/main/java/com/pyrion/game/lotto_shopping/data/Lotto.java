package com.pyrion.game.lotto_shopping.data;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.pyrion.game.lotto_shopping.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Lotto {
    //View에 관한 것
    public static int selectedDrwNo; //on history

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

    public static int getThisWeekDrwNo(){
        return getLatestDrwNo()+1;
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


    public String getResultRankingString(ArrayList<Integer>userBalls, ArrayList<Integer>resultBalls, int resultBnusNo){
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

//    Class
    public static class ResultDataSet {
        int drwNo; String resultRanking; long resultMoney;
        public ResultDataSet(int drwNo, String resultRanking, long resultMoney) {
            this.drwNo = drwNo;
            this.resultRanking = resultRanking;
            this.resultMoney = resultMoney;
        }
    }

    public static String getCurrentTime() {
// 현재 날짜 구하기
        Date now = new Date();
// 포맷 정의
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
// 포맷 적용
        String formatedNow = dateFormat.format(now);
// 결과 출력
        return formatedNow;
    }

}



