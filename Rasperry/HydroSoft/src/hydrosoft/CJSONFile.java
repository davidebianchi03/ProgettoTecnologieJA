package hydrosoft;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pacie
 */
public class CJSONFile {

    private final String nomeFileSingolaRilevazione;
    private final String nomeFileListaRilevazioni;
    private final ObjectMapper mapper;
    private String jsonObject;
    private String jsonArray;
    private String lettaLista;

    public CJSONFile() {
        this.mapper = new ObjectMapper();
        this.nomeFileSingolaRilevazione = "rilevazioniSerraSingola.json";
        this.nomeFileListaRilevazioni = "rilevazioniSerraLista.json";
        this.jsonObject = "";
        this.jsonArray = "";
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
            jsonObject = "";
            jsonObject = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(on);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(CJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void appendJSONOject(CDati dati) {
        String appendJSON = "";
        if (lettaLista.length() < 1) {
            jsonArray = "[";
            try {
                ObjectNode on = createObjectNode(dati);
                appendJSON = "";
                appendJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(on);
            } catch (JsonProcessingException ex) {
                Logger.getLogger(CJSONFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            jsonArray += appendJSON + "]";
        } else {
            int end = lettaLista.indexOf(']');
            StringBuilder sb = new StringBuilder(lettaLista);
            sb.deleteCharAt(end);
            lettaLista = sb.toString();
            try {
                ObjectNode on = createObjectNode(dati);
                appendJSON = "";
                appendJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(on);
            } catch (JsonProcessingException ex) {
                Logger.getLogger(CJSONFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            lettaLista += "," + appendJSON + "]";
            jsonArray = lettaLista;
            //System.out.println(lettaLista);
        }
    }

    public void createFile() {
        try {
            FileWriter scriviRS = new FileWriter(nomeFileSingolaRilevazione);
            scriviRS.write(jsonObject);
            scriviRS.close();
        } catch (IOException ex) {
            Logger.getLogger(CJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            FileWriter scriviRL = new FileWriter(nomeFileListaRilevazioni);
            scriviRL.write(jsonArray);
            scriviRL.close();
            //System.out.println("File completato.");
        } catch (IOException ex) {
            Logger.getLogger(CJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readFile() {
        Scanner reader;
        lettaLista = "";
        File flr = new File(nomeFileListaRilevazioni);
        try {
            if (!flr.createNewFile()) {
                try {
                    reader = new Scanner(flr);
                    while (reader.hasNextLine()) {
                        lettaLista += reader.next();
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CJSONFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(CJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNomeFileSR() {
        return nomeFileSingolaRilevazione;
    }
    
    public String getNomeFileLR() {
        return nomeFileListaRilevazioni;
    }
    
    public String getJsonObjectAsString() {
        return jsonObject;
    }

    public String getJsonArrayAsString() {
        return jsonArray;
    }
}
