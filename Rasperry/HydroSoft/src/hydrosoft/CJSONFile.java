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
import java.util.ArrayList;
import java.util.List;
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

    public void readFile(CListaDati listaDati) {
        Scanner reader;
        String lettaSingola = "";
        String lettaLista = "";
        try {
            File fsr = new File(nomeFileSingolaRilevazione);
            reader = new Scanner(fsr);
            while (reader.hasNextLine()) {
                lettaSingola += reader.nextLine();
            }
            File flr = new File(nomeFileListaRilevazioni);
            reader = new Scanner(flr);
            while (reader.hasNextLine()) {
                lettaLista += reader.nextLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(lettaSingola);
        System.out.println(lettaLista);

        //Devo trasformare la string in una CListaDati
        listaDati.clearList();
        CDati temp = new CDati();
        int startElem = 0;
        String elem;

        do {
            //POSIZIONI GRAFFE
            int posGraffaAperta = lettaLista.indexOf('{', startElem);
            int posGraffaChiusa = lettaLista.indexOf('}', posGraffaAperta);
            
            System.out.println(startElem);
            System.out.println(posGraffaAperta);
            System.out.println(posGraffaChiusa);
            //SINGOLO ELEMENTO
            elem = lettaLista.substring(posGraffaAperta, posGraffaChiusa + 1);
            //POSIZIONI SEPARATORI
            int posPnti = elem.indexOf(':', posGraffaAperta);
            int posVirg = elem.indexOf(',', posPnti);
            //ORA RILEVAZIONE
            String dato = elem.substring(posPnti + 3, posVirg - 1);
            temp.setOraRilevazione(dato);

            //TIPO PIANTA
            posPnti = elem.indexOf(':', posVirg);
            posVirg = elem.indexOf(',', posPnti);
            dato = elem.substring(posPnti + 3, posVirg - 1);
            temp.setTipoPianta(dato);

            //TEMPERATURA ARIA
            posPnti = elem.indexOf(':', posVirg);
            posVirg = elem.indexOf(',', posPnti);
            dato = elem.substring(posPnti + 2, posVirg);
            temp.setTemperaturaAria(Float.parseFloat(dato));

            //UMIDITA ARIA
            posPnti = elem.indexOf(':', posVirg);
            posVirg = elem.indexOf(',', posPnti);
            dato = elem.substring(posPnti + 2, posVirg);
            temp.setUmiditaAria(Float.parseFloat(dato));

            //BAGNATO
            posPnti = elem.indexOf(':', posVirg);
            posVirg = elem.indexOf(',', posPnti);
            dato = elem.substring(posPnti + 2, posVirg);
            temp.setBagnato(Boolean.parseBoolean(dato));

            //APERTO
            posPnti = elem.indexOf(':', posVirg);
            dato = elem.substring(posPnti + 2, posGraffaChiusa - 2);
            temp.setAperto(Boolean.parseBoolean(dato));
            listaDati.addDati(temp);
            
            startElem = posGraffaChiusa;
            System.out.println(listaDati.getDati(0));
        }  while (startElem < (lettaLista.length() - elem.length()));
        
       
            
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
