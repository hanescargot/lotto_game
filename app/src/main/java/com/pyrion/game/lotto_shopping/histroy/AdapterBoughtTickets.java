package com.pyrion.game.lotto_shopping.histroy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pyrion.game.lotto_shopping.R;
import com.pyrion.game.lotto_shopping.data.Lotto;
import com.pyrion.game.lotto_shopping.data.SixNum;
import com.pyrion.game.lotto_shopping.data.User;
import com.pyrion.game.lotto_shopping.data.UserTicketDAO;
import com.pyrion.game.lotto_shopping.data.UserTicketResultDB;

import java.util.ArrayList;

public class AdapterBoughtTickets extends RecyclerView.Adapter {
    Context context;
    TextView tvNoTickets;
    LayoutInflater inflater;
    Boolean isHistory;

    public AdapterBoughtTickets(Context context, TextView tvNoTickets){
        this.context = context;
        this.tvNoTickets = tvNoTickets;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.component_ticket_nums_item, parent, false);
        return new ViewHolder( itemView );
    }

    ArrayList<UserTicketResultDB> userTicketResultDBs;
    @Override
    public int getItemCount() {
        this.isHistory = !(Lotto.selectedDrwNo == Lotto.getThisWeekDrwNo()); //
        userTicketResultDBs = (ArrayList<UserTicketResultDB>)(UserTicketDAO.getUserTicketArr(context, Lotto.selectedDrwNo)).clone(); //회차의 티켓들
        if(userTicketResultDBs==null){tvNoTickets.setVisibility(View.VISIBLE);
        }else {tvNoTickets.setVisibility(View.INVISIBLE);}
        return userTicketResultDBs.size();

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        UserTicketResultDB userTicketResult = userTicketResultDBs.get(position) ;
        SixNum sixNum = userTicketResult.sixNum;
        for(int i=0; i<sixNum.len(); i++){
            TextView tvBall = viewHolder.getBallTextViews()[i];
            tvBall.setText(sixNum.getNumber(i));
            tvBall.setBackgroundResource(Lotto.getBgSrc( sixNum.getNumber(i) ));
        }
        if(isHistory){
            //당첨결과 발표된 회차의 사용자 티켓
            String result = userTicketResult.getRankingStr();
            viewHolder.tvResult.setText(result);
        }else{
            //아직 당첨결과 발표되지 않은 회차
            viewHolder.tvResult.setText("대기중");

        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView[] tvBalls = new TextView[6];
        TextView tvResult;
        public ViewHolder(View itemView) {
            super(itemView);
            for(int i=0; i<6; i++){
                tvBalls[i] = itemView.findViewById(R.id.tv_ball_1+i);
            }
            tvResult = itemView.findViewById(R.id.tv_result);
        }
        public TextView[] getBallTextViews() {
            return tvBalls;
        }
    }
}
