/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Kompanija;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author KORISNIK
 */
public class ModelTabeleStartapi extends AbstractTableModel {
    
    ArrayList<Kompanija> listaKompanija;

    public ModelTabeleStartapi(ArrayList<Kompanija> listaKompanija) {
        this.listaKompanija = listaKompanija;
    }
       
    @Override
    public int getRowCount() {
        return listaKompanija.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kompanija k = listaKompanija.get(rowIndex);
        switch(columnIndex) {
            case 0: return k.getNaziv();
            case 1: return k.getOplastPoslovanja();
            case 2: return k.isIpo();
            case 3: return k.getUkupanIznos();
            case 4: return k.getTip();
            default: return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "NAziv";
            case 1: return "Oblast poslovanja";
            case 2: return "IPO";
            case 3: return "Ukupne investivcije";
            case 4: return "Tip";
            default: return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex==2) {
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

    public Kompanija dajKompaniju(int selektovanRed) {
        return listaKompanija.get(selektovanRed);
    }
    
    
}
