package hydrosoft;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;
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
    private String jsonSingolo;
    private String jsonLista;
    private String nomeFileSingolaRilevazione;
    private String nomeFileListaRilevazioni;

    public CJSONFile() {
        this.mapper = new ObjectMapper();
        this.nomeFileSingolaRilevazione = "rilevazioniSerraSingola.json";
        this.nomeFileListaRilevazioni = "rilevazioniSerraLista.json";
        this.jsonSingolo = "";
        this.jsonLista = "";
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
            jsonSingolo = "";
            jsonSingolo = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(on);
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
            jsonLista = "";
            jsonLista = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(CJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createFile() {
        try {
            FileWriter scriviRS = new FileWriter(nomeFileSingolaRilevazione);
            scriviRS.write(jsonSingolo);
            scriviRS.close();
            FileWriter scriviRL = new FileWriter(nomeFileListaRilevazioni);
            scriviRL.write(jsonLista);
            scriviRL.close();
            //System.out.println("File completato.");
        } catch (IOException ex) {
            Logger.getLogger(CJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readFile() {
        Scanner reader;
        String singRilLetta = "";
        String listRilLetta = "";
        try {
            File fsr = new File(nomeFileSingolaRilevazione);
            reader = new Scanner(fsr);
            while (reader.hasNextLine()) {
                singRilLetta = reader.nextLine();
                System.out.println(singRilLetta);
            }
            File flr = new File(nomeFileListaRilevazioni);
            reader = new Scanner(flr);
            while (reader.hasNextLine()) {
                listRilLetta = reader.nextLine();
                System.out.println(listRilLetta);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNomeFile() {
        return nomeFileSingolaRilevazione;
    }

    public String getJsonObjectAsString() {
        return jsonSingolo;
    }

    public String getJsonArrayAsString() {
        return jsonLista;
    }
}
