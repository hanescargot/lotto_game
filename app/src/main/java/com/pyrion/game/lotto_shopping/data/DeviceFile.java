package com.pyrion.game.lotto_shopping.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pyrion.game.lotto_shopping.histroy.AdapterBoughtTickets;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class DeviceFile {

    public static String FILENAME_WIN_NUM_SET = "win_num_set";

    public static String FILENAME_TICKET_NUM_SET_ARRAY = "ticket_num_set_array";
    public static String boughtTicketKey = "week_bought_tickets";

    public static Type TYPE_BOUGHT_TICKET = new TypeToken< ArrayList<User.TicketDB> >(){}.getType();
    public static Type TYPE_WIN_NUM_SET = new TypeToken< WinNumSet >(){}.getType();
    public static Type TYPE_TICKET_NUM_SET_ARRAY = new TypeToken< ArrayList<TicketNumSet> >(){}.getType();


    public static AdapterBoughtTickets adapterHistory = null;

    public static SharedPreferences getSharedPref(Context context, String fileName) {
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }


    //Read
    public static Object getData(Context context, String fileName, String key, Type dataType, Object defValue){
        String stringData = getSharedPref(context, fileName).getString(key, "null_data");
        if(stringData.equals("null_data")){
            return defValue;
        }
        return new Gson().fromJson(stringData, dataType);
    }

    //Update(insert, edit)
    public static void editData(Context context,String fileName, String key, Object data){
        SharedPreferences.Editor editor = getSharedPref(context, fileName).edit();
        String stringData = new Gson().toJson(data);
        editor.putString(key, stringData);
        editor.apply();
    }

//    //Delete
//    public void delData(Context context, String key){
//        SharedPreferences.Editor editor = getSharedPref(context).edit();
//        editor.remove(key).commit();
//    }

}
