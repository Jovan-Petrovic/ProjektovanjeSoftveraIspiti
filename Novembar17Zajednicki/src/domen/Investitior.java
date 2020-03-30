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
public class Investitior implements Serializable{
    private int investitorID;
    private String naziv;
    private String adresa;
    private String username;
    private String password;

    public Investitior() {
    }

    public Investitior(int investitorID, String naziv, String adresa, String username, String password) {
        this.investitorID = investitorID;
        this.naziv = naziv;
        this.adresa = adresa;
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getInvestitorID() {
        return investitorID;
    }

    public void setInvestitorID(int investitorID) {
        this.investitorID = investitorID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    
}
