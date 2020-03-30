/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import pomocneklase.PomocnaKlasa;

/**
 *
 * @author KORISNIK
 */
public class ModelTabeleServer extends AbstractTableModel {
    ArrayList<PomocnaKlasa> lista;

    public ModelTabeleServer(ArrayList<PomocnaKlasa> lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PomocnaKlasa pk = lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return pk.getNazivLeka();
            case 1:
                return pk.getCena();
            case 2:
                return pk.getBrojKomada();
            case 3:
                return pk.getRashodi();
            case 4:
                return pk.getPrihodi();
            default: return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "NAziv leka";
            case 1:
                return "Cena";
            case 2:
                return "Broj prepisanih komada";
            case 3:
                return "Ukupni rashodi";
            case 4:
                return "Ukupni prihodi";
            default: return "";
        }
    }
    
    
    
}
