package com.pyrion.game.lotto_shopping.data;

import android.util.Log;

import java.util.ArrayList;

public class TicketNumSet {
    int[] numbers = new int[6];
    public TicketNumSet(){
    }

    public boolean addTicketNumSet( int num ){
        if(numbers.length<6){
            numbers[numbers.length]=num;
            return true;
        }
        Log.i("Error", "Ticket Number is Fully Added");
        return false;
    }
}
