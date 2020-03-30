/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Predmet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bron Zilar
 */
public class ModelTabelePredmeti extends AbstractTableModel {
    ArrayList<Predmet> predmeti;
    String[] kolone = {"Advokat","Klijent","Datum","Naziv"};
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");

    public ModelTabelePredmeti() {
        predmeti = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return predmeti.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int red, int kolona) {
        Predmet p = predmeti.get(red);
        switch(kolona) {
            case 0:
                return p.getAdvokat();
            case 1:
                return p.getKlijent();
            case 2:
                return sdf.format(p.getDatum());
            case 3:
                return p.getNaziv();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public void dodajPredmet(Predmet p) {
        predmeti.add(p);
        fireTableDataChanged();
    }
    
    public void obrisiPredmet(int red) {
        predmeti.remove(red);
        fireTableDataChanged();
    }

    public ArrayList<Predmet> getPredmeti() {
        return predmeti;
    }

    public void ocistiTabelu() {
        predmeti = new ArrayList<>();
        fireTableDataChanged();
    }
    
    
}
