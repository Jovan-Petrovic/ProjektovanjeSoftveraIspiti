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
public class Zemlja implements Serializable{
    private int zemljaID;
    private String naziv;
    private String valuta;

    public Zemlja() {
    }

    public Zemlja(int zemljaID, String naziv, String valuta) {
        this.zemljaID = zemljaID;
        this.naziv = naziv;
        this.valuta = valuta;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public int getZemljaID() {
        return zemljaID;
    }

    public void setZemljaID(int zemljaID) {
        this.zemljaID = zemljaID;
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
    
    
}
