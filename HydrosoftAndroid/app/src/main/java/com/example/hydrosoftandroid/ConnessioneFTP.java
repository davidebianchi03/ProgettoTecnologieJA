package com.example.hydrosoftandroid;


import android.util.Log;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class ConnessioneFTP{
    private FTPClient ftp;

    public ConnessioneFTP(String host, String user, String pwd){
            ftp = new FTPClient();
            ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
            int reply;
            try {
                ftp.connect(host);
                reply = ftp.getReplyCode();
                if (!FTPReply.isPositiveCompletion(reply)) {
                    ftp.disconnect();
                }
                ftp.login(user, pwd);
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                ftp.enterLocalPassiveMode();
            }
            catch (Exception exception){}
        }

        public String downloadFile(String percorsoRemoto) {
            try (ByteArrayOutputStream fos = new ByteArrayOutputStream ()) {
                this.ftp.retrieveFile(percorsoRemoto, fos);
                String contenuto = fos.toString("UTF-8");
                return contenuto;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        public void disconnect() {
            try {
                if (this.ftp.isConnected()) {
                    this.ftp.logout();
                    this.ftp.disconnect();
                }
            }
            catch(Exception exception){

            }
        }
}
