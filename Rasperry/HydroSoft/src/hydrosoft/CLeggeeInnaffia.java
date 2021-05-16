/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hydrosoft;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Edo
 */
public class CLeggeeInnaffia extends Thread{
    
private CTipoPianta p;
    public CLeggeeInnaffia(CTipoPianta p) {
       this.p= p;
    }
    
    private void AttivaPompa(){
       final GpioController gpio = GpioFactory.getInstance();
        GpioPinDigitalOutput pompa = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25);
        pompa.setState(PinState.HIGH);
    try {
        Thread.sleep(1000);
    } catch (InterruptedException ex) {
        Logger.getLogger(CLeggeeInnaffia.class.getName()).log(Level.SEVERE, null, ex);
    }
    pompa.setState(PinState.LOW);
    }
@Override
    public void run(){
        while(true){
            try {
                if (p.getSoglia()>CLeggiUmiditaTerreno.getUmiditaTerreno()) {
                    AttivaPompa();
                }
            } catch (IOException ex) {
                Logger.getLogger(CLeggeeInnaffia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}
