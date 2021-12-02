package com.pyrion.game.lotto_shopping.data;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class UserTicketDAO {
    private static HashMap<Integer, ArrayList<UserTicketResultDB>> ticketNumberSetsHash = new HashMap<>();  //회차:[번호] PUBLIC
//    private static ArrayList<TicketNumSet> ticketNumberSets = new ArrayList<>();  //한 회차에 여러개 산 경우를 생각해서
    //서버에서 로또 번호를 불러들이면 내부저장소에 저장을 하고 내부저장소에 것을 퍼플릭으로 읽어 들여져있는건 보여준다.
    //사용자가 구매한 것임으로 서버에서 받아올 필요는 없다.
    public static ArrayList<UserTicketResultDB> getUserTicketArr(Context context, int drwNo){
        if( isStaticData(drwNo) ){
            //TODO 켜질 때 file에 있는데이터 static으로 한꺼번에 받아와 두기
            return ticketNumberSetsHash.get(drwNo);
            //TODO 꺼질 때 static이랑  device&server 데이터랑 똑같이 맞추기(업로드 할때만 신경쓰면 됨)
        }
        // 사놓은 티켓이 없는 경우
        return null;

    }

    private UserTicketDAO(){}

    public static void addNewTicket(Context context, UserTicketResultDB ticketNumSet){
        int drwNo = Lotto.getThisWeekDrwNo();
        ticketNumberSetsHash.get(drwNo).add(ticketNumSet);
        Log.i("hash", ticketNumberSetsHash + "");
    }


    public static boolean isStaticData(int drwNo){
        if (ticketNumberSetsHash.containsKey(drwNo)) return true;
        return false;
    }

    //티켓을 새로 사서 추가한게 있는 경우 수시로 싱크 맞춰준다.
    public static boolean syncTicketData(Context context){
        if(isNeedTicketUpdate(context)){
            // public에만 추가한 것이 있는 경우
            addToServer(context, Lotto.getThisWeekDrwNo(), ticketNumberSetsHash.get(Lotto.getLatestDrwNo()));
            addToDevice(context, Lotto.getThisWeekDrwNo(), ticketNumberSetsHash.get(Lotto.getLatestDrwNo()));
            return true;//업데이트 됨
        }
        // 다른점이 없는 경우
        return false; //업데이트 안됨
    }

    public static boolean isNeedTicketUpdate(Context context){
        // public의 이번주 넘버가 Device에 모든 티켓이 다 있는지 체그하면 됨
        ArrayList<UserTicketResultDB> deviceTicketNumSetArr = getNumSetsFromDevice(context, Lotto.getLatestDrwNo());
        ArrayList<UserTicketResultDB> publicTicketNumSetArr = ticketNumberSetsHash.get(Lotto.getLatestDrwNo());
        if(publicTicketNumSetArr == null) return false; //산 티켓 자체가 없는 경우
        if(deviceTicketNumSetArr==null) return true; //디바이스에 아예 없는 경우
        //티켓 하나하나 체크
        if(deviceTicketNumSetArr.containsAll(publicTicketNumSetArr)) return false;
        return true; //티켓 전부까지는 안맞음.
    }

    public static boolean addToServer(Context context, int drwNo, ArrayList<UserTicketResultDB> userTicketResultDBArray){
//        TODO retrofit 서버 작없 해야함. 후..
        return true;
    }

    public static void addToDevice(Context context, int drwNo, ArrayList<UserTicketResultDB> userTicketResultDBArray){
        DeviceFile.editData(context, DeviceFile.FILENAME_USER_TICKET_DB_ARRAY, drwNo+"", userTicketResultDBArray);
    }

    private static ArrayList<UserTicketResultDB> getNumSetsFromDevice(Context context, int drwNo){
        return  ((ArrayList<UserTicketResultDB>)(DeviceFile.getData(
                context,
                DeviceFile.FILENAME_USER_TICKET_DB_ARRAY,
                drwNo+"",
                DeviceFile.TYPE_USER_TICKET_DB_ARRAY,
                null)
        ));
    }

}
