package com.example.nbateamsactivity.adapters;

import android.content.Context;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nbateamsactivity.R;
import com.example.nbateamsactivity.generated.tables.PlayerTable;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NbaTeamDetailAdapter extends RecyclerView.Adapter<NbaTeamDetailAdapter.NbaTeamViewHolder> {

    private List<PlayerTable> teamList;
    private int rowLayout;
    private Context context;


    public static class NbaTeamViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.team_Detail_layout)
        LinearLayout NbaTeamLayout;


        @BindView(R.id.tv_first_name)
        TextView tvFirstName;

        @BindView(R.id.tv_last_name)
        TextView tvLastName;

        @BindView(R.id.tv_number)
        TextView tvNumber;

        @BindView(R.id.tv_position)
        TextView tv_position;



        public NbaTeamViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
        }
    }

    public NbaTeamDetailAdapter(List<PlayerTable> teamList, int rowLayout, Context context) {
        this.teamList = teamList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public NbaTeamDetailAdapter.NbaTeamViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);

        return new NbaTeamViewHolder(view);
    }


    @Override
    public void onBindViewHolder(NbaTeamViewHolder holder, final int position) {
        holder.tvFirstName.setText(teamList.get(position).firstName);
        holder.tvLastName.setText(String.valueOf(teamList.get(position).lastName));
        holder.tv_position.setText(String.valueOf(teamList.get(position).position));
        holder.tvNumber.setText(String.valueOf(teamList.get(position).number));

    }
        

    @Override
    public int getItemCount() {
        return teamList.size();
    }

}
