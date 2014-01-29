package com.example.soccerTeams;

import android.*;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rmoriarty
 * Date: 1/12/14
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListAdapter extends BaseAdapter {

    private Context context;
    private List<Player> players;

    public ListAdapter(List<Player> players, Context context)  {
        this.players = players;
        this.context = context;
    }

    public long getItemId(int position) {
        return 0;
    }

    public Player getItem(int position) {
        return players.get(position);
    }

    public int getCount() {
        return players.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Player item = players.get(position);

        RelativeLayout itemLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.list_layout,parent,false);

        TextView textLabel = (TextView) itemLayout.findViewById(R.id.name);
        textLabel.setText(item.getName());

        CheckBox box = (CheckBox) itemLayout.findViewById(R.id.selected);

        TextView teamLabel = (TextView) itemLayout.findViewById(R.id.team);
        int team = item.getTeam();
        if(team != -1) {
            teamLabel.setText(Integer.toString(team));
        }

        return itemLayout;
    }

}
