/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Prevod;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author KORISNIK
 */
public class ModelTabele extends AbstractTableModel {
    
    ArrayList<Prevod> lista;

    public ModelTabele() {
        lista = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prevod p = lista.get(rowIndex);
        if(columnIndex == 0) {
            return p.getPrevod();
        } else {
            return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        if(column==0) {
            return "Prevod";
        } else {
            return "n/a";
        }
    }

    public void dodajPrevod(Prevod p) {
        lista.add(p);
        fireTableDataChanged();
    }

    public ArrayList<Prevod> getLista() {
        return lista;
    }
    
    
}
