package hydrosoft;

/**
 *
 * @author pacie
 */
public class CDati {
    private int temperaturaAria;
    private int umiditaAria;
    private boolean aperto; //true = aperto, false = chiuso
    private boolean bagnato; // true = bagnato, false = asciutto;
    private String oraRilevazione;

    public CDati(int temperaturaAria, int umiditaAria, boolean aperto, boolean bagnato, String oraRilevazione) {
        this.temperaturaAria = temperaturaAria;
        this.umiditaAria = umiditaAria;
        this.aperto = aperto;
        this.bagnato = bagnato;
        this.oraRilevazione = oraRilevazione;
    }

    public String getTemperaturaAria() {
        String temp = String.valueOf(temperaturaAria);
        return temp;
    }

    public String getUmiditaAria() {
        String temp = String.valueOf(umiditaAria);
        return temp;
    }

    public String isAperto() {
        String temp = String.valueOf(aperto);
        return temp;
    }

    public String isBagnato() {
        String temp = String.valueOf(bagnato);
        return temp;
    }

    public String getOraRilevazione() {
        return oraRilevazione;
    }   
    @Override
    public String toString(){
        String temp = temperaturaAria + " " + umiditaAria + " " + aperto + " " + bagnato + " " + oraRilevazione;
        return temp;
    }
}
