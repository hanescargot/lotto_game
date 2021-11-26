package com.pyrion.game.lotto_shopping.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;

public class WinNumDAO {
    private static HashMap<Integer, WinNumSet> winningNumberHash = new HashMap<>();  //회차:[번호]

    //서버에서 로또 번호를 불러들이면 내부저장소에 저장을 하고 내부저장소에 것을 퍼플릭으로 읽어 들여져있는건 보여준다.
    public static WinNumSet getWinNumSet(Context context, int drwNo){
        if( isStaticData(drwNo) ){
            //TODO 켜질 때 file에 있는데이터 static으로 한꺼번에 받아와 두기지만 언제 서버에서 업데이트 될지 모름으로 얘는 계속 업데이트 해줘야 함
            return winningNumberHash.get(drwNo);
            //TODO 꺼질 때 static이랑  device 데이터랑 똑같이 맞추기
        }

       if ( isDeviceFileData(context, drwNo) ){
           return  winningNumberHash.get(drwNo);
       }

       //Sever
        return getWinNumSetFromServer(context, drwNo);
    }


    //check data
    public static boolean isStaticData(int drwNo){
        if (winningNumberHash.containsKey(drwNo)) return true;
        return false;
    }

    public static boolean isDeviceFileData(Context context, int drwNo){
        WinNumSet winNumSet = (WinNumSet) (DeviceFile.getData(
                context,
                DeviceFile.FILENAME_WIN_NUM_SET,
                drwNo+"",
                DeviceFile.TYPE_WIN_NUM_SET,
                null));

        if(winNumSet == null) return false;

        //add to static
        winningNumberHash.put(drwNo,winNumSet);
        return true;
    }

    private static WinNumSet getWinNumSetFromServer(Context context, int drwNo) {
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        String[] key = {"drwtNo1", "drwtNo2", "drwtNo3", "drwtNo4", "drwtNo5", "drwtNo6"};
        String url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo="+drwNo;

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        HashMap<String, Double> data = new Gson().fromJson(response, HashMap.class);
                        if (data.isEmpty()) {
                            Log.i("Error", "API Error: Data is Empty");
                            return; //Error
                        }
                        int[] numbers = new int[6];
                        for (String keyString : key) {
                            //일반 번호 추가
                            int num = data.get(keyString).intValue();
                            numbers[numbers.length]=num;
                        }
                        int bNum = data.get("bnusNo").intValue();
                        WinNumSet winNumSet = new WinNumSet(numbers, bNum);
                        winningNumberHash.put(drwNo, winNumSet);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error", "API Error: Error Response");
                    }
                }
        );
            
        synchronized (context) {
            //TODO 교수님께 질문 이렇게 해하면 효과가 있습니까..?
            request.setShouldCache(false);
            requestQueue.add(request);
            return winningNumberHash.get(drwNo);
        }
    }

}
