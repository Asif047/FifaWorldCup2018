package com.asif047.fifa2018fixture.Home.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asif047.fifa2018fixture.BaseUrl;
import com.asif047.fifa2018fixture.Home.Model.Group;
import com.asif047.fifa2018fixture.Interface.ItemClickListener;
import com.asif047.fifa2018fixture.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapterGroup extends RecyclerView.Adapter<RecyclerAdapterGroup.MyViewHolder> {

    private List<Group> groups;
    private Context context;

    public RecyclerAdapterGroup( Context context, List<Group> groups) {
        this.groups = groups;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_group,
                                parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tvTeam1.setText(groups.get(position).getTeam1());
        holder.tvTeam2.setText(groups.get(position).getTeam2());
        holder.tvTeam3.setText(groups.get(position).getTeam3());
        holder.tvTeam4.setText(groups.get(position).getTeam4());

        holder.tvGroupName.setText(groups.get(position).getGroupName());

        Picasso.with(context).load(BaseUrl.BASE_URL_APP+"group/Image/"+groups.get(position)
                .getImg1()+".png").into(holder.ivTeam1);
        Picasso.with(context).load(BaseUrl.BASE_URL_APP+"group/Image/"+groups.get(position)
                .getImg2()+".png").into(holder.ivTeam2);
        Picasso.with(context).load(BaseUrl.BASE_URL_APP+"group/Image/"+groups.get(position)
                .getImg3()+".png").into(holder.ivTeam3);
        Picasso.with(context).load(BaseUrl.BASE_URL_APP+"group/Image/"+groups.get(position)
                .getImg4()+".png").into(holder.ivTeam4);

    }

    @Override
    public int getItemCount() {
        return groups.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemClickListener itemClickListener;
        TextView tvTeam1, tvTeam2, tvTeam3, tvTeam4, tvGroupName;
        ImageView ivTeam1, ivTeam2, ivTeam3, ivTeam4;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvTeam1 = itemView.findViewById(R.id.textview_team1);
            tvTeam2 = itemView.findViewById(R.id.textview_team2);
            tvTeam3 = itemView.findViewById(R.id.textview_team3);
            tvTeam4 = itemView.findViewById(R.id.textview_team4);
            tvGroupName = itemView.findViewById(R.id.group_name);

            ivTeam1 = itemView.findViewById(R.id.imageview_team1);
            ivTeam2 = itemView.findViewById(R.id.imageview_team2);
            ivTeam3 = itemView.findViewById(R.id.imageview_team3);
            ivTeam4 = itemView.findViewById(R.id.imageview_team4);

        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }
    }

}
