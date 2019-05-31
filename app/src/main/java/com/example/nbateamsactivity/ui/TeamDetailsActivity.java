package com.example.nbateamsactivity.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.example.nbateamsactivity.R;
import com.example.nbateamsactivity.adapters.NbaTeamDetailAdapter;
import com.example.nbateamsactivity.generated.tables.PlayerTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TeamDetailsActivity extends AppCompatActivity {
    String nbaTeamId;
    String teamWins;
    String teamLosses;
    String teamName;
    List<PlayerTable> teamPlayers;
    Context mContext;

    @BindView(R.id.back_icon)
    ImageView ivBackIcon;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_app_title)
    TextView tvAppTitle;
    @BindView(R.id.team_recycler_view)
    RecyclerView recyclerView ;

    @BindView(R.id.title)
    TextView tvTeamTitle;

    @BindView(R.id.tvWins)
    TextView tvTeamWins;

    @BindView(R.id.tvLost)
    TextView tvTeamLosses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nbaTeamId = extras.getString("team_id");
            teamWins = extras.getString("team_Wins");
            teamLosses = extras.getString("team_losses");
            teamName = extras.getString("team_Name");


        }

        mContext = TeamDetailsActivity.this;
        tvAppTitle.setText("NBA Team Detail");
        tvTeamTitle.setText(teamName);
        tvTeamWins.setText(teamWins);
        tvTeamLosses.setText(teamLosses);

        getPlayersList();

    }

    @OnClick(R.id.back_icon)
    public void goBack()
    {
        super.onBackPressed();
    }

    private void getPlayersList() {


        final List<PlayerTable> teamListData = getAll();
        teamPlayers = new ArrayList<>();
        for(int i = 0 ; i<teamListData.size(); i++)
        {

            if(teamListData.get(i).teamId ==Integer.valueOf(nbaTeamId) )
            {
                PlayerTable playerTable = new PlayerTable();
                playerTable.firstName = teamListData.get(i).firstName;
                playerTable.lastName = teamListData.get(i).lastName;
                playerTable.position = teamListData.get(i).position;
                playerTable.number = teamListData.get(i).number;

                teamPlayers.add(playerTable);


            }
        }
        Collections.sort(teamPlayers, new Comparator<PlayerTable>(){
            public int compare(PlayerTable obj1, PlayerTable obj2) {
                // ## Ascending order
                return obj1.firstName.compareToIgnoreCase(obj2.firstName); // To compare string values
                // return Integer.valueOf(obj1.getId()).compareTo(obj2.getId()); // To compare integer values

                // ## Descending order
                // return obj2.getCompanyName().compareToIgnoreCase(obj1.getCompanyName()); // To compare string values
                // return Integer.valueOf(obj2.getId()).compareTo(obj1.getId()); // To compare integer values
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new NbaTeamDetailAdapter(teamPlayers, R.layout.layout_nba_teams_detail_list, getApplicationContext()));

    }



    public static List<PlayerTable> getAll() {
        return new Select()
                .from(PlayerTable.class)
                .execute();
    }

}
