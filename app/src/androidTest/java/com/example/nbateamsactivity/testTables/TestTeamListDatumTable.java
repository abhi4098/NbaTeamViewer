package com.example.nbateamsactivity.testTables;




import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.example.nbateamsactivity.generated.model.Player;
import com.example.nbateamsactivity.generated.tables.PlayerTable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
@Table(name = "TestTeamListDatumTable")
public class TestTeamListDatumTable extends Model {

    @Column(name="wins")
    public int wins;

    @Column(name="losses")
    public int losses;

    @Column(name="full_name")
    public String fullName;

    @Column(name="t_id")
    public int t_id;

    @Column(name="players")
    public List<TestPlayerTable> players = new ArrayList<>();

    public TestTeamListDatumTable() {
        super();
    }

    public TestTeamListDatumTable( int wins, int losses, String fullName,int t_id,List<TestPlayerTable> players ) {
        super();

        this.wins = wins;
        this.losses = losses;
        this.fullName = fullName;
        this.t_id =t_id;
        this.players = players;
    }
    public TestTeamListDatumTable( int wins, int losses, String fullName,int t_id) {
        super();

        this.wins = wins;
        this.losses = losses;
        this.fullName = fullName;
        this.t_id =t_id;

    }



}
