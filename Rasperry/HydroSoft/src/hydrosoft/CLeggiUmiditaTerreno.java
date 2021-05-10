package hydrosoft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Davide
 */
public class CLeggiUmiditaTerreno {

    public CLeggiUmiditaTerreno() {
    }
    
    public static int getUmiditaTerreno() throws IOException{
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("python /home/pi/Desktop/misuraUmiditaTerreno.py");
        BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = bri.readLine();
        int valAnalogico = 0;
        
        try{
        valAnalogico = Integer.parseInt(line);
        }
        catch(Exception e){
            
        }
        
        return valAnalogico;
    }
    
}
