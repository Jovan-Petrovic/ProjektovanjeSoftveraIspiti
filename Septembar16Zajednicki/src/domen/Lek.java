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
public class Lek implements Serializable {
    private int lekID;
    private String naziv;
    private double cena;
    private String lista;

    public Lek() {
    }

    public Lek(int lekID, String naziv, double cena, String lista) {
        this.lekID = lekID;
        this.naziv = naziv;
        this.cena = cena;
        this.lista = lista;
    }

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }

    public int getLekID() {
        return lekID;
    }

    public void setLekID(int lekID) {
        this.lekID = lekID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    
}
