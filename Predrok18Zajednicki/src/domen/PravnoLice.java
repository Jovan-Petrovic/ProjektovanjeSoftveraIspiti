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
public class PravnoLice extends PoreskiObveznik implements Serializable{
    private String pib;
    private String maticniBroj;
    private String naziv;

    public PravnoLice() {
    }

    public PravnoLice(int obveznikID, String pib, String maticniBroj, String naziv) {
        super(obveznikID);
        this.pib = pib;
        this.maticniBroj = maticniBroj;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getMaticniBroj() {
        return maticniBroj;
    }

    public void setMaticniBroj(String maticniBroj) {
        this.maticniBroj = maticniBroj;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    
}
