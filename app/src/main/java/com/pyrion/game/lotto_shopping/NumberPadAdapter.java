package com.pyrion.game.lotto_shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NumberPadAdapter extends BaseAdapter {

    Integer[] selectableNumbers = new Integer[45];

    int number;
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
        TextView textView = convertView.findViewById(R.id.key_number);
        textView.setText((position+1)+"");
        return convertView;
    }
}
