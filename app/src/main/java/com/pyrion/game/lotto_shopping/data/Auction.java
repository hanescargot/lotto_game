package com.pyrion.game.lotto_shopping.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Auction {
    public static double auctionTimer = 10000;
    public static String endTimeString = "2021-11-30 23:59:59";

    public static String getLeftTime(String endTIme) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date currentTime = new Date();
        Date endTime = new Date();
        try {
            endTime = dateFormat.parse(endTIme);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = endTime.getTime() - currentTime.getTime();
        diff = diff/1000;
        long sec = diff % 60;
        long tmp_min = diff / 60;
        long min = tmp_min % 60;
        long tmp_hour = tmp_min / 60;
        long hour = tmp_hour % 24;
        long day = tmp_hour / 24;




        return (day+"일 "+hour+"시간 "+min+"분 "+sec+"초"); //서버로 부터 경매가 끝나는 날짜 받아와서 Double로 바꾸기9/4
    }

}
