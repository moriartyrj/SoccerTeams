package com.example.soccerTeams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rmoriarty
 * Date: 1/14/14
 * Time: 6:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Team {
    private List<Player> players;

    public Team(List<Player> players) {
        this.players = players;
    }

    public Team() {
        players = new ArrayList<Player>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public String toString() {
        String result = new String();

        for(Player player : players) {
            result += player.toString() + "\n";
        }

        return result;
    }
}
