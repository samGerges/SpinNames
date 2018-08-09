package com.spinname.gerg.spinnames;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class playedActivity extends AppCompatActivity {

    TextView firstP;
    TextView secondP;
    Button anotherPick;
    TextView asks;
    ArrayList<String> original;
    ArrayList<String> firstList;
    ArrayList<String> secondList;
    Animation scaleAnimate;
    Animation fadeAnimate;
    int SPLASH_TIMEOUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_played);

        scaleAnimate = AnimationUtils.loadAnimation(this,R.anim.scaling);
        fadeAnimate = AnimationUtils.loadAnimation(this,R.anim.fading);
        firstP = findViewById(R.id.firstP);
        secondP = findViewById(R.id.secondP);
        asks = findViewById(R.id.asks);
        anotherPick = findViewById(R.id.anotherPick);

        Intent intent = getIntent();

        original = intent.getStringArrayListExtra("original");
        asks.startAnimation(scaleAnimate);
        firstP.startAnimation(fadeAnimate);
        secondP.startAnimation(fadeAnimate);
        spin(original, original);
        anotherPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                asks.startAnimation(scaleAnimate);
                firstP.startAnimation(fadeAnimate);
                secondP.startAnimation(fadeAnimate);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
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
                }, SPLASH_TIMEOUT);
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
            System.out.println("second loop ");
        } while (secondPlayer.get(sName).equals(temp2) || secondPlayer.get(sName).equals(firstPlayer.get(fName)));

        temp = firstPlayer.get(fName);
        temp2 = secondPlayer.get(sName);


        firstP.setText(temp);
        secondP.setText(temp2);

        System.out.println(firstPlayer.size() + "\n" + temp + "\n" + temp2 + "\n" + secondPlayer.size());

        firstPlayer.remove(fName);
        secondPlayer.remove(sName);

        firstList = new ArrayList<>(firstPlayer);
        secondList = new ArrayList<>(secondPlayer);

    }
}
