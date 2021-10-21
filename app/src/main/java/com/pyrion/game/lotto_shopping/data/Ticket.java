package com.pyrion.game.lotto_shopping.data;

import java.util.ArrayList;

public class Ticket {
    public static int COIN = 0 ;
    public static int AD = 1 ;
    public static int TIME = 2 ;

    public static int selected = Ticket.AD;
//    public static ArrayList<int[]> bought = new ArrayList<>();

    public static String getNumPadText(){
        if(selected == COIN)return "1000 Coin";
        if(selected == AD)return "광고보고 무료티켓 받기";
        if(selected == TIME)return "24시간 1회 무료";
        return "티켓 오류";
    }

}
