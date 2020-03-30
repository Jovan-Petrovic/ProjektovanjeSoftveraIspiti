/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import pomocne.PomocnaKlasa;

/**
 *
 * @author Bron Zilar
 */
public class ModelTabeleServer extends AbstractTableModel {

    ArrayList<PomocnaKlasa> lista;

    public ModelTabeleServer() {
        lista = new ArrayList<>();
    }

    public ModelTabeleServer(ArrayList<PomocnaKlasa> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }
    
    

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PomocnaKlasa pk = lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return pk.getLinija().getLinijaID();
            case 1:
                return pk.getLinija().getPocetnaStanica();
            case 2:
                return pk.getLinija().getKrajnjaStanica();
            case 3:
                return pk.getMedjustanice();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Naziv linije";
            case 1:
                return "Pocetna stanica";
            case 2:
                return "Krajnja stanica";
            case 3:
                return "Medjustanice";
            default:
                return "";
        }
    }
    
    
}
