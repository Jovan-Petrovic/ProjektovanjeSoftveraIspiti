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
public class Stanica implements Serializable {
    
    private int stanicaID;
    private String nazivStanice;

    public Stanica() {
    }

    public Stanica(int stanicaID, String nazivStanice) {
        this.stanicaID = stanicaID;
        this.nazivStanice = nazivStanice;
    }

    public String getNazivStanice() {
        return nazivStanice;
    }

    public void setNazivStanice(String nazivStanice) {
        this.nazivStanice = nazivStanice;
    }

    public int getStanicaID() {
        return stanicaID;
    }

    public void setStanicaID(int stanicaID) {
        this.stanicaID = stanicaID;
    }

    @Override
    public String toString() {
        return nazivStanice;
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
        final Stanica other = (Stanica) obj;
        if (this.stanicaID != other.stanicaID) {
            return false;
        }
        return true;
    }
    
    
}
