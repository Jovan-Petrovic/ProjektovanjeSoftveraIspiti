/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;
import pomocniPaket.PomocnaKlasa;

/**
 *
 * @author Bron Zilar
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
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PomocnaKlasa pk = lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return pk.getNazivReprezentacije();
            case 1:
                return pk.getBrojDatihGolova();
            case 2:
                return pk.getBrojPrimljenihGolova();
            case 3:
                return pk.getGolRazlika();
            default: 
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Reprezentacija";
            case 1:
                return "Golova dato";
            case 2:
                return "Golova primljeno";
            case 3:
                return "Gol razlika";
            default: 
                return "";
        }
    }
    
    
}
