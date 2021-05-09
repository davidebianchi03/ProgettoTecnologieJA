package hydrosoft;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pacie
 */
public class CJSONFile {

    private String json;
    private CListaDati listaDati;
    private String nomeFile;

    public CJSONFile(CListaDati listaDati) {
        this.nomeFile = "rilevazioniSerra.json";
        this.json = "";
        this.listaDati = listaDati;
    }

    public void createJSON() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode arrayNode = mapper.createArrayNode();
            for (int i = 0; i < listaDati.getNumEl(); i++) {
                ObjectNode tempDati = mapper.createObjectNode();
                tempDati.put("oraRilevazione", listaDati.getDati(i).getOraRilevazione());
                tempDati.put("tempAria", listaDati.getDati(i).getTemperaturaAria());
                tempDati.put("umidAria", listaDati.getDati(i).getUmiditaAria());
                tempDati.put("bagnato", listaDati.getDati(i).isBagnato());
                tempDati.put("aperto", listaDati.getDati(i).isAperto());

                arrayNode.add(tempDati);
            }
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
            //System.out.println(json);
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
        /*
            TODO: Inserire verifica esistenza del file prima di leggerlo 
        */
        ObjectMapper mapper = new ObjectMapper();
    }
    
    public String getNomeFile() {
        return nomeFile;
    }
}