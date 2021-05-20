package com.example.hydrosoftandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ActivityVisDati extends AppCompatActivity {

    private static final String LOG_TAG = "ActivityVisDati";
    //credenziali
    final String user = "hydrosoft";
    final String pass = "6QUqBkdbqRWB";
    final String url = "hydrosoft.altervista.org";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vis_dati);
        Button btnScarica = (Button) findViewById(R.id.btnScarica);
        TextView text = findViewById(R.id.textView2);

        new Thread(new Runnable() {
            public void run() {
                String testoJSON;
                ConnessioneFTP ftp = new ConnessioneFTP(url, user, pass);
                testoJSON= ftp.downloadFile("rilevazioniSerraSingola.json");
                ftp.disconnect();
                text.setText(testoJSON);
            }
        }).start();

        btnScarica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 new Thread(new Runnable() {
                    public void run() {
                        String testoJSON;
                        ConnessioneFTP ftp = new ConnessioneFTP(url, user, pass);
                        testoJSON= ftp.downloadFile("rilevazioniSerraSingola.json");
                        ftp.disconnect();
                        text.setText(testoJSON);
                    }
                }).start();
            }
        });
    }

}