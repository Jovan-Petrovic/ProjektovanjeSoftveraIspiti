/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import pomocneKlase.pretragaKlasa;

/**
 *
 * @author Bron Zilar
 */
public class ModelTabelePretraga extends AbstractTableModel{
    List<pretragaKlasa> lista;

    public ModelTabelePretraga() {
        lista = new ArrayList<>();
    }

    public void setLista(List<pretragaKlasa> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int red, int kolona) {
        pretragaKlasa pk = lista.get(red);
        
        switch(kolona) {
            case 0:
                return pk.getImePrezime();
            case 1:
                return pk.getBrojpredmeta();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Ime i prezime";
            case 1: return "Broj bredmeta";
            default: return "n/a";
        }
    }
    
    
}
