package com.pyrion.game.lotto_shopping.data;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    public static String name = "null_name";
    public static long money = (long)0;
    public static ArrayList< TicketDB > weekBoughtTickets = new ArrayList<>();
    public static HashMap< Integer, ArrayList<TicketDB> > historyBoughtTickets = new HashMap<>(); //회차 : 구매한 티켓 번호세트들


    public static class TicketDB{
        ArrayList<Integer> numbers; String resultRanking;
        public TicketDB(ArrayList<Integer> numbers) {
            this.numbers = numbers;
            this.resultRanking = "대기중";
        }
        public TicketDB(ArrayList<Integer> numbers, String resultRanking) {
            this.numbers = numbers;
            this.resultRanking = resultRanking;
        }

        public void setNumbers(ArrayList<Integer> numbers) {
            this.numbers = numbers;
        }
        public void setResultRanking(String resultRanking) {
            this.resultRanking = resultRanking;
        }

        public ArrayList<Integer> getNumbers() {
            return numbers;
        }
        public String getResultRanking() {
            return resultRanking;
        }
    }
}
