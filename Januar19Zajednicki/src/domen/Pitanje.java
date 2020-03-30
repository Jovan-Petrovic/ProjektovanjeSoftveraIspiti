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
public class Pitanje implements Serializable {
    private int pitanjeID;
    private String pitanje;
    private String tacanOdgovor;
    private int brojPoena;
    private Kviz kviz;

    public Pitanje() {
    }

    public Pitanje(int pitanjeID, String pitanje, String tacanOdgovor, int brojPoena, Kviz kviz) {
        this.pitanjeID = pitanjeID;
        this.pitanje = pitanje;
        this.tacanOdgovor = tacanOdgovor;
        this.brojPoena = brojPoena;
        this.kviz = kviz;
    }

    public Kviz getKviz() {
        return kviz;
    }

    public void setKviz(Kviz kviz) {
        this.kviz = kviz;
    }

    public int getPitanjeID() {
        return pitanjeID;
    }

    public void setPitanjeID(int pitanjeID) {
        this.pitanjeID = pitanjeID;
    }

    public String getPitanje() {
        return pitanje;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
    }

    public String getTacanOdgovor() {
        return tacanOdgovor;
    }

    public void setTacanOdgovor(String tacanOdgovor) {
        this.tacanOdgovor = tacanOdgovor;
    }

    public int getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(int brojPoena) {
        this.brojPoena = brojPoena;
    }

    @Override
    public String toString() {
        return pitanje;
    }
    
    
}
