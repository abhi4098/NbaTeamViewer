package com.example.nbateamsactivity.ui;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;

import com.activeandroid.query.Select;
import com.example.nbateamsactivity.R;

import com.example.nbateamsactivity.testTables.TestPlayerTable;
import com.example.nbateamsactivity.testTables.TestTeamListDatumTable;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<SplashActivity>(SplashActivity.class);
     SplashActivity mActivity = null;
     List<TestPlayerTable> players = null;
     TestTeamListDatumTable mInstance = null;
     TestPlayerTable playerTables = null;
    List<TestTeamListDatumTable> retrievedTeam = null;




    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.nbateamsactivity", appContext.getPackageName());
    }

        @Test
    public void saveToDatabase() throws Exception {
        int wins = 10;
        int losses = 5;
        String fullName = "Tom";
        int t_id = 2;
         players = new ArrayList<>();

        playerTables = new TestPlayerTable();

        playerTables.p_id= 1;
        playerTables.firstName = "Ben";
        playerTables.lastName = "Stokes";
        playerTables.position = "7";
        playerTables.number = 8;
        playerTables.teamId = 2;
        playerTables.save();
        players.add(playerTables);



        mInstance= new TestTeamListDatumTable(wins, losses,fullName,t_id,players);
        mInstance.save();





        assertEquals(fullName, mInstance.fullName);
        assertEquals(wins, mInstance.wins);
        assertEquals(losses, mInstance.losses);
        assertEquals(players,mInstance.players);


    }

    @Test
    public void retrieveFromDatabase()

    {

        int wins = 10;
        int losses = 5;
        String fullName = "Tom";
        int t_id = 2;
        players = new ArrayList<>();

        playerTables = new TestPlayerTable();

        playerTables.p_id= 1;
        playerTables.firstName = "Ben";
        playerTables.lastName = "Stokes";
        playerTables.position = "7";
        playerTables.number = 8;
        playerTables.teamId = 2;
        playerTables.save();
        players.add(playerTables);


         retrievedTeam= new Select()
                .from(TestTeamListDatumTable.class)
                .execute();
        Log.e("abhi", "retrieveFromDatabase: ....." +retrievedTeam.size());
        for(int i=0; i<retrievedTeam.size(); i++)
        {
            assertEquals(fullName, retrievedTeam.get(i).fullName);
            assertEquals(wins, retrievedTeam.get(i).wins);
            assertEquals(losses, retrievedTeam.get(i).losses);
            for(int k = 0; k<retrievedTeam.get(i).players.size(); k++)
            {
                assertEquals(players.get(k).p_id, retrievedTeam.get(i).players.get(i).p_id);
                assertEquals(players.get(k).firstName, retrievedTeam.get(i).players.get(i).firstName);
                assertEquals(players.get(k).lastName, retrievedTeam.get(i).players.get(i).lastName);
                assertEquals(players.get(k).number, retrievedTeam.get(i).players.get(i).number);
                assertEquals(players.get(k).teamId, retrievedTeam.get(i).players.get(i).teamId);
                assertEquals(players.get(k).position, retrievedTeam.get(i).players.get(i).position);
            }

        }



    }


    @Test
    public void testLaunch ()
    {
        View view =mActivity.findViewById(R.id.splash_screen);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {


        mActivity = null;
         players = null;
        mInstance = null;
        playerTables = null;

    }
}