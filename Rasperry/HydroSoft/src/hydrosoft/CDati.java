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

    public CDati() {
        this.oraRilevazione = "";
        this.tipoPianta = "";
        this.temperaturaAria = 0.0f;
        this.umiditaAria = 0.0f;
        this.aperto = false;
        this.bagnato = false;
    }
    
    public CDati(String tipoPianta, int temperaturaAria, int umiditaAria, boolean aperto, boolean bagnato) {
        this.oraRilevazione = CFormatOra.getOra();
        this.tipoPianta = tipoPianta;
        this.temperaturaAria = temperaturaAria;
        this.umiditaAria = umiditaAria;
        this.aperto = aperto;
        this.bagnato = bagnato;
    }

    public void setOraRilevazione(String oraRilevazione) {
        this.oraRilevazione = oraRilevazione;
    }

    public void setTipoPianta(String tipoPianta) {
        this.tipoPianta = tipoPianta;
    }

    public void setTemperaturaAria(float temperaturaAria) {
        this.temperaturaAria = temperaturaAria;
    }

    public void setUmiditaAria(float umiditaAria) {
        this.umiditaAria = umiditaAria;
    }

    public void setAperto(boolean aperto) {
        this.aperto = aperto;
    }

    public void setBagnato(boolean bagnato) {
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
        String temp = oraRilevazione + " " + tipoPianta + " " +temperaturaAria + " " + umiditaAria + " " +  bagnato + " " + aperto;
        return temp;
    }
}