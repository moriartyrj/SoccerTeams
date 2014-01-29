package com.example.soccerTeams;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rmoriarty
 * Date: 1/12/14
 * Time: 10:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManagePlayersActivity extends ListActivity {

    private DBAdapter adapter;

    public List<Player> players;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_players);

        adapter = new DBAdapter(this);

        refreshList();

        Button addPlayerButton = (Button) findViewById(R.id.buttonAddPlayer);

        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPlayerIntent = new Intent(v.getContext(),AddPlayerActivity.class);

                startActivityForResult(addPlayerIntent, 0);
            }
        });

        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editPlayerIntent = new Intent(view.getContext(),AddPlayerActivity.class);

                editPlayerIntent.putExtra("id",players.get(position).getPlayerId());
                editPlayerIntent.putExtra("name",players.get(position).getName());
                editPlayerIntent.putExtra("rank",players.get(position).getRank());
                editPlayerIntent.putExtra("placements",players.get(position).getPlacements());

                startActivityForResult(editPlayerIntent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        refreshList();
    }

    private void refreshList() {
        players = adapter.getAllPlayers();

        ListAdapter listAdapter = new ListAdapter(players, this);

        setListAdapter(listAdapter);
    }

}
