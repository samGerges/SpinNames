package com.spinname.gerg.spinnames;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> players = new ArrayList<String>();
    private TextView nameField = findViewById(R.id.namesField);
    private Button addButton =  findViewById(R.id.addButton);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void addClick(){
        System.out.println("Hello");
    }
    private void shuffleNames(ArrayList<String> names){

    }
}
