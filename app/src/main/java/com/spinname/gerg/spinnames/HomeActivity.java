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

    final ArrayList<String> players = new ArrayList<>();
    RecyclerViewAdapter adapter = new RecyclerViewAdapter(players, this);
    Button addButton;
    Button playButton;
    Button clearButton;
    String temp = "";
    String temp2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addButton = findViewById(R.id.addButton);
        playButton = findViewById(R.id.playButton);
        clearButton = findViewById(R.id.clearButton);

        final int prev = 0;

        final EditText namesField = findViewById(R.id.namesField);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final AlertDialog.Builder chosen = new AlertDialog.Builder(this);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(namesField.getText());
                Boolean exist = false;
                if (!name.equals("")){
                    for (int i=0; i<players.size();i++){
                        if(name.equalsIgnoreCase(players.get(i))){
                            exist = true;
                            break;
                        }else {
                            exist = false;
                        }
                    }
                    if (exist){
                        alert.setTitle("Something Went Wrong")
                                .setMessage("Name already exists")
                                .setPositiveButton("Change it", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setNegativeButton("Add It", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        players.add(namesField.getText().toString());
                                        namesField.setText("");
                                        initRecyclerView(players);
                                    }
                                })
                                .show();
                    }else {
                        players.add(namesField.getText().toString());
                        namesField.setText("");
                        initRecyclerView(players);
                    }

                }else {
                    alert.setTitle("Something Went Wrong")
                            .setMessage("Type a name to be added to the list ;)")
                            .setPositiveButton("Got It!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    alert.show();
                }
                buttonConfig();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                players.clear();
                initRecyclerView(players);
                buttonConfig();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spin(players, chosen);
            }
        });
        buttonConfig();

    }

    private void spin(final ArrayList<String> playerSpin, final AlertDialog.Builder chosen){
        int fName;
        int sName;

        final ArrayList<String> players2 = new ArrayList<>(playerSpin);

        do {
            fName = (int) Math.round(Math.random()*((players2.size())-1));
            System.out.println("first loop ");
        }while (players2.get(fName).equals(temp));

        do {
            sName = (int) Math.round(Math.random()*((players2.size())-1));
        }while (fName == sName);

        temp = players2.get(fName);
        temp2 = players2.get(sName);

        System.out.println(players2.size() + "\n" +fName + "\n"+ sName);

        players2.remove(fName);
        System.out.println(players2.size());

        chosen.setTitle("Lucky Name")
                .setMessage(temp + " is going to ask " + temp2)
                .setPositiveButton("Ready for a new Pick!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (players2.size() > 2){
                                spin(players2, chosen);
                            }else{
                                spin(players,chosen);
                            }
                        }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();

    }
    private void initRecyclerView(final ArrayList<String> players){
        RecyclerView recyclerView = findViewById(R.id.nameList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.onItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                adapter.removeItem(position);
                initRecyclerView(players);
                buttonConfig();
            }
        });
    }
    private void buttonConfig(){
        if(players.isEmpty()){
            playButton.setEnabled(false);
            clearButton.setEnabled(false);
        }else if(players.size() <= 2) {
            playButton.setEnabled(false);
            clearButton.setEnabled(true);
        }else {
            playButton.setEnabled(true);
        }
    }
}
