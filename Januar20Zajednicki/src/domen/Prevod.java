/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Bron Zilar
 */
public class Prevod implements Serializable {
    private int prevodID;
    private String prevod;
    private Recnik saJezika;
    private Recnik naJezik;
    private Rec rec;
    private int red;

    public Prevod() {
    }

    public Prevod(int prevodID, String prevod, Recnik saJezika, Recnik naJezik, Rec rec) {
        this.prevodID = prevodID;
        this.prevod = prevod;
        this.saJezika = saJezika;
        this.naJezik = naJezik;
        this.rec = rec;
    }

    

    public Recnik getNaJezik() {
        return naJezik;
    }

    public void setNaJezik(Recnik naJezik) {
        this.naJezik = naJezik;
    }

    public int getPrevodID() {
        return prevodID;
    }

    public void setPrevodID(int prevodID) {
        this.prevodID = prevodID;
    }

    public String getPrevod() {
        return prevod;
    }

    public void setPrevod(String prevod) {
        this.prevod = prevod;
    }

    public Recnik getSaJezika() {
        return saJezika;
    }

    public void setSaJezika(Recnik saJezika) {
        this.saJezika = saJezika;
    }

    @Override
    public String toString() {
        return prevod;
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
        final Prevod other = (Prevod) obj;
        if (this.prevodID != other.prevodID) {
            return false;
        }
        return true;
    }


    
    
    

    public Rec getRec() {
        return rec;
    }

    public void setRec(Rec rec) {
        this.rec = rec;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

   

    

    
    
    
}
