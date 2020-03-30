/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Reprezentacija;
import domen.Utakmica;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bron Zilar
 */
public class ModelTabeleUtakmica extends AbstractTableModel {
    ArrayList<Utakmica> lista;
    ArrayList<Utakmica> listaObrisanih;

    public ModelTabeleUtakmica() {
        lista = new ArrayList<>();
        listaObrisanih = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Utakmica u = lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return u.getGrupa();
            case 1:
                return u.getDomacin();
            case 2:
                return u.getGost();
            case 3:
                return u.getBrojGlovaDomacina();
            case 4:
                return u.getBrojGolovaGost();
            case 5:
                return u.getStatus();
            default: return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Grupa";
            case 1:
                return "Domacin";
            case 2:
                return "Gost";
            case 3:
                return "Golovi domacin";
            case 4:
                return "Golovi gost";
            case 5:
                return "Status";
            default: return "";
        }
    }

    public void setLista(ArrayList<Utakmica> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public void obrisiUtakmicu(int red) {
        Utakmica u = lista.remove(red);
        listaObrisanih.add(u);
        u.setStatus("brisanje");
        fireTableDataChanged();
    }

//    @Override
//    public boolean isCellEditable(int rowIndex, int columnIndex) {
//        return columnIndex == 0 || columnIndex == 1 || columnIndex == 2 || columnIndex == 3 || columnIndex == 4;
//    }
//
//    @Override
//    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        Utakmica u = lista.get(rowIndex);
//        switch(columnIndex) {
//            case 0:
//                u.setGrupa(aValue.toString());
//                break;
//            case 1:
//                u.getDomacin().setNaziv(aValue.toString());
//                break;
//            case 2:
//                u.getGost().setNaziv(aValue.toString());
//                break;
//            case 3:
//                u.setBrojGlovaDomacina(Integer.parseInt(aValue.toString()));
//                break;
//            case 4:
//                u.setBrojGolovaGost(Integer.parseInt(aValue.toString()));
//                break;
//        }
//    }

    public Utakmica dajUtakmicu(int red) {
        return lista.get(red);
    }

    public boolean izmeni(Utakmica u) {
        if(u.getGost().getReprezentacijaID()==u.getDomacin().getReprezentacijaID()) {
            JOptionPane.showMessageDialog(null, "Domacin i gost ne mogu biti isti");
            return false;
        }
        String[] nizDozvoljenih = {"A","B","C","D","E","F"};
        boolean flag = false;
        for(int i = 0; i < nizDozvoljenih.length; i++) {
            if(nizDozvoljenih[i].equals(u.getGrupa())) {
                flag = true;
            }
        } 
        if(!flag) {
            JOptionPane.showMessageDialog(null, "Grupa moze imati vrednosti A,B,C,D,E ili F");
            return false;
        }
        for (Utakmica utakmica : lista) {
            if(utakmica.getDomacin().getReprezentacijaID()==u.getDomacin().getReprezentacijaID() && utakmica.getGost().getReprezentacijaID()==u.getGost().getReprezentacijaID()) {
                JOptionPane.showMessageDialog(null, "Vec postoji dati par ekipa");
                return false;
            }
        }
        for (Utakmica utakmica : lista) {
            if(utakmica.getUtakmicaID()==u.getUtakmicaID()) {
                utakmica.setGrupa(u.getGrupa());
                utakmica.setDomacin(u.getDomacin());
                utakmica.setGost(u.getGost());
                utakmica.setBrojGlovaDomacina(u.getBrojGlovaDomacina());
                utakmica.setBrojGolovaGost(u.getBrojGolovaGost());
                utakmica.setStatus("izmena");
            }
        }
        fireTableDataChanged();
        return true;
    }

    public ArrayList<Utakmica> vratiListuSvihUtakmica() {
        ArrayList<Utakmica> lista1 = lista;
        lista1.addAll(listaObrisanih);
        return lista1;
        
    }
    
    
    
}
