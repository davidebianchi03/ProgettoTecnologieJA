package hydrosoft;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.Gpio;

/**
 *
 * @author Davide
 */
//grassa
//appartamento
//acquatiche
public class CTipoPianta extends Thread {

    private final String[] tipiPiante = {"grassa", "appartamento", "acquatiche"};
    private final int[] valMinimi = {0, 8000, 16000};
    private int piantaSelezionata;

    public CTipoPianta() {
        piantaSelezionata = 1;

    }

    @Override
    public void run() {
        final GpioController gpio = GpioFactory.getInstance();
        GpioPinDigitalInput pulsante = gpio.provisionDigitalInputPin(RaspiPin.GPIO_29);
        while (true) {
            if (pulsante.isHigh()) {//se l'utente preme e rilascia il pulsante 
                while (pulsante.isHigh());
                piantaSelezionata++;
                if (piantaSelezionata >= tipiPiante.length) {
                    piantaSelezionata = 0;
                }System.out.println(tipiPiante[piantaSelezionata]);
            }
            
        }
    }

    public String getPianta() {
        return tipiPiante[piantaSelezionata];
    }

    public int getSoglia() {
        return valMinimi[piantaSelezionata];
    }

}
