package com.example.hydrosoftandroid;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class ElaboraDati {
    private String datiJSON;

    private String ora;
    private String tipo;
    private float temperatura;
    private float umidita;
    private boolean bagnato;
    private boolean aperto;
    public ElaboraDati(String datiJSON){
        this.datiJSON = datiJSON;
        try {
            JSONObject object = new JSONObject(datiJSON);
            this.ora = object.getString("oraRilevazione");
            this.tipo = object.getString("tipoPianta");
            this.temperatura = Float.parseFloat(object.getString("temperaturaAria"));
            this.umidita = Float.parseFloat(object.getString("umiditaAria"));
            this.bagnato = object.getBoolean("bagnato");
            this.aperto = object.getBoolean("aperto");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String getOra(){
        return ora;
    }
    public String getTipo(){
        return tipo;
    }
    public float getTemperatura(){
        return temperatura;
    }
    public float getUmidita(){
        return umidita;
    }
    public boolean getBagnato(){
        return bagnato;
    }
    public boolean getAperto(){
        return aperto;
    }
}
