package com.example.hydrosoftandroid;


import android.util.Log;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.InputStream;

public class ConnessioneFTP {
    private final FTPClient client;
    private final String server;
    private final int port;
    private final String user;
    private final String password;

    public ConnessioneFTP(String server, int port, String user, String password){
        this.client = new FTPClient();
        this.server = server;
        this.port = port;
        this.user = user;
        this.password = password;
    }
    public ConnessioneFTP(String server, String user, String password){
        this.client = new FTPClient();
        this.server = server;
        this.port = 21; //Default di AlterVista
        this.user = user;
        this.password = password;
    }
    public void downloadFile(){

        try {
            client.connect(server, port);

            client.login(user, password);

            String nomeFile = "rilevazioniSerraSingola.json";
            InputStream iS = client.retrieveFileStream(nomeFile);
            String file = iS.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
