package com.pyrion.game.lotto_shopping.data;

import java.util.ArrayList;

public class NumberPad {

    public static ArrayList<Integer> buyNumbers = new ArrayList<>();


    // data update
//    String data = new Gson().toJson(prefNumArray);
//                Log.i("num_arr_update", data);
//    SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putString("num_array", data );
//                editor.commit();
//    public ArrayList<Integer> getPrefNumArr(){
//        String str = sharedPref.getString("num_array","null");
//        Log.i("num_arr",str);
//        if (str.equals("null")){return new ArrayList<Integer>();}
//
//        Type setType = new TypeToken<ArrayList<Integer>>(){}.getType();
//        return new Gson().fromJson(str,setType);
//    void editPref(String key,Object numArray){
//        String strNumArray = new Gson().toJson(numArray);
//
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("buy", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(key, strNumArray );
//        editor.commit();
//    }
//    }
}
