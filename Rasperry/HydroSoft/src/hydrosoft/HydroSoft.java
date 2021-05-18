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
 * @author Davide, pacie, Edo
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
        int sogliaApertura = 20;//temperatura a cui la serra si apre
        int sogliaChiusura = 18;//temperatura a cui la serra si chiude
        long lastUpload = System.currentTimeMillis();
        
        CApriChiudiSerra.chiudiSerra();
        
        String urlServer = "";
        String user = "";
        String pass = "";
        
        while (true) {
            if(pulsante.isHigh()){
                while(pulsante.isHigh());
                tipoPianta.cambiaPianta();
            }
            
            //rilevazione della temperatura dell'aria
            Float t = dht11.getTemperatura(7);
            if(t != null)
                temperatura = t.floatValue();
            
            
            //rilevazione dell'umidità dell'aria
            Float u = dht11.getUmidita(7);
            if(u!= null)
                umidita = u.floatValue();
            
            
            try {
                //lettura umidita del terreno
                umiditaTerreno = CLeggiUmiditaTerreno.getUmiditaTerreno();
            } catch (IOException ex) {
                Logger.getLogger(HydroSoft.class.getName()).log(Level.SEVERE, null, ex);
            }

            
            if(umiditaTerreno < tipoPianta.getSoglia()){//se la pianta è asciutta
                umido = false;
                //irrigo il terreno
                pompa.setState(PinState.HIGH);
                
            }
            else
                umido = true;
            
            if(!aperta && temperatura > sogliaApertura){//se fa caldo apro la serra
                aperta = true;
                CApriChiudiSerra.apriSerra();
            }
            
            if(aperta && temperatura < sogliaChiusura){//se fa freddo chiudo la serra
                aperta = false;
                CApriChiudiSerra.chiudiSerra();
            }
            
            //aggiornamento file json su altervista tramite ftp ogni 2 minuti
            if(System.currentTimeMillis() - lastUpload > 120000){
                //TODO: Aggiornamento ftp
            }
            

        }
    }

}
