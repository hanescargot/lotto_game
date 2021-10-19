package com.pyrion.game.lotto_shopping;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class NumberPadAdapter extends BaseAdapter {
    SharedPreferences sharedPref;
    Integer[] selectableNumbers = new Integer[45];

    ArrayList<Integer> prefNumArray;

    Context context;
    public NumberPadAdapter(Context context){
        for(int temp = 0; temp<selectableNumbers.length; temp++){
            selectableNumbers[temp] = temp;
        }
        this.context = context;
    }

    @Override
    public int getCount() {
        return selectableNumbers.length;
    }

    @Override
    public Object getItem(int position) {
        return selectableNumbers[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.component_number_check_pad_item, null);
        }
        sharedPref = context.getSharedPreferences("buy", Context.MODE_PRIVATE); // 파일이름 'buy'

        TextView textView = convertView.findViewById(R.id.key_number);
        textView.setText((position+1)+"");
        ImageView iv = convertView.findViewById(R.id.iv);


        prefNumArray = getPrefNumArr();
        Log.i("num_arr_R",prefNumArray+"");
        int checked_num = Integer.parseInt(textView.getText().toString());
        Log.i("contain", checked_num+"");
        if (prefNumArray.contains(checked_num)){
            iv.setVisibility(View.VISIBLE);
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checked_num = Integer.parseInt( ((TextView)v).getText().toString() );
                if(iv.getVisibility()==View.VISIBLE){
                    //체크했던 것 풀기
                    iv.setVisibility(View.INVISIBLE);
                    if(prefNumArray.indexOf(checked_num)!=-1){
                        prefNumArray.remove(prefNumArray.indexOf(checked_num));
                    }
                }
                else if (prefNumArray.size()>6){
                        //7개 모두 체크함
                        showToast("7개 선택 가능");
                        return;
                }else{
                        iv.setVisibility(View.VISIBLE);
                        prefNumArray.add(checked_num);
                }

                // data update
                String data = new Gson().toJson(prefNumArray);
                Log.i("num_arr_update", data);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.clear();
                editor.putString("num_array", data );
                editor.commit();
            }
        });
        return convertView;
    }

    public ArrayList<Integer> getPrefNumArr(){
        String str = sharedPref.getString("num_array","null");
        Log.i("num_arr",str);
        if (str.equals("null")){return new ArrayList<Integer>();}

        Type setType = new TypeToken<ArrayList<Integer>>(){}.getType();
        return new Gson().fromJson(str,setType);
    }

    Toast toast;
    public void showToast( String str ){
        if(toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(
                context,
                str,
                Toast.LENGTH_SHORT
        );
//        toast.setGravity(Gravity.CENTER_VERTICAL, 0 , 200);
        toast.show();
    }

}
