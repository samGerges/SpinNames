package com.spinname.gerg.spinnames;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(this);
        if (nfc!=null && nfc.isEnabled()){
            Toast.makeText(this, "enabled",Toast.LENGTH_LONG).show();
        }else {
            finish();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Toast.makeText(this,"hello",Toast.LENGTH_LONG).show();
        super.onNewIntent(intent);
    }
}
