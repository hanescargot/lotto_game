package com.pyrion.game.lotto_shopping.data;

import android.util.Log;

import com.pyrion.game.lotto_shopping.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Lotto {
    public static int latestDrwNo = 1;
    public static int selectedDrwNo = 1; //on history
    public static HashMap<Integer, int[]> lottoNumberHash = new HashMap<>();  //회차:[번호]

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
        Log.i("cor", colorNum+"");
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


    public static String getResultString(int code){
        switch (code){
            case 0 : return "꽝";
            case 1 : return "5등 당첨"; //3개
            case 2 : return "4등 당첨"; //4개
            case 3 : return "3등 당첨"; //5개
            case 4 : return "2등 당첨"; //5개와 보너스번호
            case 5 : return "1등 당첨"; //6개
       }
       return "꽝";
    }
}