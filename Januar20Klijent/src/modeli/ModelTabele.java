/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Prevod;
import domen.Rec;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bron Zilar
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
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prevod p = lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return p.getRec().getRec();
            case 1:
                return p.getPrevod();
            case 2:
                return p.getSaJezika();
            case 3:
                return p.getNaJezik();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Rec";
            case 1:
                return "Prevod";
            case 2:
                return "Sa jezika";
            case 3:
                return "Na jezik";
            default:
                return "";
        }
    }

    public void dodajprevod(Prevod p) {
        lista.add(p);
        fireTableDataChanged();
    }

    public void obrisiPrevod(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }

    public ArrayList<Prevod> getLista() {
        return lista;
    }
  
}
