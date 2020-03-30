/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Prevod;
import domen.Rec;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author KORISNIK
 */
public class ModelTabele extends AbstractTableModel {

    List<Rec> listaReci;

    public ModelTabele(List<Rec> listaReci) {
        this.listaReci = listaReci;
    }

    @Override
    public int getRowCount() {
        return listaReci.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rec r = listaReci.get(rowIndex);
        switch(columnIndex) {
            case 0: return r;
            case 1:
                ArrayList<Prevod> listaPrevoda = r.getListaPrevoda();
                String nalepi = "";
                for (Prevod p : listaPrevoda) {
                    nalepi+=p;
                }
                return nalepi;
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Rec - SRB";
            case 1: return "Prevod - ENG";
            default: return "n/a";
        }
    }
    
    
}
