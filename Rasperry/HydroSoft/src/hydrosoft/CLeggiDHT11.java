package hydrosoft;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;

/**
 *
 * @author Davide
 * Classe per la lettura della temperatura e umità dell'aria tramite il sensore DHT11
 * https://www.circuitbasics.com/how-to-set-up-the-dht11-humidity-sensor-on-the-raspberry-pi/ -> codice c++ di esempio per lettura temperatura e umidità dell'aria
 */
public class CLeggiDHT11 {

  private static final int maxVolte  = 85;
    private final int[] datiSensore = { 0, 0, 0, 0, 0 };

    public CLeggiDHT11() {

        if (Gpio.wiringPiSetup() == -1) {//se l'avvio dei pin GPIO non va a buon fine
            System.out.println("Errore durante l'avvio dei pin GPIO");
            return;
        }

        GpioUtil.export(3, GpioUtil.DIRECTION_OUT);
    }

    public Float getTemperatura(final int pin) {//restituisce il valore della temperatura se la misura va a buon fine altrimenti il valore null
        int ultimoStato = Gpio.HIGH;
        int j = 0;
        datiSensore[0] = datiSensore[1] = datiSensore[2] = datiSensore[3] = datiSensore[4] = 0;

        Gpio.pinMode(pin, Gpio.OUTPUT);
        Gpio.digitalWrite(pin, Gpio.LOW);
        Gpio.delay(18);

        Gpio.digitalWrite(pin, Gpio.HIGH);
        Gpio.pinMode(pin, Gpio.INPUT);

        for (int i = 0; i < maxVolte; i++) {
            int conta = 0;
            while (Gpio.digitalRead(pin) == ultimoStato) {
                conta++;
                Gpio.delayMicroseconds(1);
                if (conta == 255) {
                    break;
                }
            }

            ultimoStato = Gpio.digitalRead(pin);

            if (conta == 255) {
                break;
            }

            if (i >= 4 && i % 2 == 0) {
                datiSensore[j / 8] <<= 1;//sposto i bit avanti di una posizione Es 5 = 101 -> 1010
                if (conta > 16) {
                    datiSensore[j / 8] |= 1;
                }
                j++;
            }
        }
        
        //controllo per eventuali errori
        if (j >= 40 &&datiSensore[4] == (datiSensore[0] + datiSensore[1] + datiSensore[2] + datiSensore[3] & 0xFF)) {

            float temperatura = (float) (((datiSensore[2] & 0x7F) << 8) + datiSensore[3]) / 10;
            if (temperatura > 125) {
                temperatura = datiSensore[2]; 
            }
            if ((datiSensore[2] & 0x80) != 0) {
                temperatura = -temperatura;
            }
            
            return temperatura;
        } else {
            return null;
        }

    }
    
    public Float getUmidita(final int pin) {//restituisce il valore della temperatura se la misura va a buon fine altrimenti il valore null
        int ultimoStato = Gpio.HIGH;
        int j = 0;
        datiSensore[0] = datiSensore[1] = datiSensore[2] = datiSensore[3] = datiSensore[4] = 0;

        Gpio.pinMode(pin, Gpio.OUTPUT);
        Gpio.digitalWrite(pin, Gpio.LOW);
        Gpio.delay(18);

        Gpio.digitalWrite(pin, Gpio.HIGH);
        Gpio.pinMode(pin, Gpio.INPUT);

        for (int i = 0; i < maxVolte; i++) {
            int conta = 0;
            while (Gpio.digitalRead(pin) == ultimoStato) {
                conta++;
                Gpio.delayMicroseconds(1);
                if (conta == 255) {
                    break;
                }
            }

            ultimoStato = Gpio.digitalRead(pin);

            if (conta == 255) {
                break;
            }

            if (i >= 4 && i % 2 == 0) {
                datiSensore[j / 8] <<= 1;
                if (conta > 16) {
                    datiSensore[j / 8] |= 1;
                }
                j++;
            }
        }

        if (j >= 40 &&datiSensore[4] == (datiSensore[0] + datiSensore[1] + datiSensore[2] + datiSensore[3] & 0xFF)) {
            float umidita = (float) ((datiSensore[0] << 8) + datiSensore[1]) / 10;
            if (umidita > 100) {
                umidita = datiSensore[0]; 
            }
            
            return umidita;
        } else {
            return null;
        }

    }

    
}