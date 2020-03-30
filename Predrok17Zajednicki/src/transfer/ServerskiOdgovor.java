/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author KORISNIK
 */
public class ServerskiOdgovor implements Serializable{
    private String poruka;
    private Object odgovori;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(String poruka, Object odgovori) {
        this.poruka = poruka;
        this.odgovori = odgovori;
    }

    public Object getOdgovori() {
        return odgovori;
    }

    public void setOdgovori(Object odgovori) {
        this.odgovori = odgovori;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }
    
    
}
