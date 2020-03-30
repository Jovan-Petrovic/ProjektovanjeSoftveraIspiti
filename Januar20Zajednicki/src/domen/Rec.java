/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Bron Zilar
 */
public class Rec implements Serializable{
    private int recID;
    private String rec;
    private Recnik recnik;
    private ArrayList<Prevod> prevodi;

    public Rec() {
    }

    public Rec(int recID, String rec, Recnik recnik, ArrayList<Prevod> prevodi) {
        this.recID = recID;
        this.rec = rec;
        this.recnik = recnik;
        this.prevodi = prevodi;
    }

    

    

   

    public int getRecID() {
        return recID;
    }

    public void setRecID(int recID) {
        this.recID = recID;
    }

    public String getRec() {
        return rec;
    }

    public void setRec(String rec) {
        this.rec = rec;
    }

    public Recnik getRecnik() {
        return recnik;
    }

    public void setRecnik(Recnik recnik) {
        this.recnik = recnik;
    }

    @Override
    public String toString() {
        return rec;
    }

    public ArrayList<Prevod> getPrevodi() {
        return prevodi;
    }

    public void setPrevodi(ArrayList<Prevod> prevodi) {
        this.prevodi = prevodi;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rec other = (Rec) obj;
        if (!Objects.equals(this.rec, other.rec)) {
            return false;
        }
        return true;
    }

    

    
    
    
    
}
