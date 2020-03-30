/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 *
 * @author Bron Zilar
 */
public class Dogadjaj implements Serializable{
    private int id;
    private String naziv;
    private Timestamp datumVremePocetka;
    private Timestamp datumVremeKraja;
    private String tip;
    private String nazivLokacije;

    public Dogadjaj() {
    }

    public Dogadjaj(int id, String naziv, Timestamp datumVremePocetka, Timestamp datumVremeKraja, String tip, String nazivLokacije) {
        this.id = id;
        this.naziv = naziv;
        this.datumVremePocetka = datumVremePocetka;
        this.datumVremeKraja = datumVremeKraja;
        this.tip = tip;
        this.nazivLokacije = nazivLokacije;
    }

    public String getNazivLokacije() {
        return nazivLokacije;
    }

    public void setNazivLokacije(String nazivLokacije) {
        this.nazivLokacije = nazivLokacije;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Timestamp getDatumVremePocetka() {
        return datumVremePocetka;
    }

    public void setDatumVremePocetka(Timestamp datumVremePocetka) {
        this.datumVremePocetka = datumVremePocetka;
    }

    public Timestamp getDatumVremeKraja() {
        return datumVremeKraja;
    }

    public void setDatumVremeKraja(Timestamp datumVremeKraja) {
        this.datumVremeKraja = datumVremeKraja;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    
}
