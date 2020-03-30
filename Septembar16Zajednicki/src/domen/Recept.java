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
public class Recept implements Serializable {
    private int receptID;
    private Date datum;
    private int kolicina;
    private double ukupanIznos;
    private Lekar lekar;
    private OsiguranoLice osiguranoLice;
    private Lek lek;

    public Recept() {
    }

    public Recept(int receptID, Date datum, int kolicina, double ukupanIznos, Lekar lekar, OsiguranoLice osiguranoLice, Lek lek) {
        this.receptID = receptID;
        this.datum = datum;
        this.kolicina = kolicina;
        this.ukupanIznos = ukupanIznos;
        this.lekar = lekar;
        this.osiguranoLice = osiguranoLice;
        this.lek = lek;
    }

    public Lek getLek() {
        return lek;
    }

    public void setLek(Lek lek) {
        this.lek = lek;
    }

    public int getReceptID() {
        return receptID;
    }

    public void setReceptID(int receptID) {
        this.receptID = receptID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public OsiguranoLice getOsiguranoLice() {
        return osiguranoLice;
    }

    public void setOsiguranoLice(OsiguranoLice osiguranoLice) {
        this.osiguranoLice = osiguranoLice;
    }
    
    

}
