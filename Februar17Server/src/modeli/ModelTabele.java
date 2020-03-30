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
public class ModelTabele extends AbstractTableModel {

    private ArrayList<StavkaKursneListe> lista;

    public ModelTabele(ArrayList<StavkaKursneListe> lista) {
        this.lista = lista;
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
            case 5:
                return skl.getSrednji();
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
                return "prosecni kupovni";
            case 4:
                return "Prosecni prodajni";
            case 5:
                return "Prosecni srednji";
            default:
                return "";
                
        }
    }
    
}
