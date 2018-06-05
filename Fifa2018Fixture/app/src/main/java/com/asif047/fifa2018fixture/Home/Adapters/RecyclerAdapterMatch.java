package com.asif047.fifa2018fixture.Home.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asif047.fifa2018fixture.BaseUrl;
import com.asif047.fifa2018fixture.Home.Model.Match;
import com.asif047.fifa2018fixture.Interface.ItemClickListener;
import com.asif047.fifa2018fixture.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by admin on 6/4/2018.
 */

public class RecyclerAdapterMatch extends RecyclerView.Adapter<RecyclerAdapterMatch.MyViewHolder> {

    private List<Match> matches;
    private Context context;

    public RecyclerAdapterMatch( Context context, List<Match> matches) {
        this.matches = matches;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_match,
                                parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tvDate.setText(matches.get(position).getDate());
        holder.tvTeam1.setText(matches.get(position).getTeam1());
        holder.tvTeam2.setText(matches.get(position).getTeam2());
        holder.tvVenue.setText(matches.get(position).getVenue());

        Picasso.with(context).load(BaseUrl.BASE_URL_APP+"group/Image/"+
                matches.get(position).getImg1()+".png").into(holder.ivTeam1);

        Picasso.with(context).load(BaseUrl.BASE_URL_APP+"group/Image/"+
                matches.get(position).getImg2()+".png").into(holder.ivTeam2);

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemClickListener itemClickListener;
        TextView tvDate, tvTeam1, tvTeam2, tvVenue;
        ImageView ivTeam1, ivTeam2;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.textview_date_match);
            tvTeam1 = itemView.findViewById(R.id.textview_team1_match);
            tvTeam2 = itemView.findViewById(R.id.textview_team2_match);
            tvVenue = itemView.findViewById(R.id.textview_venue);

            ivTeam1 = itemView.findViewById(R.id.imageview_team1_match);
            ivTeam2 = itemView.findViewById(R.id.imageview_team2_match);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            this.itemClickListener.onItemClick(this.getLayoutPosition());

        }
    }


}
