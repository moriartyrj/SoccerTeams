package com.example.soccerTeams;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: rmoriarty
 * Date: 1/8/14
 * Time: 10:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player implements Comparable<Player> {

    private int playerId;
    private String name;
    public int rank;
    private int placements;
    public boolean selected;
    int team;

    public Player(int playerId,String name,int rank,int placements) {
        this.playerId = playerId;
        this.name = name;
        this.rank = rank;
        this.placements = placements;

        selected = false;
        team = -1;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean getSelected() {
        return selected;
    }

    public String getName() {
        return name;
    }
    public int getPlayerId() {
        return playerId;
    }
    public int getRank() {
        return rank;
    }
    public int getPlacements() {
        return placements;
    }
    public int getTeam() {
        return team;
    }
    public void setTeam(int team) {
        this.team = team;
    }

    public int compareTo(Player otherPlayer) {
        int randomRank = new Random().nextDouble() < 0.5 ? -1 : 1;
        if(team == -1 && otherPlayer.getTeam() == -1) {
            return rank < otherPlayer.getRank() ? 1 : rank > otherPlayer.getRank() ? -1 : randomRank;
        } else if (team == -1 && otherPlayer.team != -1) {
            return 1;
        } else if (team != -1 && otherPlayer.team == -1) {
            return -1;
        } else {
            return team < otherPlayer.getTeam() ? -1 : team > otherPlayer.getTeam() ? 1 : 0;
        }

    }
}
