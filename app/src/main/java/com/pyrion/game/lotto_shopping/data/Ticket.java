package com.pyrion.game.lotto_shopping.data;

public class Ticket {
    public static int COIN = 0 ;
    public static int AD = 1 ;
    public static int TIME = 2 ;

    public static int buyTicket = Ticket.AD;


    public static String getNumPadText(){
        if(buyTicket == COIN)return "1000 Coin";
        if(buyTicket == AD)return "광고보고 무료티켓 받기";
        if(buyTicket == TIME)return "24시간 1회 무료";
        return "티켓 오류";
    }

}
