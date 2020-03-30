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
public class Linija implements Serializable  {
    private int linijaID;
    private String nazivLinije;
    private Stanica pocetnaStanica;
    private Stanica krajnjaStanica;

    public Linija() {
    }

    public Linija(int linijaID, String nazivLinije, Stanica pocetnaStanica, Stanica krajnjaStanica) {
        this.linijaID = linijaID;
        this.nazivLinije = nazivLinije;
        this.pocetnaStanica = pocetnaStanica;
        this.krajnjaStanica = krajnjaStanica;
    }

    public Stanica getKrajnjaStanica() {
        return krajnjaStanica;
    }

    public void setKrajnjaStanica(Stanica krajnjaStanica) {
        this.krajnjaStanica = krajnjaStanica;
    }

    public int getLinijaID() {
        return linijaID;
    }

    public void setLinijaID(int linijaID) {
        this.linijaID = linijaID;
    }

    public String getNazivLinije() {
        return nazivLinije;
    }

    public void setNazivLinije(String nazivLinije) {
        this.nazivLinije = nazivLinije;
    }

    public Stanica getPocetnaStanica() {
        return pocetnaStanica;
    }

    public void setPocetnaStanica(Stanica pocetnaStanica) {
        this.pocetnaStanica = pocetnaStanica;
    }

    @Override
    public String toString() {
        return nazivLinije;
    }
    
    
}
