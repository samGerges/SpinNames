package com.spinname.gerg.spinnames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class playedActivity extends AppCompatActivity {

    TextView firstP;
    TextView secondP;
    Button anotherPick;
    ArrayList<String> original;
    ArrayList<String> firstList;
    ArrayList<String> secondList;
    Boolean fEmpty = false;
    Boolean sEmpty = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_played);

        firstP = findViewById(R.id.firstP);
        firstP.setText("A");
        secondP = findViewById(R.id.secondP);
        secondP.setText("B");
        anotherPick = findViewById(R.id.anotherPick);

        Intent intent = getIntent();

        original = intent.getStringArrayListExtra("original");
        spin(original, original);
        anotherPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstList.size() <2 && secondList.size() <2){
                    spin(original, original);
                }else if(firstList.size() <2){
                    spin(original,secondList);
                }else if(secondList.size() <2){
                    spin(firstList, original);
                }else{
                    spin(firstList, secondList);
                }
            }
        });

    }

    String temp = "";
    String temp2 = "";

    private void spin(final ArrayList<String> playerSpin, final ArrayList<String> askedPlayer) {
        int fName;
        int sName;

        final ArrayList<String> firstPlayer = new ArrayList<>(playerSpin);
        final ArrayList<String> secondPlayer = new ArrayList<>(askedPlayer);

        do {
            fName = (int) Math.round(Math.random() * ((firstPlayer.size()) - 1));
            System.out.println("first loop ");
        } while (firstPlayer.get(fName).equals(temp));
        do {
            sName = (int) Math.round(Math.random() * ((secondPlayer.size()) - 1));
            System.out.println("first loop ");
        } while (secondPlayer.get(sName).equals(temp2) || fName == sName);

        temp = firstPlayer.get(fName);
        temp2 = secondPlayer.get(sName);

        firstP.setText(temp);
        secondP.setText(temp2);

        System.out.println(firstPlayer.size() + "\n" + fName + "\n" + sName + "\n" + secondPlayer.size());

        firstPlayer.remove(fName);
        secondPlayer.remove(sName);
        System.out.println(firstPlayer.size());
        System.out.println(secondPlayer.size());

        firstList = new ArrayList<>(firstPlayer);
        secondList = new ArrayList<>(secondPlayer);

    }
}
