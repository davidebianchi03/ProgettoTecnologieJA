package hydrosoft;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
* Aggiungere libreria esterna - commons net ftp
* 
 */
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author pacie
 */
public class ConnessioneFTP {

    //Da impostare dati di accesso per server FTP
    private String server;
    private int port; //Porta di default di altervista
    private String user;
    private String password;
    private FTPClient client;

    public ConnessioneFTP(String server, String user, String password) {
        this.server = server;
        this.port = 21;
        this.user = user;
        this.password = password;
        this.client = new FTPClient();
    }

    public ConnessioneFTP(String server, int port, String user, String password) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.password = password;
        this.client = new FTPClient();
    }

    public void caricaFile(String filePath) {
        try {
            client.connect(server, port);
            client.login(user, password);
            client.enterLocalPassiveMode();
            client.setFileType(FTP.BINARY_FILE_TYPE);
            //System.out.println("Connessione avviata a: " + server + "  : " + port);
            
            File file = new File(filePath);
            String nomeFile = file.getName();
            InputStream inputStream = new FileInputStream(file);
            //System.out.println("Caricamento del file");
            boolean done = client.storeFile(nomeFile, inputStream);
            inputStream.close();
            if (done) {
                //System.out.println("File caricato.");
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (client.isConnected()) {
                    client.logout();
                    client.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
