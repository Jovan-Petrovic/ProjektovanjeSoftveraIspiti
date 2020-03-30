/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author KORISNIK
 */
public class Rentiranje implements Serializable {
    private int rentiranjeID;
    private String klijent;
    private Date datumOd;
    private Date datumDo;
    private double ukupanIznos;
    private ArrayList<StavkaRentiranja> listaStavki;

    public Rentiranje() {
        listaStavki = new ArrayList<>();
    }

    public Rentiranje(int rentiranjeID, String klijent, Date datumOd, Date datumDo, double ukupanIznos, ArrayList<StavkaRentiranja> listaStavki) {
        this.rentiranjeID = rentiranjeID;
        this.klijent = klijent;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.ukupanIznos = ukupanIznos;
        this.listaStavki = listaStavki;
    }

    public ArrayList<StavkaRentiranja> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(ArrayList<StavkaRentiranja> listaStavki) {
        this.listaStavki = listaStavki;
    }

    public int getRentiranjeID() {
        return rentiranjeID;
    }

    public void setRentiranjeID(int rentiranjeID) {
        this.rentiranjeID = rentiranjeID;
    }

    public String getKlijent() {
        return klijent;
    }

    public void setKlijent(String klijent) {
        this.klijent = klijent;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }
    
    
}
