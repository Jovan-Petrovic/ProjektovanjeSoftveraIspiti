/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medeli;

import domen.StavkaPoreskePrijave;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bron Zilar
 */
public class ModelTabele extends AbstractTableModel {

    private ArrayList<StavkaPoreskePrijave> lista;

    public ModelTabele() {
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
        StavkaPoreskePrijave stavka = lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return stavka.getRb();
            case 1:
                return stavka.getDatumPrometa();
            case 2:
                return stavka.getVrsta();
            case 3:
                return stavka.getVrednost();
            case 4:
                return stavka.getNapomena();
            default:
                return "greska";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Redni broj";
            case 1:
                return "Datum prometa";
            case 2:
                return "Vrsta";
            case 3:
                return "Vrednost";
            case 4:
                return "Napomena";
            default:
                return "greska";
        }
    }

    public void dodajStavku(StavkaPoreskePrijave stavka) {
        lista.add(stavka);
        fireTableDataChanged();
    }

    public int vratiRedniBroj() {
        return lista.size()+1;
    }

    public ArrayList<StavkaPoreskePrijave> getLista() {
        return lista;
    }

    public void obrisiStavku(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }
    
    
    
}
