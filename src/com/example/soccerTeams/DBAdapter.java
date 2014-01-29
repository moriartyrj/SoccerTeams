package com.example.soccerTeams;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rmoriarty
 * Date: 1/8/14
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBAdapter {
    public static final String DB = "soccerTeams";
    public static final String TABLE = "player";
    public static final int DB_VERSION = 1;

    public static final String NAME = "name";
    public static final String RANK = "rank";
    public static final String PLACEMENTS = "placements";

    private static final int ID_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int RANK_INDEX = 2;
    private static final int PLACEMENTS_INDEX = 3;

    public static final String CREATE_DB = "create table player(_id integer primary key autoincrement, " +
                "name text not null, " +
                "rank int not null," +
                "placements int default 0" +
                ");";

    private SQLiteDatabase db;

    public DBAdapter(Context ctx)      {
        try {
            db = ctx.openOrCreateDatabase(DB, 0, null);
            db.execSQL(CREATE_DB);
        } catch (SQLiteException e) {
            Log.e("Exception on query: ",e.toString());
        }
    }

    public void close() {
        db.close();
    }

    public long createPlayer(String name, int rank) {
        ContentValues values = new ContentValues();
        values.put(NAME,name);
        values.put(RANK,rank);
        return db.insert(TABLE,null,values);
    }

    public void deletePlayer(int playerId) {
        db.delete(TABLE,"_id=" + playerId,null);
    }

    /*public Cursor getAllPlayers() {
        try{
            return db.query(TABLE,new String[] {"_id",NAME,RANK,PLACEMENTS},
                        null,null,null,null,PLACEMENTS + "," + NAME);
        } catch (SQLiteException e) {
            Log.e("Exception on query: ",e.toString());
            return null;
        }
    }     */

    public List<Player> getAllPlayers() {

        Cursor playerCursor = null;
        List<Player> players = new ArrayList<Player>();

        try{
            playerCursor = db.query(TABLE,new String[] {"_id",NAME,RANK,PLACEMENTS},
                    null,null,null,null,PLACEMENTS + "," + NAME);
        } catch (SQLiteException e) {
            Log.e("Exception on query: ",e.toString());
            return null;
        }

        while(playerCursor.moveToNext()) {
            int playerId = playerCursor.getInt(ID_INDEX);
            String name = playerCursor.getString(NAME_INDEX);
            int rank = playerCursor.getInt(RANK_INDEX);
            int placements = playerCursor.getInt(PLACEMENTS_INDEX);

            Player player = new Player(playerId, name, rank, placements);
            players.add(player);
        }

        return players;
    }

    public void updatePlayer(int playerId, String name, int rank, int placements) {
        ContentValues values = new ContentValues();
        values.put(NAME,name);
        values.put(RANK,rank);
        values.put(PLACEMENTS,placements);
        db.update(TABLE,values,"_id=" + playerId,null);
    }

}
