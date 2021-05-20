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
    private ElaboraDati elabora;
    //credenziali
    final String user = "hydrosoft";
    final String pass = "6QUqBkdbqRWB";
    final String url = "hydrosoft.altervista.org";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vis_dati);
        Button btnScarica = findViewById(R.id.btnScarica);
        TextView textOra = findViewById(R.id.txtOra);
        TextView textTipo = findViewById(R.id.txtTipo);
        TextView textTemp = findViewById(R.id.txtTemperatura);
        TextView textUmid = findViewById(R.id.txtUmidita);
        TextView textAperto = findViewById(R.id.txtAperta);
        TextView textBagnato = findViewById(R.id.txtBagnato);
        //aggiornamento dei dati
        ScaricaConFTP scarica = new ScaricaConFTP(url, user, pass);
        scarica.start();
        try {
            scarica.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String jsonString = scarica.getJSONString();
        ElaboraDati elaboraDati = new ElaboraDati(jsonString);
        textOra.setText(elaboraDati.getOra());
        textTipo.setText(elaboraDati.getTipo());
        textTemp.setText((int) elaboraDati.getTemperatura() + "°C");
        textUmid.setText((int) elaboraDati.getUmidita() + "%");
        boolean aperto = elaboraDati.getAperto();
        if (aperto)
            textAperto.setText("Serra aperta");
        else
            textAperto.setText("Serra chiusa");

        boolean terrenoUmido = elaboraDati.getBagnato();
        if (terrenoUmido)
            textBagnato.setText("Terreno bagnato");
        else
            textBagnato.setText("Terreno asciutto");

        btnScarica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aggiornamento dei dati
                ScaricaConFTP scarica = new ScaricaConFTP(url, user, pass);
                scarica.start();
                try {
                    scarica.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String jsonString = scarica.getJSONString();
                ElaboraDati elaboraDati = new ElaboraDati(jsonString);
                textOra.setText(elaboraDati.getOra());
                textTipo.setText(elaboraDati.getTipo());
                textTemp.setText((int) elaboraDati.getTemperatura() + "°C");
                textUmid.setText((int) elaboraDati.getUmidita() + "%");
                boolean aperto = elaboraDati.getAperto();
                if (aperto)
                    textAperto.setText("Serra aperta");
                else
                    textAperto.setText("Serra chiusa");

                boolean terrenoUmido = elaboraDati.getBagnato();
                if (terrenoUmido)
                    textBagnato.setText("Terreno bagnato");
                else
                    textBagnato.setText("Terreno asciutto");
            }
        });

    }

}