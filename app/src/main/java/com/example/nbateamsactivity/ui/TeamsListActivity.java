package com.example.nbateamsactivity.ui;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.example.nbateamsactivity.R;
import com.example.nbateamsactivity.adapters.NbaTeamAdapter;

import com.example.nbateamsactivity.generated.tables.TeamListDatumTable;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.activeandroid.Cache.getContext;


public class TeamsListActivity extends AppCompatActivity {


    @BindView(R.id.back_icon)
    ImageView ivBackIcon;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_app_title)
    TextView tvAppTitle;
    @BindView(R.id.team_recycler_view)
    RecyclerView recyclerView ;
    @BindView(R.id.spinner)
    Spinner spSort ;

    Context mContext;
    ArrayList<TeamListDatumTable> showTeamList = null;
    NbaTeamAdapter mAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = TeamsListActivity.this;
        tvAppTitle.setText("NBA Teams List");
        ivBackIcon.setVisibility(View.INVISIBLE);
        spSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                if(selected.equals("Wins"))
                {
                    SortShowTeamListByWins();
                    mAdapter.notifyDataSetChanged();

                }
                else if(selected.equals("Lost"))
                {
                    SortShowTeamListByLost();
                    mAdapter.notifyDataSetChanged();
                }
                else
                {
                    SortShowTeamListByName();
                    mAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        preparedListItem();

        }


    public static List<TeamListDatumTable> getAll() {
        return new Select()
                .from(TeamListDatumTable.class)
                .execute();
    }


    private void preparedListItem() {

        final List<TeamListDatumTable> teamListData = getAll();

        showTeamList = new ArrayList<>();

                for (int i = 0; i < teamListData.size(); i++) {

                    TeamListDatumTable postItem = new TeamListDatumTable();
                    postItem.fullName = teamListData.get(i).fullName;
                    postItem.wins = teamListData.get(i).wins;
                    postItem.losses = teamListData.get(i).losses;
                    postItem.t_id = teamListData.get(i).t_id;
                    showTeamList.add(postItem);
                   Log.e("abhi", "preparedListItem: " +showTeamList.get(i).players );
                }

               SortShowTeamListByName();
                sendToAdapter();



            }


    private void sendToAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NbaTeamAdapter(showTeamList, R.layout.layout_nba_teams_list, getApplicationContext());
        recyclerView.setAdapter(mAdapter);
    }


    private void SortShowTeamListByName() {
        Collections.sort(showTeamList, new Comparator<TeamListDatumTable>(){
            public int compare(TeamListDatumTable obj1, TeamListDatumTable obj2) {
                // ## Ascending order
                return obj1.fullName.compareToIgnoreCase(obj2.fullName); // To compare string values

            }
        });
    }

    private void SortShowTeamListByWins() {
        Collections.sort(showTeamList, new Comparator<TeamListDatumTable>(){
            public int compare(TeamListDatumTable obj1, TeamListDatumTable obj2) {

                return Integer.valueOf(obj2.wins).compareTo(obj1.wins); // To compare string values

            }
        });
    }


    private void SortShowTeamListByLost() {
        Collections.sort(showTeamList, new Comparator<TeamListDatumTable>(){
            public int compare(TeamListDatumTable obj1, TeamListDatumTable obj2) {

                return Integer.valueOf(obj2.losses).compareTo(obj1.losses);  // To compare string values

            }
        });
    }



}

