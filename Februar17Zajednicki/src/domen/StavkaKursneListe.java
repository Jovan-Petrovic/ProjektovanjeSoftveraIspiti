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
public class StavkaKursneListe implements Serializable{
    private int rb;
    private Zemlja zemlja;
    private int vaziZa;
    private double kupovni;
    private double srednji;
    private double prodajni;

    public StavkaKursneListe() {
    }

    public StavkaKursneListe(int rb, Zemlja zemlja, int vaziZa, double kupovni, double srednji, double prodajni) {
        this.rb = rb;
        this.zemlja = zemlja;
        this.vaziZa = vaziZa;
        this.kupovni = kupovni;
        this.srednji = srednji;
        this.prodajni = prodajni;
    }

    public double getProdajni() {
        return prodajni;
    }

    public void setProdajni(double prodajni) {
        this.prodajni = prodajni;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Zemlja getZemlja() {
        return zemlja;
    }

    public void setZemlja(Zemlja zemlja) {
        this.zemlja = zemlja;
    }

    public int getVaziZa() {
        return vaziZa;
    }

    public void setVaziZa(int vaziZa) {
        this.vaziZa = vaziZa;
    }

    public double getKupovni() {
        return kupovni;
    }

    public void setKupovni(double kupovni) {
        this.kupovni = kupovni;
    }

    public double getSrednji() {
        return srednji;
    }

    public void setSrednji(double srednji) {
        this.srednji = srednji;
    }
    
    
}
