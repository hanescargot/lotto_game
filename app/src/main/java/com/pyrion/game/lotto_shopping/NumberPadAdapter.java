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

import com.pyrion.game.lotto_shopping.data.NumberPad;

public class NumberPadAdapter extends BaseAdapter {
    SharedPreferences sharedPref;
    Integer[] selectableNumbers = new Integer[45];

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

        Log.i("num_arr_R",NumberPad.buyNumbers+"");
        int checkedNum = Integer.parseInt(textView.getText().toString());
        Log.i("contain", checkedNum +"");
        iv.setVisibility(View.INVISIBLE);
        if (NumberPad.buyNumbers.contains(checkedNum)){
            iv.setVisibility(View.VISIBLE);
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checked_num = Integer.parseInt( ((TextView)v).getText().toString() );
                if(iv.getVisibility()==View.VISIBLE){
                    //체크했던 것 풀기
                    iv.setVisibility(View.INVISIBLE);
                    if(NumberPad.buyNumbers.indexOf(checked_num)!=-1){
                        NumberPad.buyNumbers.remove(NumberPad.buyNumbers.indexOf(checked_num));
                    }
                }
                else if (NumberPad.buyNumbers.size()>6){
                        //7개 모두 체크함
                        showToast("7개 선택 가능");
                        return;
                }else{
                    iv.setVisibility(View.VISIBLE);
                    NumberPad.buyNumbers.add(checked_num);
                }
            }
        });
        return convertView;
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
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();
    }

}
