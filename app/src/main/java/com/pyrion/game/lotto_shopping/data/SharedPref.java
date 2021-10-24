package com.pyrion.game.lotto_shopping.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Adapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pyrion.game.lotto_shopping.histroy.AdapterBoughtTickets;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPref {
    public static Gson gson;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static Type ticketType = new TypeToken< ArrayList<ArrayList<Integer>> >(){}.getType();
    public static String ticketKey = "week_bought_tickets";
    public static AdapterBoughtTickets adapterHistory = null;

    public SharedPref(Context context) {
        gson = new Gson();
        sharedPreferences = context.getSharedPreferences("file_name_default", Context.MODE_PRIVATE); // 파일이름
        editor = sharedPreferences.edit();
    }

    public static void editData(String key, Object data){
        String stringData = gson.toJson(data);
        editor.putString(key, stringData);
        editor.commit();
    }

    public static Object getData(String key, Type type){
        String stringData = sharedPreferences.getString(key, "null_data");
        Log.i("sf", stringData);
        if(stringData.equals("null_data")){
            if(key == ticketKey) return new ArrayList<>();
            return null;
        }
        Log.i("sf", stringData+","+type);
        Log.i("sf", gson.fromJson(stringData, type)+",");
        return gson.fromJson(stringData, type);
    }

}
