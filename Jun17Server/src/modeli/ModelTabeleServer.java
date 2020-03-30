/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import pomocneKlase.PomocnaKlasa;

/**
 *
 * @author Bron Zilar
 */
public class ModelTabeleServer extends AbstractTableModel {

    ArrayList<PomocnaKlasa> lista;

    public ModelTabeleServer() {
        lista = new ArrayList<>();
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
        PomocnaKlasa pm = lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return pm.getOprema();
            case 1:
                 return pm.getOprema().getProizvodjac();
            case 2:
                return pm.getOprema().getCenaPoDanu();
            case 3:
                return pm.getBrojDana();
            case 4:
                return pm.getPrihod();
            default:
                    return "";
                 
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Oprema";
            case 1:
                 return "Proizvodjac";
            case 2:
                return "cena po danu";
            case 3:
                return "Broj dana";
            case 4:
                return "Prihod";
            default:
                    return "";
                 
        }
    }

    public ArrayList<PomocnaKlasa> getLista() {
        return lista;
    }

    public void setLista(ArrayList<PomocnaKlasa> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }
    
    
}
