package com.pyrion.game.lotto_shopping.research;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pyrion.game.lotto_shopping.R;


public class AdapterRecyclerViewRanking extends RecyclerView.Adapter {
    String[] itemList = new String[]{"1","2","3","4","5"};
    LayoutInflater inflater;
    Context context;
    public AdapterRecyclerViewRanking(Context context) {
        inflater= LayoutInflater.from(context);
        this.context = context;
//        Toast.makeText(context, "maked!!!!!!!!!!", Toast.LENGTH_SHORT).show();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.ranking_recycler_veiw_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        //todo view 값 setting 하기

    }

    @Override
    public int getItemCount() {
        return itemList.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView ivBall;

        public ImageView getIvBall() {
            return ivBall;
        }

        public TextView getTvRanking() {
            return tvRanking;
        }

        public TextView getTvCount() {
            return tvCount;
        }

        private final TextView tvRanking, tvCount;

        public ViewHolder(View itemView) {
            super(itemView);

            ivBall = itemView.findViewById(R.id.iv_ball);
            tvRanking = itemView.findViewById(R.id.tv_ranking);
            tvCount = itemView.findViewById(R.id.tv_count);
        }
    }

}
