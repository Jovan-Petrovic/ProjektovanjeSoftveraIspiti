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
public class OsiguranoLice implements Serializable {
    private int osiguranoLiceID;
    private int lbo;
    private String ime;
    private String prezime;
    private String osnovOsiguranja;

    public OsiguranoLice() {
    }

    public OsiguranoLice(int osiguranoLiceID, int lbo, String ime, String prezime, String osnovOsiguranja) {
        this.osiguranoLiceID = osiguranoLiceID;
        this.lbo = lbo;
        this.ime = ime;
        this.prezime = prezime;
        this.osnovOsiguranja = osnovOsiguranja;
    }

    public String getOsnovOsiguranja() {
        return osnovOsiguranja;
    }

    public void setOsnovOsiguranja(String osnovOsiguranja) {
        this.osnovOsiguranja = osnovOsiguranja;
    }

    public int getOsiguranoLiceID() {
        return osiguranoLiceID;
    }

    public void setOsiguranoLiceID(int osiguranoLiceID) {
        this.osiguranoLiceID = osiguranoLiceID;
    }

    public int getLbo() {
        return lbo;
    }

    public void setLbo(int lbo) {
        this.lbo = lbo;
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
    
    
}
