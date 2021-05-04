package hydrosoft;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Davide
 */
public class CApriChiudiSerra {

    public CApriChiudiSerra(int pin) {

    }
    
    public static void apriSerra(){
        Runtime rt= Runtime.getRuntime();

        try {
            Process p=rt.exec("python /home/pi/Desktop/servoApri.py");
        } catch (IOException ex) {
            Logger.getLogger(CApriChiudiSerra.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static void chiudiSerra(){
        Runtime rt= Runtime.getRuntime();

        try {
            Process p=rt.exec("python /home/pi/Desktop/servoChiudi.py");
        } catch (IOException ex) {
            Logger.getLogger(CApriChiudiSerra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
