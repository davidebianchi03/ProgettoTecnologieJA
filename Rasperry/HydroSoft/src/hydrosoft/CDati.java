package hydrosoft;

/**
 *
 * @author pacie
 */
public class CDati {

    private String oraRilevazione;
    private String tipoPianta;
    private float temperaturaAria;
    private float umiditaAria;
    private boolean aperto; //true = aperto, false = chiuso
    private boolean bagnato; // true = bagnato, false = asciutto;

    public CDati(String tipoPianta, int temperaturaAria, int umiditaAria, boolean aperto, boolean bagnato) {
        this.oraRilevazione = CFormatOra.getOra();
        this.tipoPianta = tipoPianta;
        this.temperaturaAria = temperaturaAria;
        this.umiditaAria = umiditaAria;
        this.aperto = aperto;
        this.bagnato = bagnato;
    }

    public String getOraRilevazione() {
        return this.oraRilevazione;
    }

    public String getTipoPianta() {
        return this.tipoPianta;
    }

    public float getTemperaturaAria() {
        return this.temperaturaAria;
    }

    public String getTemperaturaAriaAsString() {
        String temp = String.valueOf(temperaturaAria);
        return temp;
    }

    public float getUmiditaAria() {
        return this.umiditaAria;
    }

    public String getUmiditaAriaAsString() {
        String temp = String.valueOf(umiditaAria);
        return temp;
    }
    
    public boolean isAperto(){
        return this.aperto;
    }
    
    public String isApertoAsString() {
        String temp = String.valueOf(aperto);
        return temp;
    }
    
    public boolean isBagnato(){
        return this.bagnato;
    }
    
    public String isBagnatoAsString() {
        String temp = String.valueOf(bagnato);
        return temp;
    }

    @Override
    public String toString() {
        String temp = temperaturaAria + " " + umiditaAria + " " + aperto + " " + bagnato + " " + oraRilevazione;
        return temp;
    }
}
