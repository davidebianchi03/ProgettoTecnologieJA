package hydrosoft;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pacie
 */
public class CListaDati {
    private List<CDati> lista;
    public CListaDati(){
        this.lista = new ArrayList<CDati>();
    }
    public void addDati(CDati dati){
        lista.add(dati);
    }
    public int getNumEl(){
        return lista.size();
    }
    public CDati getDati(int index){
        return lista.get(index);
    }
}
