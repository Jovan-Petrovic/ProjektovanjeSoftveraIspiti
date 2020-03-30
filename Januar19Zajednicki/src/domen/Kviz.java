/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;



/**
 *
 * @author Bron Zilar
 */
public class Kviz implements Serializable{
    private int kvizID;
    private String naziv;
    private Timestamp datumVremePocetka;
    private Timestamp datumVremeKraja;
    private String pobednik;

    public Kviz() {
    }

    public Kviz(int kvizID, String naziv, Timestamp datumVremePocetka, Timestamp datumVremeKraja, String pobednik) {
        this.kvizID = kvizID;
        this.naziv = naziv;
        this.datumVremePocetka = datumVremePocetka;
        this.datumVremeKraja = datumVremeKraja;
        this.pobednik = pobednik;
    }

    

    public String getPobednik() {
        return pobednik;
    }

    public void setPobednik(String pobednik) {
        this.pobednik = pobednik;
    }

    public int getKvizID() {
        return kvizID;
    }

    public void setKvizID(int kvizID) {
        this.kvizID = kvizID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    

    @Override
    public String toString() {
        return naziv;
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
    
    
}
