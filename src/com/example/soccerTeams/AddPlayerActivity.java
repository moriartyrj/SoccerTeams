package com.example.soccerTeams;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created with IntelliJ IDEA.
 * User: rmoriarty
 * Date: 1/12/14
 * Time: 10:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddPlayerActivity extends Activity {

    private DBAdapter adapter;
    private int id;
    private String name;
    private int rank;
    private int placements;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_player);

        Intent intent = getIntent();

        adapter = new DBAdapter(this);

        Button doneButton = (Button) findViewById(R.id.buttonAddPlayerDone);
        Button deleteButton = (Button) findViewById(R.id.buttonDeletePlayer);

        EditText nameText = (EditText) findViewById(R.id.enterName);
        Spinner rankSpinner = (Spinner) findViewById(R.id.enterRank);

        id = intent.getIntExtra("id",0);
        name = intent.getStringExtra("name");
        rank = intent.getIntExtra("rank",0);
        placements = intent.getIntExtra("placements",-1);

        nameText.setText(name);

        ArrayAdapter arrayAdapter = (ArrayAdapter) rankSpinner.getAdapter();
        rankSpinner.setSelection(arrayAdapter.getPosition(Integer.toString(rank)));

        doneButton.setOnClickListener(new DoneButtonListener(nameText, rankSpinner));

        deleteButton.setOnClickListener(new DeleteButtonListener(id));
    }

    class DoneButtonListener implements View.OnClickListener {

        private EditText nameText;
        private Spinner rankSpinner;

        public DoneButtonListener(EditText name, Spinner rank){
            this.nameText = name;
            this.rankSpinner = rank;
        }

        public void onClick(View v) {
            if(id == 0) {
                adapter.createPlayer(nameText.getText().toString(),Integer.parseInt(rankSpinner.getSelectedItem().toString()));
            } else {
                adapter.updatePlayer(id,nameText.getText().toString(),Integer.parseInt(rankSpinner.getSelectedItem().toString()),placements);
            }

            finish();
        }

    }

    class DeleteButtonListener implements View.OnClickListener{

        private int id;

        public DeleteButtonListener(int id) {
            this.id = id;
        }

        public void onClick(View view) {
            new AlertDialog.Builder(view.getContext()).setTitle("Delete player").setMessage("Are you sure?")
                    .setPositiveButton("Yep",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.deletePlayer(id);
                            finish();
                        }
                    })
                    .setNegativeButton("No",null).show();
        }
    }


}
