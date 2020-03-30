/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.StavkaKursneListe;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bron Zilar
 */
public class ModelTabeleStavke extends AbstractTableModel {

    private ArrayList<StavkaKursneListe> lista;

    public ModelTabeleStavke() {
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
        StavkaKursneListe skl = lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return skl.getZemlja();
            case 1:
                return skl.getZemlja() == null ? "" : skl.getZemlja().getValuta();
            case 2:
                return skl.getVaziZa();
            case 3:
                return skl.getKupovni();
            case 4:
                return skl.getProdajni();
            default:
                return "";
                
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Zemlja";
            case 1:
                return "Valuta";
            case 2:
                return "Vazi za";
            case 3:
                return "Kupovni";
            case 4:
                return "Prodajni";
            default:
                return "";
                
        }
    }

    public void dodajStavku(StavkaKursneListe stavka) {
        lista.add(stavka);
        fireTableDataChanged();
        srediRedneBrojeve();
    }

    public void obrisiRed(int red) {
        lista.remove(red);
        fireTableDataChanged();
        srediRedneBrojeve();
    }

    public ArrayList<StavkaKursneListe> getLista() {
        return lista;
    }

    public void setLista(ArrayList<StavkaKursneListe> lista) {
        this.lista = lista;
    }

    private void srediRedneBrojeve() {
        int brojac = 1;
        for (StavkaKursneListe stavkaKursneListe : lista) {
            stavkaKursneListe.setRb(brojac);
            brojac++;
        }
    }
    
    
    
    
    
}
