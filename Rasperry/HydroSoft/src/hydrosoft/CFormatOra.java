package hydrosoft;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author pacie
 */
public class CFormatOra {
    public static String getOra() {
        LocalDate data = LocalDate.now();
        LocalTime tempo = LocalTime.now();
        String time = tempo.toString().substring(0, 8);
        String dataOra = data.toString() + " " + time;
        System.out.println(dataOra);
        return dataOra;
    } 
}
