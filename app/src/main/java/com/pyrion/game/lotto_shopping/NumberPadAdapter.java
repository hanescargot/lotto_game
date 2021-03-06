package com.pyrion.game.lotto_shopping;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pyrion.game.lotto_shopping.data.Ticket;

import java.util.ArrayList;

public class NumberPadAdapter extends BaseAdapter {
    Integer[] selectableNumbers = new Integer[45];

    Context context;
    ImageView btnBuy;
    View btnSearch;
    Boolean isResearchNumPad = false;


    ArrayList<Integer> numberPadNumAddress;  // 6개 체크 가능

    public NumberPadAdapter(Context context, View btn){
        //연구 페이지에 있는 넘패드
        isResearchNumPad = true;
        this.context = context;
        for(int temp = 0; temp<selectableNumbers.length; temp++){
            selectableNumbers[temp] = temp;
        }
        btnSearch = btn;
        numberPadNumAddress = Ticket.researchNumberPadNum;
    }
    public NumberPadAdapter(Context context, ImageView buyBtn){

        this.context = context;
        this.btnBuy = buyBtn;
        for(int temp = 0; temp<selectableNumbers.length; temp++){
            selectableNumbers[temp] = temp;
        }
        numberPadNumAddress = Ticket.buyNumberPadNum;

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
                        if(isResearchNumPad){
                            btnSearch.setBackgroundResource(R.drawable.auction_round_box_full_subblue);
                            btnSearch.setTag("unactivated");
                        }else {
                            // button 비활성화
                            btnBuy.setImageResource(R.drawable.ic_red_button_grey);
                        }
                    }
                    //이미 체크되어 있었던 것 클릭
                    iv.setVisibility(View.INVISIBLE);
                    if(numberPadNumAddress.contains(checkedNum)){
                        numberPadNumAddress.contains(numberPadNumAddress.indexOf(checkedNum));
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
                    if(isResearchNumPad){
                        //todo
                        btnSearch.setBackgroundResource(R.drawable.clickable_btn_blue);
                        btnSearch.setTag("activated");
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
