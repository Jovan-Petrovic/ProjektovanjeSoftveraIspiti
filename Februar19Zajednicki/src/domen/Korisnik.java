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
public class Korisnik implements Serializable{
    private int sifra;
    private String imePrezime;
    private String korisnickoIme;
    private String lozinka;

    public Korisnik() {
    }

    public Korisnik(int sifra, String imePrezime, String korisnickoIme, String lozinka) {
        this.sifra = sifra;
        this.imePrezime = imePrezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    @Override
    public String toString() {
        return imePrezime;
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
        final Korisnik other = (Korisnik) obj;
        if (this.sifra != other.sifra) {
            return false;
        }
        return true;
    }
    
    
}
