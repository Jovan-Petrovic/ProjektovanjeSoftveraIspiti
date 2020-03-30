/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Recept;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author KORISNIK
 */
public class ModelTabeleRecept extends AbstractTableModel {

    ArrayList<Recept> recepti;

    public ModelTabeleRecept(ArrayList<Recept> recepti) {
        this.recepti = recepti;
    }

    @Override
    public int getRowCount() {
        return recepti.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Recept r = recepti.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return r.getLekar();
            case 1:
                return r.getOsiguranoLice();
            case 2:
                return r.getLek();
            case 3: 
                return r.getLek().getCena();
            case 4:
                return r.getKolicina();
            case 5:
                return r.getUkupanIznos();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Lekar";
            case 1:
                return "Osigurano lice";
            case 2:
                return "Lek";
            case 3: 
                return "Cena";
            case 4:
                return "Kolicina";
            case 5:
                return "Ukupan iznos";
            default:
                return "";
        }
    }

    public ArrayList<Recept> getRecepti() {
        return recepti;
    }

    public int vratiID() {
        int broj = recepti.size();
        return ++broj;
    }

    public void dodajRecept(Recept recept) {
        recepti.add(recept);
        fireTableDataChanged();
    }

    public Recept nadjiRecept(int red) {
        return recepti.get(red);
    }

    public void obrisiRecept(int red) {
        recepti.remove(red);
        fireTableDataChanged();
    }

    public void isprazniTabelu() {
        recepti = new ArrayList<>();
        fireTableDataChanged();
    }
    
    
}
