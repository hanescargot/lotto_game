package com.pyrion.game.lotto_shopping.data;

import android.content.Intent;

import java.util.ArrayList;

public class WinNumSet {
    int[] numbers = new int[6];
    int bNum;

    public WinNumSet(){
    }

    public WinNumSet(int[] numbers, int bNum) {
        this.numbers = numbers;
        this.bNum = bNum;
    }
    //setter
    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }
    public void setbNum(int bNum) {
        this.bNum = bNum;
    }
    //getter
    public int[] getNumbers() {
        return numbers;
    }
    public int getbNum() {
        return bNum;
    }
}
