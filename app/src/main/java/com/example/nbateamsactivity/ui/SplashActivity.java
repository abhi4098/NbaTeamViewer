package com.example.nbateamsactivity.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.activeandroid.query.Delete;
import com.example.nbateamsactivity.R;
import com.example.nbateamsactivity.api.ApiAdapter;
import com.example.nbateamsactivity.api.RetrofitInterface;
import com.example.nbateamsactivity.generated.model.Player;
import com.example.nbateamsactivity.generated.model.TeamListDatum;
import com.example.nbateamsactivity.generated.tables.PlayerTable;
import com.example.nbateamsactivity.generated.tables.TeamListDatumTable;
import com.example.nbateamsactivity.utils.LoadingDialog;
import com.example.nbateamsactivity.utils.NetworkUtils;


import android.content.Context;
import android.content.Intent;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.nbateamsactivity.api.ApiEndPoints.BASE_URL;


public class SplashActivity extends AppCompatActivity {

    Context mContext;

    private RetrofitInterface.UserLoginClient UserLoginAdapter;
    ArrayList<TeamListDatumTable> teamListData;
    ArrayList<PlayerTable> playersInfoTable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        mContext = SplashActivity.this;
        calltoSplash();
    }


    public void calltoSplash() {
        LoadData();


    }


    private void LoadData() {

        setUpRestAdapter();
        getTeamsList();

    }

    private void getTeamsList() {
        LoadingDialog.showLoadingDialog(this,"Sync Data...");
        Call<List<TeamListDatum>> call = UserLoginAdapter.listRepos();
        if (NetworkUtils.isNetworkConnected(this)) {
            call.enqueue(new Callback<List<TeamListDatum>>() {

                @Override
                public void onResponse(Call<List<TeamListDatum>> call, Response<List<TeamListDatum>> response) {
                    if (response.isSuccessful()) {

                        saveTeamListData(response);


                        LoadingDialog.cancelLoading();
                    }
                }

                @Override
                public void onFailure(Call<List<TeamListDatum>> call, Throwable t) {


                    LoadingDialog.cancelLoading();
                }


            });


        }
        else {
            openNextActivity();

            LoadingDialog.cancelLoading();
        }
    }

    private void saveTeamListData(Response<List<TeamListDatum>> response) {
        new Delete().from(TeamListDatumTable.class).execute();
        new Delete().from(PlayerTable.class).execute();
        teamListData = new ArrayList<>();

        for(int i=0; i<response.body().size(); i++)
        {

            TeamListDatumTable teamListDatumTable = new TeamListDatumTable();

            List<Player> player = response.body().get(i).getPlayers();
            playersInfoTable = new ArrayList<>();
            for(int j=0; j<response.body().get(i).getPlayers().size(); j++)
            {

                PlayerTable  playerTables = new PlayerTable();

                playerTables.p_id= player.get(j).getId();
                playerTables.firstName = player.get(j).getFirstName();
                playerTables.lastName = player.get(j).getLastName();
                playerTables.position = player.get(j).getPosition();
                playerTables.number = player.get(j).getNumber();
                playerTables.teamId = response.body().get(i).getId();
                playerTables.save();
                playersInfoTable.add(playerTables);


            }



            teamListDatumTable.wins = response.body().get(i).getWins();
            teamListDatumTable.losses = response.body().get(i).getLosses();
            teamListDatumTable.fullName = response.body().get(i).getFullName();
            teamListDatumTable.t_id = response.body().get(i).getId();
            teamListDatumTable.players =  playersInfoTable;


            teamListDatumTable.save();

            teamListData.add(teamListDatumTable);





        }

        openNextActivity();




    }

    private void openNextActivity() {
        Intent i = new Intent(SplashActivity.this, TeamsListActivity.class);

        startActivity(i);
        finish();
    }

    private void setUpRestAdapter() {
        UserLoginAdapter = ApiAdapter.createRestAdapter(RetrofitInterface.UserLoginClient.class, BASE_URL, this);

    }

}


