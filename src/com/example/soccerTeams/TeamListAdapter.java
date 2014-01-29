package com.example.soccerTeams;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rmoriarty
 * Date: 1/14/14
 * Time: 6:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class TeamListAdapter {

    private Context context;
    private List<Team> teams;

    public TeamListAdapter(List<Team> teamss, Context context)  {
        this.teams = teamss;
        this.context = context;
    }

    public long getItemId(int position) {
        return 0;
    }

    public Team getItem(int position) {
        return teams.get(position);
    }

    public int getCount() {
        return teams.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Team item = teams.get(position);

        RelativeLayout itemLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.team_list_layout,parent,false);

        TextView textLabel = (TextView) itemLayout.findViewById(R.id.name);
        textLabel.setText(item.toString());

        CheckBox box = (CheckBox) itemLayout.findViewById(R.id.selected);

        return itemLayout;
    }

}
