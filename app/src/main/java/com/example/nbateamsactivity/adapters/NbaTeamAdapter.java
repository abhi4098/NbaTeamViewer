package com.example.nbateamsactivity.adapters;

import android.content.Context;
import android.content.Intent;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import android.widget.TextView;

import com.example.nbateamsactivity.R;

import com.example.nbateamsactivity.generated.tables.TeamListDatumTable;
import com.example.nbateamsactivity.ui.TeamDetailsActivity;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NbaTeamAdapter extends RecyclerView.Adapter<NbaTeamAdapter.NbaTeamViewHolder> {

    private List<TeamListDatumTable> teamList;
    private int rowLayout;
    private Context context;


    public static class NbaTeamViewHolder extends RecyclerView.ViewHolder {



        @BindView(R.id.team_layout)
        LinearLayout NbaTeamLayout;


        @BindView(R.id.teamTitle)
        TextView nbaTeamName;

        @BindView(R.id.tvWins)
        TextView totalWins;

        @BindView(R.id.tvLost)
        TextView totalLosses;


        public NbaTeamViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
            
        }
    }

    public NbaTeamAdapter(List<TeamListDatumTable> teamList, int rowLayout, Context context) {
        this.teamList = teamList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public NbaTeamAdapter.NbaTeamViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);

        return new NbaTeamViewHolder(view);
    }


    @Override
    public void onBindViewHolder(NbaTeamViewHolder holder, final int position) {
        holder.nbaTeamName.setText(teamList.get(position).fullName);
        holder.totalWins.setText(String.valueOf(teamList.get(position).wins));
        holder.totalLosses.setText(String.valueOf(teamList.get(position).losses));
        holder.NbaTeamLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context, TeamDetailsActivity
                        .class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("team_Name", teamList.get(position).fullName);
                intent.putExtra("team_Wins", String.valueOf(teamList.get(position).wins));
                intent.putExtra("team_id", String.valueOf(teamList.get(position).t_id));
                intent.putExtra("team_losses", String.valueOf(teamList.get(position).losses));


                context.startActivity(intent);
            }
        });
    }
        

    @Override
    public int getItemCount() {
        return teamList.size();
    }

}
