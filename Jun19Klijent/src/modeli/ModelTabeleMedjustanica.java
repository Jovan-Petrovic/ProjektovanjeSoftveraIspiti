/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Stanica;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bron Zilar
 */
public class ModelTabeleMedjustanica extends AbstractTableModel {

    private ArrayList<Stanica> stanice;

    public ModelTabeleMedjustanica() {
        stanice = new ArrayList<>();
    }
 
    @Override
    public int getRowCount() {
        return stanice.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Stanica s = stanice.get(rowIndex);
        if(columnIndex==0) {
            return s.getNazivStanice();
        } else {
            return "greska";
        }
    }

    @Override
    public String getColumnName(int column) {
        if(column==0) {
            return "Naziv medjustanice";
        } else {
            return "greska";
        }
    }

    public ArrayList<Stanica> getStanice() {
        return stanice;
    }

    public void setStanice(ArrayList<Stanica> stanice) {
        this.stanice = stanice;
        fireTableDataChanged();
    }

    public void dodajMedjustanicu(Stanica medjustanica) {
        stanice.add(medjustanica);
        fireTableDataChanged();
    }
    
    
    
}
