/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Bron Zilar
 */
public class PoreskaPrijava implements Serializable{
    private int prijavaID;
    private int godina;
    private PoreskiObveznik obveznik;
    private PoreskiInspektor inspektor;
    private ArrayList<StavkaPoreskePrijave> stavke;

    public PoreskaPrijava() {
    }

    public PoreskaPrijava(int prijavaID, int godina, PoreskiObveznik obveznik, PoreskiInspektor inspektor, ArrayList<StavkaPoreskePrijave> stavke) {
        this.prijavaID = prijavaID;
        this.godina = godina;
        this.obveznik = obveznik;
        this.inspektor = inspektor;
        this.stavke = stavke;
    }

    

    public PoreskiInspektor getInspektor() {
        return inspektor;
    }

    public void setInspektor(PoreskiInspektor inspektor) {
        this.inspektor = inspektor;
    }

    public int getPrijavaID() {
        return prijavaID;
    }

    public void setPrijavaID(int prijavaID) {
        this.prijavaID = prijavaID;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public PoreskiObveznik getObveznik() {
        return obveznik;
    }

    public void setObveznik(PoreskiObveznik obveznik) {
        this.obveznik = obveznik;
    }

    public ArrayList<StavkaPoreskePrijave> getStavke() {
        return stavke;
    }

    public void setStavke(ArrayList<StavkaPoreskePrijave> stavke) {
        this.stavke = stavke;
    }
    
    
}
