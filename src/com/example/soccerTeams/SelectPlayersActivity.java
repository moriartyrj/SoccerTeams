package com.example.soccerTeams;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rmoriarty
 * Date: 1/11/14
 * Time: 9:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class SelectPlayersActivity extends ListActivity {

    private DBAdapter adapter;

    private static final int ID_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int RANK_INDEX = 2;
    private static final int PLACEMENTS_INDEX = 3;

    public List<Player> players;
    public List<Player> selectedPlayers;
    int selectedPlayersRankTotal;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_select);

        adapter = new DBAdapter(this);
        players = adapter.getAllPlayers();
        selectedPlayers = new ArrayList<Player>();

        selectedPlayersRankTotal = 0;

        Button makeTeamsButton = (Button) findViewById(R.id.buttonMakeTeams);

        makeTeamsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeTeams();
            }
        });

        resetListAdapter();
        //Cursor playerCursor = adapter.getAllPlayers();

        //android.widget.ListAdapter listAdapter = new SimpleCursorAdapter(this,android.R.layout.,playerCursor,
        //            new String[] {"player.name","player.rank"}, new int[] {android.R.id.text1, android.R.id.text2 },0);

        //playerCursor = adapter.getAllPlayers();

        //setListAdapter(new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_1, players));
    }

    public void makeTeams() {
        //int teamCount = (int)Math.ceil((double)selectedPlayers.size() / 5);
        EditText teamCountText = (EditText) findViewById(R.id.teamCount);
        try {
            int teamCount = Integer.parseInt(teamCountText.getText().toString());
            float teamAvg = selectedPlayersRankTotal / teamCount;

            Collections.sort(selectedPlayers);

            int currentTeamIndex = 0;
            /*
            List<List<Player>> teams = new ArrayList<List<Player>>();

            for(int i = 0 ; i < teamCount ; i++) {
                teams.add(new ArrayList<Player>());
            }

            for (Player player : selectedPlayers) {
                teams.get(currentTeamIndex).add(player);

                currentTeamIndex++;
                if(currentTeamIndex == teamCount) {
                    currentTeamIndex = 0;
                }
            }*/

            List<Team> teamList = new ArrayList<Team>();

            for(int i = 0 ; i < teamCount ; i++) {
                teamList.add(new Team());
            }

            for (Player player : selectedPlayers) {
                teamList.get(currentTeamIndex).addPlayer(player);

                player.setTeam(currentTeamIndex + 1);

                currentTeamIndex++;
                if(currentTeamIndex == teamCount) {
                    currentTeamIndex = 0;
                }
            }

            Collections.sort(players);

            resetListAdapter();
        } catch (Exception e) {
            return;
        }

        return;
    }

    public void resetListAdapter() {
        ListAdapter listAdapter = new ListAdapter(players, this);

        setListAdapter(listAdapter);

        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox box = (CheckBox) view.findViewById(R.id.selected);
                box.setChecked(!box.isChecked());

                Player clickedPlayer = players.get(position);
                clickedPlayer.setSelected(!clickedPlayer.getSelected());

                if(clickedPlayer.selected)  {
                    selectedPlayersRankTotal += clickedPlayer.rank;
                    selectedPlayers.add(clickedPlayer);
                } else {
                    selectedPlayersRankTotal -= clickedPlayer.rank;
                    selectedPlayers.remove(clickedPlayer);
                }
            }
        });
    }
}
