package hydrosoft;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pacie
 */
public class CJSONFile {

    /*
    FUNZIONAMENTO:
    Creo l'oggetto json
    Creo il file
    Restituisco nome file
    
    -Aggiuntivo:
    Restituisco json come stringa
     */
    private ObjectMapper mapper;
    private String json;
    private String nomeFile;

    public CJSONFile() {
        this.mapper = new ObjectMapper();
        this.nomeFile = "rilevazioniSerra.json";
        this.json = "";

        //Crea il file, se esite già non lo sovrascrive -> nel caso della lettura per non fare il controllo se esite il file
        try {

            File f = new File(nomeFile);
            if (f.createNewFile()) {
                System.out.println("file creato");
            }

        } catch (IOException ex) {
            Logger.getLogger(CJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ObjectNode createObjectNode(CDati dati) {
        //Valori modificabili con AsString nel caso si voglia la string invece del valore        
        ObjectNode on = mapper.createObjectNode();
        on.put("oraRilevazione", dati.getOraRilevazione());
        on.put("tipoPianta", dati.getTipoPianta());
        on.put("temperaturaAria", dati.getTemperaturaAria());
        on.put("umiditaAria", dati.getUmiditaAria());
        on.put("bagnato", dati.isBagnato());
        on.put("aperto", dati.isAperto());
        return on;
    }

    public void createJSONObject(CDati dati) {
        try {
            ObjectNode on = createObjectNode(dati);
            json = "";
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(on);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(CJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createJSONArray(CListaDati listaDati) {
        try {
            ArrayNode arrayNode = mapper.createArrayNode();
            for (int i = 0; i < listaDati.getNumEl(); i++) {
                ObjectNode on = createObjectNode(listaDati.getDati(i));
                arrayNode.add(on);
            }
            json = "";
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(CJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createFile() {
        try {
            FileWriter scrivi = new FileWriter(nomeFile);
            scrivi.write(json);
            scrivi.close();
            System.out.println("File completato.");
        } catch (IOException ex) {
            Logger.getLogger(CJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readFile() {
    }

    public String getNomeFile() {
        return nomeFile;
    }

    public String getJsonAsString() {
        return json;
    }
}
