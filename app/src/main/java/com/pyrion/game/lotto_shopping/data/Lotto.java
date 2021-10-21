package com.pyrion.game.lotto_shopping.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Lotto {
    public static int currentDrwNo = 1;
    public static HashMap<Integer, int[]> lottoNumberHash = new HashMap<>();  //회차:[번호]

    public static int getCurrentDrwNo() throws ParseException {
        String startDate = "2002-12-07 23:59:59";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date cDate = new Date();
        Date sDate = dateFormat.parse(startDate);
        long diff = cDate.getTime() - sDate.getTime();
        long nextEpi = (diff / (86400 * 1000 * 7))+1;
        return (int) nextEpi;
    }
}