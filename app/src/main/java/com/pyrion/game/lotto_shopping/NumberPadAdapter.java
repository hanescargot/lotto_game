package com.pyrion.game.lotto_shopping;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pyrion.game.lotto_shopping.data.NumberPad;

import java.util.ArrayList;

public class NumberPadAdapter extends BaseAdapter {
    SharedPreferences sharedPref;
    Integer[] selectableNumbers = new Integer[45];

    Context context;
    ImageView btnBuy;
    View btnSearch;
    Boolean isDialog = false;

    ArrayList<Integer> numberPadNumAddress;

    public NumberPadAdapter(Context context,View btn){
        isDialog = true;
        this.context = context;
        for(int temp = 0; temp<selectableNumbers.length; temp++){
            selectableNumbers[temp] = temp;
        }
        btnSearch = btn;
        numberPadNumAddress = NumberPad.researchNumbers;

    }
    public NumberPadAdapter(Context context, ImageView buyBtn){
        this.context = context;
        this.btnBuy = buyBtn;
        for(int temp = 0; temp<selectableNumbers.length; temp++){
            selectableNumbers[temp] = temp;
        }
        numberPadNumAddress = NumberPad.buyNumbers;

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

        int checkedNum = Integer.parseInt(textView.getText().toString());
        iv.setVisibility(View.INVISIBLE);
        if (numberPadNumAddress.contains(checkedNum)){
            iv.setVisibility(View.VISIBLE);
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedNum = Integer.parseInt( ((TextView)v).getText().toString() );
                int numCount = numberPadNumAddress.size();
                if(iv.getVisibility()==View.VISIBLE){
                    if(numCount==6){
                        if(isDialog){
                            btnSearch.setBackgroundResource(R.drawable.auction_round_box_full_subblue);
                        }else {
                            // button 비활성화
                            btnBuy.setImageResource(R.drawable.ic_red_button_grey);
                        }
                    }
                    //이미 체크되어 있었던 것 클릭
                    iv.setVisibility(View.INVISIBLE);
                    if(numberPadNumAddress.indexOf(checkedNum)!=-1){
                        numberPadNumAddress.remove(numberPadNumAddress.indexOf(checkedNum));
                    }
                    return;
                }
                if(numCount==6){
                    //6개 모두 체크 해 놓은 상태일 때
                    showToast("6개 선택 가능");
                    return;
                }

                //새 번호 체크 하기
                iv.setVisibility(View.VISIBLE);
                numberPadNumAddress.add(checkedNum);

                if(numCount==5){
                    if(isDialog){
                        //todo
                        btnSearch.setBackgroundResource(R.drawable.clickable_btn_blue);
                    }else {
                        // button 활성화
                        btnBuy.setImageResource(R.drawable.clickable_btn_red);
                    }
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
