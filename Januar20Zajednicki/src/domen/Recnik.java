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
public class Recnik implements Serializable {
    private int recnikID;
    private String jezik;

    public Recnik() {
    }

    public Recnik(int recnikID, String jezik) {
        this.recnikID = recnikID;
        this.jezik = jezik;
    }

    public String getJezik() {
        return jezik;
    }

    public void setJezik(String jezik) {
        this.jezik = jezik;
    }

    public int getRecnikID() {
        return recnikID;
    }

    public void setRecnikID(int recnikID) {
        this.recnikID = recnikID;
    }

    @Override
    public String toString() {
        return jezik;
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
        final Recnik other = (Recnik) obj;
        if (this.recnikID != other.recnikID) {
            return false;
        }
        return true;
    }
    
    
}
