package com.example.hydrosoftandroid;

public class ScaricaConFTP extends Thread{

    private String url;
    private String user;
    private String pass;
    private int port;
    private String testoJSON;

    public ScaricaConFTP(String url, String user, String pass){
        this.url = url;
        this.user = user;
        this.pass = pass;
        testoJSON = "";
    }

    @Override
    public void run() {
        ConnessioneFTP ftp = new ConnessioneFTP(url, user, pass);
         testoJSON = ftp.downloadFile("rilevazioniSerraSingola.json");
        ftp.disconnect();
    }

    public String getJSONString(){
        return testoJSON;
    }
}
