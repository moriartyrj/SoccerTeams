package com.example.soccerTeams;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class soccerTeamActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private DBAdapter adapter;
    private List<Player> players;
    private List<Player> activePlayers;

    private SelectPlayersActivity selectPlayersActivity;

    private int activePlayerRankTotal;

    private static final int ID_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int RANK_INDEX = 2;
    private static final int PLACEMENTS_INDEX = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        adapter = new DBAdapter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button selectPlayersButton = (Button) findViewById(R.id.buttonSelectPlayers);
        Button managePlayersButton = (Button) findViewById(R.id.buttonManagePlayers);

        selectPlayersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectPlayersIntent = new Intent(v.getContext(),SelectPlayersActivity.class);
                startActivity(selectPlayersIntent);
            }
        });

        managePlayersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent managePlayersIntent = new Intent(v.getContext(),ManagePlayersActivity.class);
                startActivity(managePlayersIntent);
            }
        });


    }
    /*
    public void getAllPlayers() {
        Cursor playerCursor = adapter.getAllPlayers();

        while(playerCursor.moveToNext()) {
            int playerId = playerCursor.getInt(ID_INDEX);
            String name = playerCursor.getString(NAME_INDEX);
            int rank = playerCursor.getInt(RANK_INDEX);
            int placements = playerCursor.getInt(PLACEMENTS_INDEX);

            Player player = new Player(playerId, name, rank, placements);
            players.add(player);
        }
    }
    */

    public void clearPlayers() {
        players = null;
    }

    public void createPlayer(String name,int rank) {
        adapter.createPlayer(name,rank);
    }

    public void selectPlayer(Player player) {
        if(!player.selected)  {
            activePlayerRankTotal += player.rank;
            activePlayers.add(player);
        } else {
            activePlayerRankTotal -= player.rank;
            activePlayers.remove(player);
        }
    }

    public void createTeams() {
        int teamSize = (int)Math.ceil(activePlayers.size() / 5);
        float teamAvg = activePlayerRankTotal / teamSize;
    }
}
