package hydrosoft;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Davide
 */
public class HydroSoft {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        CLeggiDHT11 dht11 = new CLeggiDHT11();

        
         for (int i = 0; i < 10; i++) {


            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HydroSoft.class.getName()).log(Level.SEVERE, null, ex);
            }

             System.out.println(dht11.getTemperatura(7));
             
        }
         
         CApriChiudiSerra.apriSerra();
         */
 /*
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(HydroSoft.class.getName()).log(Level.SEVERE, null, ex);
        }
   
         CApriChiudiSerra.chiudiSerra();*/

        CJSONFile json = new CJSONFile();
        //LEGGO IL FILE
        json.readFile();
        
        //PROCEDURA CREAZIONE FILE
        //SOVRASCRIVERE DATI PER FILE SINGOLO
        json.createJSONObject(new CDati()); //da togliere il new CDati
        //APPEND ALLA LISTA
        json.appendJSONOject(new CDati());
        //CREAZIONE FILE
        json.createFile();
        //CARICAMENTO SU SERVER
        String server = "", user = "", password = "";
        ConnessioneFTP connessione = new ConnessioneFTP("paciemanuele.altervista.org", "paciemanuele", "4sg9rA8JpRBZ");
        String file = json.getNomeFileSR();
        connessione.caricaFile(file);
        file = json.getNomeFileLR();
        connessione.caricaFile(file);
    }
}
