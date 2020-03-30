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
public class Kompanija implements Serializable {
    private int kompanijaID;
    private String naziv;
    private String oplastPoslovanja;
    private boolean ipo;
    private String tip;
    double ukupanIznos;

    public Kompanija() {
    }

    public Kompanija(int kompanijaID, String naziv, String oplastPoslovanja, boolean ipo, String tip, double ukupanIznos) {
        this.kompanijaID = kompanijaID;
        this.naziv = naziv;
        this.oplastPoslovanja = oplastPoslovanja;
        this.ipo = ipo;
        this.tip = tip;
        this.ukupanIznos = ukupanIznos;
    }

    

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getKompanijaID() {
        return kompanijaID;
    }

    public void setKompanijaID(int kompanijaID) {
        this.kompanijaID = kompanijaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOplastPoslovanja() {
        return oplastPoslovanja;
    }

    public void setOplastPoslovanja(String oplastPoslovanja) {
        this.oplastPoslovanja = oplastPoslovanja;
    }

    public boolean isIpo() {
        return ipo;
    }

    public void setIpo(boolean ipo) {
        this.ipo = ipo;
    }

    @Override
    public String toString() {
        return naziv;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }
    
    
    
}
