package com.spinname.gerg.spinnames;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
    Button clearListButton;
    EditText namesField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addButton = findViewById(R.id.addButton);
        playButton = findViewById(R.id.playButton);
        clearButton = findViewById(R.id.clearButton);
        clearListButton = findViewById(R.id.clearListButton);

        namesField = findViewById(R.id.namesField);

        clearButton.setEnabled(false);

        namesField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                clearButton.setEnabled(!namesField.getText().toString().isEmpty());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                clearButton.setEnabled(!namesField.getText().toString().isEmpty());
            }
        });

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(namesField.getText());
                boolean exist = false;

                if(name.startsWith(" ")){
                    alert.setTitle("Something Went Wrong")
                            .setMessage("Type a name to be added to the list, and make sure no spaces in the beginning.")
                            .setPositiveButton("Got It!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    alert.show();
                }
                else if (!name.equals("")){
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

                }
                else {
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

        clearListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                players.clear();
                initRecyclerView(players);
                buttonConfig();
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namesField.setText("");
                buttonConfig();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playedAct = new Intent(HomeActivity.this, playedActivity.class);
                playedAct.putStringArrayListExtra("original", players);
                startActivity(playedAct);
            }
        });
        buttonConfig();

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
            clearListButton.setEnabled(false);
        }else if(players.size() <= 2) {
            playButton.setEnabled(false);
            clearListButton.setEnabled(true);
        }else {
            playButton.setEnabled(true);
        }
    }
}
