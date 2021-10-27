package com.pyrion.game.lotto_shopping.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pyrion.game.lotto_shopping.histroy.AdapterBoughtTickets;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class SharedPref {
    public static Gson gson;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static Type boughtTicketType = new TypeToken< ArrayList<User.TicketDB> >(){}.getType();
    public static Type hashResultsType = new TypeToken< HashMap<Integer,ArrayList<Integer>> >(){}.getType();
    public static String boughtTicketKey = "week_bought_tickets";
    public static String hashResultsKey = "hash_results";

    public static AdapterBoughtTickets adapterHistory = null;

    public SharedPref(Context context) {
        gson = new Gson();
        sharedPreferences = context.getSharedPreferences("file_name_default", Context.MODE_PRIVATE); // 파일이름
        editor = sharedPreferences.edit();
    }

    public static void editData(String key, Object data){
        String stringData = gson.toJson(data);
        Log.i("ticket_D",data+"");
        editor.putString(key, stringData);
        editor.commit();
    }

    public static Object getData(String key, Type type){
        String stringData = sharedPreferences.getString(key, "null_data");
        if(stringData.equals("null_data")){
            if(key == boughtTicketKey) return new ArrayList<>();
            return null;
        }
        return gson.fromJson(stringData, type);
    }




}
