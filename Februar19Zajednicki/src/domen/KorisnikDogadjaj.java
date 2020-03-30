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
public class KorisnikDogadjaj implements Serializable{
    private Korisnik korisnik;
    private Dogadjaj dogadjaj;

    public KorisnikDogadjaj() {
    }

    public KorisnikDogadjaj(Korisnik korisnik, Dogadjaj dogadjaj) {
        this.korisnik = korisnik;
        this.dogadjaj = dogadjaj;
    }

    public Dogadjaj getDogadjaj() {
        return dogadjaj;
    }

    public void setDogadjaj(Dogadjaj dogadjaj) {
        this.dogadjaj = dogadjaj;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
    
    
}
