package com.pyrion.game.lotto_shopping.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    public static String name = "null_name";
    public static ArrayList< ArrayList<Integer> > weekBoughtTickets = new ArrayList<>();

    public static HashMap<Integer, ArrayList< ArrayList<Integer> >> historyBoughtTickets = new HashMap<>(); //회차 : 구매한 티켓 번호세트들

}
