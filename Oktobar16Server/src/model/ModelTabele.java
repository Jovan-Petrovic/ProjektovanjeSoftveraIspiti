/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import pomocna.PodaciZaTabelu;

/**
 *
 * @author KORISNIK
 */
public class ModelTabele extends AbstractTableModel {
    
    ArrayList<PodaciZaTabelu> lista;

    public ModelTabele(ArrayList<PodaciZaTabelu> lista) {
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
        PodaciZaTabelu p = lista.get(rowIndex);
        switch(columnIndex) {
            case 0: return p.getNazivSP();
            case 1: return p.getSkolskaGodina();
            case 2: return p.getBrojKandidata();
            case 3: return p.getPrihod();
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Stud prog.";
            case 1: return "Skolska godina";
            case 2: return "Broj kandidata";
            case 3: return "Prihod";
            default: return "n/a";
        }
    }
    
    
}
