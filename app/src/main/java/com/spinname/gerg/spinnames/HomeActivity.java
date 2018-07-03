package com.spinname.gerg.spinnames;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    final ArrayList<String> players = new ArrayList<String>();
    RecyclerView recyclerView = findViewById(R.id.namesList);
    RecyclerViewAdapter adapter = new RecyclerViewAdapter(players, this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final int prev = 0;

        final Button addButton = findViewById(R.id.addButton);
        final Button playButton = findViewById(R.id.playButton);
        playButton.setEnabled(false);
        final Button clearButton = findViewById(R.id.clearButton);
        clearButton.setEnabled(false);


        final EditText namesField = findViewById(R.id.namesField);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final AlertDialog.Builder chosen = new AlertDialog.Builder(this);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(namesField.getText());
                if (!name.equals("")){
                    players.add(namesField.getText().toString());
                    namesField.setText("");
                    initRecyclerView(players);
                    clearButton.setEnabled(true);
                    if (players.size() > 1){
                        playButton.setEnabled(true);
                    }
                }else {
                    alert.setTitle("Something Went Wrong")
                            .setMessage("Enter a name to be added to the list ;)")
                            .setPositiveButton("Got It!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    alert.show();
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                players.clear();
                initRecyclerView(players);
                clearButton.setEnabled(false);
                playButton.setEnabled(false);
            }
        });

        playButton.setText("Pick Name");
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spin(players, chosen, prev);
            }
        });
    }
    private void spin(final ArrayList<String> players, final AlertDialog.Builder chosen, int prev){
        if(players.isEmpty()){
        }else{
            int current;
            do{
                current = (int) Math.round(Math.random()*((players.size())-1));
            }while (current == prev);
            prev = current;
            final int finalPrev = prev;
            chosen.setTitle("Lucky Name")
                    .setMessage(players.get(current))
                    .setPositiveButton("Ready for a new Pick!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            spin(players, chosen, finalPrev);
                        }
                    })
                    .setNegativeButton("Quit :(", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        }
    }

    private void initRecyclerView(ArrayList<String> players){
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
