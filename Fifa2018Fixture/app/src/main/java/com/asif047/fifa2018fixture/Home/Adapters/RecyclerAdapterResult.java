package com.asif047.fifa2018fixture.Home.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asif047.fifa2018fixture.BaseUrl;
import com.asif047.fifa2018fixture.Home.Model.Result;
import com.asif047.fifa2018fixture.Interface.ItemClickListener;
import com.asif047.fifa2018fixture.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapterResult extends RecyclerView.Adapter<RecyclerAdapterResult.MyViewHolder> {

    private List<Result> results;
    private Context context;

    public RecyclerAdapterResult(Context context, List<Result> results) {
        this.results = results;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_result,
                                parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tvTeam1.setText(results.get(position).getTeam1());
        holder.tvTeam2.setText(results.get(position).getTeam2());
        holder.tvScore1.setText(results.get(position).getScore1());
        holder.tvScore2.setText(results.get(position).getScore2());
        holder.tvStatus.setText(results.get(position).getStatus());
        holder.tvMatchNo.setText(""+(position+1));

        Picasso.with(context).load(BaseUrl.BASE_URL_APP+"group/Image/"+
                results.get(position).getImg1()+".png").into(holder.ivTeam1);

        Picasso.with(context).load(BaseUrl.BASE_URL_APP+"group/Image/"+
                results.get(position).getImg2()+".png").into(holder.ivTeam2);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemClickListener itemClickListener;
        TextView tvTeam1, tvTeam2, tvScore1, tvScore2, tvStatus, tvMatchNo;
        ImageView ivTeam1, ivTeam2;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvTeam1 = itemView.findViewById(R.id.textview_team1_result);
            tvTeam2 = itemView.findViewById(R.id.textview_team2_result);
            tvScore1 = itemView.findViewById(R.id.textview_score1_result);
            tvScore2 = itemView.findViewById(R.id.textview_score2_result);
            tvStatus = itemView.findViewById(R.id.textview_status);
            tvMatchNo = itemView.findViewById(R.id.match_no_result);

            ivTeam1 = itemView.findViewById(R.id.imageview_team1_result);
            ivTeam2 = itemView.findViewById(R.id.imageview_team2_result);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
