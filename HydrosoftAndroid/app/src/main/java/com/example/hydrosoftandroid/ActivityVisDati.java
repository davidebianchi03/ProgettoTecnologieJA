package com.example.hydrosoftandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;


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
        TextView textView = findViewById(R.id.textView2);
        textView.setText("Hello world");
    }

}