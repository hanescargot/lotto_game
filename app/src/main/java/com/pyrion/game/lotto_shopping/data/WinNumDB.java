package com.pyrion.game.lotto_shopping.data;

public class WinNumDB {
    SixNum numbers = new SixNum();
    int bNum;

    public WinNumDB(){
    }

    public WinNumDB(SixNum numbers, int bNum) {
        this.numbers = numbers;
        this.bNum = bNum;
    }
    //setter
    public void setNumbers(SixNum numbers) {
        this.numbers = numbers;
    }
    public void setbNum(int bNum) {
        this.bNum = bNum;
    }
    //getter
    public SixNum getNumbers() {
        return numbers;
    }
    public int getBnusNum() {
        return bNum;
    }
}
