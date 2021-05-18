package hydrosoft;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Davide
 */

/*
    Il tutto sarebbe potuto essere fatto con i thread ma con essi non riesco a leggere il valore di umidità e temperatura (ho pensato che fosse un problema dovuto
    alla condivisione dei pin GPIO tra i vari processi ma anche facendo la mutua esclusione non funzionano)
 */
public class HydroSoft {

    public static void main(String[] args) {
        final GpioController gpio = GpioFactory.getInstance();
        CLeggiDHT11 dht11 = new CLeggiDHT11();
        GpioPinDigitalInput pulsante = gpio.provisionDigitalInputPin(RaspiPin.GPIO_29);//pin pulsante cambio pianta
        GpioPinDigitalOutput pompa = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25);//pin pompa acqua
        CTipoPianta tipoPianta = new CTipoPianta();
        CJSONFile fileJSON = new CJSONFile();

        float temperatura = 0.0f;
        float umidita = 0.0f;
        int umiditaTerreno = 0;
        boolean umido = true;
        boolean aperta = false;
        int sogliaApertura = 19;//temperatura a cui la serra si apre
        int sogliaChiusura = 17;//temperatura a cui la serra si chiude
        long lastUpload = System.currentTimeMillis();

        CApriChiudiSerra.chiudiSerra();

        String urlServer = "";
        String user = "";
        String pass = "";

        boolean pompaAttiva = false;

        long oraAccensione = 0;
        pompa.setState(PinState.LOW);//inizializzo la pompa a spenta
        while (true) {
            if (pulsante.isHigh()) {//se preme e rilascia il pulsante per cambiare il tipo di pianta
                while (pulsante.isHigh());
                tipoPianta.cambiaPianta();
            }

            //rilevazione della temperatura dell'aria
            Float t = dht11.getTemperatura(7);
            if (t != null) {
                temperatura = t.floatValue();
            }
            //System.out.println("t"+temperatura);
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(HydroSoft.class.getName()).log(Level.SEVERE, null, ex);
            }

            //lettura umidita del terreno
            try {
                umiditaTerreno = CLeggiUmiditaTerreno.getUmiditaTerreno();
            } catch (IOException ex) {
                Logger.getLogger(HydroSoft.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (umiditaTerreno < tipoPianta.getSoglia() || pompaAttiva == true) {//se la pianta è asciutta
                //System.out.println("terreno secco");
                umido = false;
                //irrigo il terreno
                if (System.currentTimeMillis() - oraAccensione > 120000 && !pompaAttiva) {//se sono passati almeno due minuti dall'ultima irrigazione
                    //System.out.println("Pompa on");
                    pompaAttiva = true;
                    pompa.setState(PinState.HIGH);//accendo la pompa
                    oraAccensione = System.currentTimeMillis();
                }
                //TODO:
                //Impostare tempo spegnimento pompa -> 20s
                if (pompaAttiva && System.currentTimeMillis() - oraAccensione > 20000) {
                    //System.out.println("Pompa off");
                    pompaAttiva = false;
                    pompa.setState(PinState.LOW);//spengo la pompa
                }

            } else if (umiditaTerreno > tipoPianta.getSoglia()) {
                umido = true;
            }

            //rilevazione dell'umidità dell'aria
            Float u = dht11.getUmidita(7);
            //System.out.println(u);
            if (u != null) {

                umidita = u.floatValue();
            }
            //System.out.println("u"+umidita);
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(HydroSoft.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!aperta && temperatura > sogliaApertura) {//se fa caldo apro la serra
                aperta = true;
                CApriChiudiSerra.apriSerra();
            }

            if (aperta && temperatura < sogliaChiusura) {//se fa freddo chiudo la serra
                aperta = false;
                CApriChiudiSerra.chiudiSerra();
            }
            //System.out.println("oke");
            //aggiornamento file json su altervista tramite ftp ogni 2 minuti
            if (System.currentTimeMillis() - lastUpload > 120000) {
                CDati dati = new CDati(tipoPianta.getPianta(), (int) temperatura, (int) umidita, aperta, umido);
                fileJSON.readFile();
                fileJSON.createJSONObject(dati);
                fileJSON.appendJSONOject(dati);
                fileJSON.createFile();
                ConnessioneFTP ftp = new ConnessioneFTP(urlServer, user, pass);
                String file = fileJSON.getNomeFileSR();//upload singola rilevazione
                ftp.caricaFile(file);
                file = fileJSON.getNomeFileLR();//upload più rilevazioni
                ftp.caricaFile(file);
                //System.out.println("FileUploaded");
                lastUpload = System.currentTimeMillis();
            }

        }
    }

}
