package com.pyrion.game.lotto_shopping.histroy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pyrion.game.lotto_shopping.R;
import com.pyrion.game.lotto_shopping.data.Lotto;
import com.pyrion.game.lotto_shopping.data.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterBoughtTickets extends RecyclerView.Adapter {
    Context context;
    TextView tvNoTickets;
    ArrayList< ArrayList<Integer> > tickets;
    LayoutInflater inflater;
    Boolean isHistory;
    public AdapterBoughtTickets(Context context, TextView tvNoTickets){
        this.context = context;
        this.tvNoTickets = tvNoTickets;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("balls","create");
        View itemView = inflater.inflate(R.layout.component_ticket_nums_item, parent, false);
        return new ViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;

        ArrayList<Integer> balls =  tickets.get(position);
        for(int i=0; i<viewHolder.getBallTextViews().length; i++){
            TextView tvBall = viewHolder.getBallTextViews()[i];
            tvBall.setText(balls.get(i)+"");
            tvBall.setBackgroundResource(Lotto.getBgSrc( balls.get(i)) );
        }
        if(isHistory){
            //당첨결과 발표된 회차
            int resultCode = balls.get( balls.size()-1 );
            String result = Lotto.getResultString( resultCode );
            viewHolder.tvResult.setText(result);
        }else{
            //아직 당첨결과 발표되지 않은 회차
            viewHolder.tvResult.setText("대기중");

        }
    }

    @Override
    public int getItemCount() {
        this.isHistory = !(Lotto.selectedDrwNo == Lotto.latestDrwNo+1); //
        if(isHistory){
            if(User.historyBoughtTickets.containsKey(Lotto.selectedDrwNo)){
                tickets = User.historyBoughtTickets.get(Lotto.selectedDrwNo);
            }else{
                tickets = new ArrayList<>();
            }
        }else{
            tickets =  User.weekBoughtTickets;
        }

        if(tickets.size()==0){tvNoTickets.setVisibility(View.VISIBLE);
        }else {tvNoTickets.setVisibility(View.INVISIBLE);}
        return tickets.size();
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
