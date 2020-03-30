/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author KORISNIK
 */
public class Investicija implements Serializable {
    private int investicijaID;
    private Investitior investitior;
    private Kompanija kompanija;
    private Date datum;
    private double iznos;

    public Investicija() {
    }

    public Investicija(int investicijaID, Investitior investitior, Kompanija kompanija, Date datum, double iznos) {
        this.investicijaID = investicijaID;
        this.investitior = investitior;
        this.kompanija = kompanija;
        this.datum = datum;
        this.iznos = iznos;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public int getInvesticijaID() {
        return investicijaID;
    }

    public void setInvesticijaID(int investicijaID) {
        this.investicijaID = investicijaID;
    }

    public Investitior getInvestitior() {
        return investitior;
    }

    public void setInvestitior(Investitior investitior) {
        this.investitior = investitior;
    }

    public Kompanija getKompanija() {
        return kompanija;
    }

    public void setKompanija(Kompanija kompanija) {
        this.kompanija = kompanija;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
    
    
}
