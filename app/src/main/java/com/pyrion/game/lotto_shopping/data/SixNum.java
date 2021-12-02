package com.pyrion.game.lotto_shopping.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class SixNum {
    int[] numbers = new int[6];
    public SixNum(){
    }
    public SixNum(ArrayList<Integer> newTicketNumber){
        for(int i=0; i<newTicketNumber.size(); i++){
            numbers[i] = newTicketNumber.get(i);
        }
    }

    public int getNumber( int index ) {
        return numbers[index];
    }

    public boolean appendNumber(int num ){
        if(numbers.length<6){
            numbers[numbers.length]=num;
            return true;
        }
        Log.i("Error", "Ticket Number is Fully Added");
        return false;
    }

    public int len(){
        return numbers.length;
    }

    public boolean isContain(int num){
        for(int i : numbers){
            if (i==num) return true;
        }
        return false;
    }

    public void clear(){
        this.numbers = new int[6];
    }

    public void sort(){
        Arrays.sort(numbers);
    }
    public void setNum( int index, int num ){
        numbers[index] = num;
    }
}
