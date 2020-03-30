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
public class VrstaPoreza implements Serializable{
    private int vrstaID;
    private String naziv;
    private double procenatPoreza;

    public VrstaPoreza() {
    }

    public VrstaPoreza(int vrstaID, String naziv, double procenatPoreza) {
        this.vrstaID = vrstaID;
        this.naziv = naziv;
        this.procenatPoreza = procenatPoreza;
    }

    public double getProcenatPoreza() {
        return procenatPoreza;
    }

    public void setProcenatPoreza(double procenatPoreza) {
        this.procenatPoreza = procenatPoreza;
    }

    public int getVrstaID() {
        return vrstaID;
    }

    public void setVrstaID(int vrstaID) {
        this.vrstaID = vrstaID;
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
