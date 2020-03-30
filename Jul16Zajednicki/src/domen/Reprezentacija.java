/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author Bron Zilar
 */
public class Reprezentacija implements Serializable{
    private int reprezentacijaID;
    private String naziv;

    public Reprezentacija() {
    }

    public Reprezentacija(int reprezentacijaID, String naziv) {
        this.reprezentacijaID = reprezentacijaID;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getReprezentacijaID() {
        return reprezentacijaID;
    }

    public void setReprezentacijaID(int reprezentacijaID) {
        this.reprezentacijaID = reprezentacijaID;
    }

    @Override
    public String toString() {
        return naziv;
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
        final Reprezentacija other = (Reprezentacija) obj;
        if (this.reprezentacijaID != other.reprezentacijaID) {
            return false;
        }
        return true;
    }
    
    
}
