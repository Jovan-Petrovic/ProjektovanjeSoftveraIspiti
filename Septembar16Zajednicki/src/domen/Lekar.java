/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author KORISNIK
 */
public class Lekar implements Serializable {
    private int lekarID;
    private String ime;
    private String prezime;
    private String specijalnost;

    public Lekar() {
    }

    public Lekar(int lekarID, String ime, String prezime, String specijalnost) {
        this.lekarID = lekarID;
        this.ime = ime;
        this.prezime = prezime;
        this.specijalnost = specijalnost;
    }

    public String getSpecijalnost() {
        return specijalnost;
    }

    public void setSpecijalnost(String specijalnost) {
        this.specijalnost = specijalnost;
    }

    public int getLekarID() {
        return lekarID;
    }

    public void setLekarID(int lekarID) {
        this.lekarID = lekarID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
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
        final Lekar other = (Lekar) obj;
        if (this.lekarID != other.lekarID) {
            return false;
        }
        return true;
    }

    
    
    
}
