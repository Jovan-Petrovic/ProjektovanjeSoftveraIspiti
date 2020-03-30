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
public class Prijava implements Serializable {
    private int prijavaID;
    private int godinaUpisa;
    private int godinaDIplomiranja;
    private double prosecnaOcena;
    private Date datumPrijave;
    private boolean oslobodjenPrijemnog;
    private Fakultet fakultet;
    private StudijskiProgram studijskiProgram;
    private Kandidat kandidat;

    public Prijava() {
    }

    public Prijava(int prijavaID, int godinaUpisa, int godinaDIplomiranja, double prosecnaOcena, Date datumPrijave, boolean oslobodjenPrijemnog, Fakultet fakultet, StudijskiProgram studijskiProgram, Kandidat kandidat) {
        this.prijavaID = prijavaID;
        this.godinaUpisa = godinaUpisa;
        this.godinaDIplomiranja = godinaDIplomiranja;
        this.prosecnaOcena = prosecnaOcena;
        this.datumPrijave = datumPrijave;
        this.oslobodjenPrijemnog = oslobodjenPrijemnog;
        this.fakultet = fakultet;
        this.studijskiProgram = studijskiProgram;
        this.kandidat = kandidat;
    }

    public Kandidat getKandidat() {
        return kandidat;
    }

    public void setKandidat(Kandidat kandidat) {
        this.kandidat = kandidat;
    }

    public int getPrijavaID() {
        return prijavaID;
    }

    public void setPrijavaID(int prijavaID) {
        this.prijavaID = prijavaID;
    }

    public int getGodinaUpisa() {
        return godinaUpisa;
    }

    public void setGodinaUpisa(int godinaUpisa) {
        this.godinaUpisa = godinaUpisa;
    }

    public int getGodinaDIplomiranja() {
        return godinaDIplomiranja;
    }

    public void setGodinaDIplomiranja(int godinaDIplomiranja) {
        this.godinaDIplomiranja = godinaDIplomiranja;
    }

    public double getProsecnaOcena() {
        return prosecnaOcena;
    }

    public void setProsecnaOcena(double prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }

    public boolean isOslobodjenPrijemnog() {
        return oslobodjenPrijemnog;
    }

    public void setOslobodjenPrijemnog(boolean oslobodjenPrijemnog) {
        this.oslobodjenPrijemnog = oslobodjenPrijemnog;
    }

    public Fakultet getFakultet() {
        return fakultet;
    }

    public void setFakultet(Fakultet fakultet) {
        this.fakultet = fakultet;
    }

    public StudijskiProgram getStudijskiProgram() {
        return studijskiProgram;
    }

    public void setStudijskiProgram(StudijskiProgram studijskiProgram) {
        this.studijskiProgram = studijskiProgram;
    }

    public Date getDatumPrijave() {
        return datumPrijave;
    }

    public void setDatumPrijave(Date datumPrijave) {
        this.datumPrijave = datumPrijave;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
