package hydrosoft;
/**
 *
 * @author Davide
 */
//grassa
//appartamento
//acquatiche
public class CTipoPianta {

    private final String[] tipiPiante = {"grassa", "appartamento", "acquatiche"};
    private final int[] valMinimi = {0, 8000, 16000};
    private int piantaSelezionata;

    public CTipoPianta() {
        piantaSelezionata = 1;

    }

    public void cambiaPianta() {
        piantaSelezionata++;
        if (piantaSelezionata >= tipiPiante.length) {
            piantaSelezionata = 0;
        }
        //System.out.println(tipiPiante[piantaSelezionata]);
    }

    public String getPianta() {
        return tipiPiante[piantaSelezionata];
    }

    public int getSoglia() {
        return valMinimi[piantaSelezionata];
    }

}
