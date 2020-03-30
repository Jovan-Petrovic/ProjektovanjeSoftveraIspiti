/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Oprema;
import domen.StavkaRentiranja;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bron Zilar
 */
public class ModelTabele extends AbstractTableModel {

    ArrayList<StavkaRentiranja> lista;

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
        StavkaRentiranja stavka = lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return stavka.getRb();
            case 1:
                return stavka.getOprema();
            case 2:
                return stavka.getOprema().getCenaPoDanu();
            case 3:
                return stavka.getIznos();
            default:
                return "";   
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Redni broj";
            case 1:
                return "Oprema";
            case 2:
                return "Cena po danu";
            case 3:
                return "Iznos";
            default:
                return "";   
        }
    }

    public void dodajStavku(Oprema o) {
        StavkaRentiranja s = new StavkaRentiranja(0, o, 0);
        lista.add(s);
        srediRb();
        fireTableDataChanged();
    }

    private void srediRb() {
        int brojac = 1;
        for (StavkaRentiranja stavkaRentiranja : lista) {
            stavkaRentiranja.setRb(brojac);
            brojac++;
        }
    }

    public void obrisiStavku(int red) {
        lista.remove(red);
        srediRb();
        fireTableDataChanged();
    }

    public ArrayList<StavkaRentiranja> getListaStavki() {
        return lista;
    }

    public void obrisiTabelu() {
        lista = new ArrayList<>();
        fireTableDataChanged();
    }
    
    
    
}
