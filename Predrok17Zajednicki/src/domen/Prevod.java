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
 * @author KORISNIK
 */
public class Prevod implements Serializable{
    private int prevodID;
    private Korisnik korisnik;
    private String prevod;

    public Prevod() {
    }

    public Prevod(int prevodID, Korisnik korisnik, String prevod) {
        this.prevodID = prevodID;
        this.korisnik = korisnik;
        this.prevod = prevod;
    }

    public String getPrevod() {
        return prevod;
    }

    public void setPrevod(String prevod) {
        this.prevod = prevod;
    }

    public int getPrevodID() {
        return prevodID;
    }

    public void setPrevodID(int prevodID) {
        this.prevodID = prevodID;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    public String toString() {
        return prevod+"("+korisnik+"); ";
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
        if (!Objects.equals(this.prevod, other.prevod)) {
            return false;
        }
        return true;
    }
    
    
}
