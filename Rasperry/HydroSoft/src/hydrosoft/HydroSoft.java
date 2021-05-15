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
        CListaDati listaDati = new CListaDati();
        //CListaDati rilevazioni = new CListaDati();
//        rilevazioni.addDati(new CDati("pianta1", 21, 50, false, true));
//        rilevazioni.addDati(new CDati("pianta2", 21, 50, false, true));
//        rilevazioni.addDati(new CDati("pianta3", 21, 50, false, true));
//        rilevazioni.addDati(new CDati("pianta4", 21, 50, false, true));
        json.readFile(listaDati);
        //json.createJSONObject(new CDati("pianta1", 21, 50, false, true)); //da togliere il new CDati per test
        //json.createJSONArray(rilevazioni); //-> Aspetta ad usare devo risolvere cose :D (paci)
        //json.createFile(); //-> Da un errore devo risolvere
        //Caricamento file su server
//        String server = "", user = "", password = "";
//        ConnessioneFTP connessione = new ConnessioneFTP(server, user, password);
//        String file = json.getNomeFile();
//        connessione.caricaFile(file);

    }

}
