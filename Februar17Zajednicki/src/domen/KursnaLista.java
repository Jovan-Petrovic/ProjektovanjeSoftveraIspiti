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
 * @author Bron Zilar
 */
public class KursnaLista implements Serializable {
    private int kursnaListaID;
    private Date dan;
    private String izvor;
    private ArrayList<StavkaKursneListe> listaStavki;

    public KursnaLista() {
        listaStavki = new ArrayList<>();
    }

    public KursnaLista(int kursnaListaID, Date dan, String izvor, ArrayList<StavkaKursneListe> listaStavki) {
        this.kursnaListaID = kursnaListaID;
        this.dan = dan;
        this.izvor = izvor;
        this.listaStavki = listaStavki;
    }

    public ArrayList<StavkaKursneListe> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(ArrayList<StavkaKursneListe> listaStavki) {
        this.listaStavki = listaStavki;
    }

    public int getKursnaListaID() {
        return kursnaListaID;
    }

    public void setKursnaListaID(int kursnaListaID) {
        this.kursnaListaID = kursnaListaID;
    }

    public Date getDan() {
        return dan;
    }

    public void setDan(Date dan) {
        this.dan = dan;
    }

    public String getIzvor() {
        return izvor;
    }

    public void setIzvor(String izvor) {
        this.izvor = izvor;
    }
    
    
}
