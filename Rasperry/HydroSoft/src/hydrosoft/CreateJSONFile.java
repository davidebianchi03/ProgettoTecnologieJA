/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprogettotecnologieja;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author pacie
 */
public class CreateJSONFile {

    private JSONObject json;
    private int tempAria;
    private int umidAria;
    private boolean bagnato;
    private boolean aperto;

    private String nomeFile;

    public CreateJSONFile(int tempAria, int umidAria, boolean aperto, boolean bagnato) {
        this.tempAria = tempAria;
        this.umidAria = umidAria;
        this.bagnato = bagnato;
        this.aperto = aperto;

        this.nomeFile = "rilevazioniSerra.json";
        json = new JSONObject();
    }

    public void createJSON() {

        LocalDate data = LocalDate.now();
        LocalTime tempo = LocalTime.now();
        String time = tempo.toString().substring(0, 8);
        String dataOra  = data.toString() + " " + time;
        System.out.println(dataOra);
        //Creazione oggetto JSON
        String tmp = String.valueOf(tempAria);
        json.put("tempAria", tmp);
        tmp = String.valueOf(umidAria);
        json.put("umidAria", tmp);
        tmp = String.valueOf(bagnato);
        json.put("bagnato", tmp);
        tmp = String.valueOf(aperto);
        json.put("aperto", tmp);
        json.put("lastRilevazione", dataOra);
        //System.out.println(json);
    }

    public void createFile() {
//        //Creazione del file
//        File file = new File(nomeFile);
//        try {
//            if (file.createNewFile()) {
//                System.out.println("File creato: " + file.getName());
//                //Scrittura su file
//            } else {
//                System.out.println("Il file esite già");
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(CreateJSONFile.class.getName()).log(Level.SEVERE, null, ex);
//        }
        try {
            //Scrittura su file
            FileWriter scrivi = new FileWriter(nomeFile);
            scrivi.write(json.toJSONString());
            scrivi.close();
            System.out.println("File completato.");
        } catch (IOException ex) {
            Logger.getLogger(CreateJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getNomeFile() {
        return nomeFile;
    }
}
