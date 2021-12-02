package com.pyrion.game.lotto_shopping.data;

import android.content.Context;

public class UserTicketResultDB {
    public Context context;
    public int drwNo = 0;
    public SixNum sixNum;
    public int resultRanking;
    public long resultMoney;
    public String time = "null_time"; //"yyyy-MM-dd hh:mm:ss"

    int DEFAULT_NULL_NUM = 0;
    public UserTicketResultDB(Context context, SixNum sixNum){
        this.context = context;
        this.sixNum = sixNum;
        this.resultRanking = DEFAULT_NULL_NUM;
        this.resultMoney  = DEFAULT_NULL_NUM;
        this.time =  Lotto.getCurrentTime();
        this.drwNo = DEFAULT_NULL_NUM;
    }
    public UserTicketResultDB(Context context, int drwNo, SixNum sixNum, int resultRanking, long resultMoney) {
        this.context = context;
        this.drwNo = drwNo;
        this.sixNum = sixNum;
        this.resultRanking = getResultRankingInt(drwNo, sixNum);
        this.resultMoney = resultMoney;
        this.time = Lotto.getCurrentTime();
    }

    public String getRankingStr(){
        switch (resultRanking){
            case 5 : return "5등 당첨"; //3개
            case 4 : return "4등 당첨"; //4개
            case 3 : return "3등 당첨"; //5개
            case 2 : return "2등 당첨"; //5개와 보너스번호
            case 1 : return "1등 당첨"; //6개
            default: return "꽝";
        }
    }

    public int getResultRankingInt(int drwNo, SixNum userSixNum){
        int correct = 0;
        WinNumDB winNumDB = WinNumDAO.getWinNumSet(context, drwNo);
        SixNum winSixNum = winNumDB.getNumbers();
        int bnusNo = winNumDB.getBnusNum();
        Boolean bBallCorrect = false;
        for(int userBall : userSixNum.numbers){
            if(winSixNum.isContain(userBall)) correct+=1;
            if(userBall == bnusNo) bBallCorrect = true;
        }
        if(correct == 5 && bBallCorrect) {
            // 보너스 넘버로 2등
            return 2; //2등
        }
        switch (correct){
            case 3 : return 5; //3개
            case 4 : return 4; //4개
            case 5 : return 3; //5개
            case 6 : return 1; //6개
            default: return 0;
        }
    }
}
