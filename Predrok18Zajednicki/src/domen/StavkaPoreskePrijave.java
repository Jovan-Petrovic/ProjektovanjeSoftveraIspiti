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
 * @author Bron Zilar
 */
public class StavkaPoreskePrijave implements Serializable{
    private int rb;
    private Date datumPrometa;
    private double vrednost;
    private String napomena;
    private VrstaPoreza vrsta;

    public StavkaPoreskePrijave() {
    }

    public StavkaPoreskePrijave(int rb, Date datumPrometa, double vrednost, String napomena, VrstaPoreza vrsta) {
        this.rb = rb;
        this.datumPrometa = datumPrometa;
        this.vrednost = vrednost;
        this.napomena = napomena;
        this.vrsta = vrsta;
    }

    public VrstaPoreza getVrsta() {
        return vrsta;
    }

    public void setVrsta(VrstaPoreza vrsta) {
        this.vrsta = vrsta;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Date getDatumPrometa() {
        return datumPrometa;
    }

    public void setDatumPrometa(Date datumPrometa) {
        this.datumPrometa = datumPrometa;
    }

    public double getVrednost() {
        return vrednost;
    }

    public void setVrednost(double vrednost) {
        this.vrednost = vrednost;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }
    
    
}
