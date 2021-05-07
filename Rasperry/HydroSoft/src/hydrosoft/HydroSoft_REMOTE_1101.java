package hydrosoft;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiGpioProvider;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.RaspiPinNumberingScheme;
import com.pi4j.util.CommandArgumentParser;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HydroSoft {

    public static void main(String[] args) throws InterruptedException {
        //System.out.println(System.getProperty("os.name")); //test per vedere se il progetto è stato configurato correttamente per essere eseguito su rasperry/controllo che il programma venga eseguito su rasperry
        //GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));
/*
        CLeggiDHT11 dht11 = new CLeggiDHT11();

        
         for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HydroSoft.class.getName()).log(Level.SEVERE, null, ex);
            }
             System.out.println(dht11.getTemperatura(7));
             
             
        }*/

        CApriChiudiSerra.apriSerra();
        Thread.sleep(5000);
        CApriChiudiSerra.chiudiSerra();

//        //Creazione file JSON con dati raccolti
//        int temp = 0, umid = 0;
//        boolean aperto = false, bagnato = false;
//        CreateJSONFile json = new CreateJSONFile(temp, umid, aperto, bagnato);
//        json.createJSON();
//        json.createFile();
//        //Invio del file con i dati a server altervista
//        String server = "";
//        String user = "";
//        String password = "";
//        ConnessioneFTP connessione = new ConnessioneFTP(server, user, password);
//        String file = json.getNomeFile();
//        connessione.caricaFile(file);

    }

}