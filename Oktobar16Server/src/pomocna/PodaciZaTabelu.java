/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomocna;

/**
 *
 * @author KORISNIK
 */
public class PodaciZaTabelu {
    private String nazivSP;
    private int skolskaGodina;
    private int brojKandidata;
    private double prihod;

    public PodaciZaTabelu() {
    }

    public PodaciZaTabelu(String nazivSP, int skolskaGodina, int brojKandidata, double prihod) {
        this.nazivSP = nazivSP;
        this.skolskaGodina = skolskaGodina;
        this.brojKandidata = brojKandidata;
        this.prihod = prihod;
    }

    public double getPrihod() {
        return prihod;
    }

    public void setPrihod(double prihod) {
        this.prihod = prihod;
    }

    public String getNazivSP() {
        return nazivSP;
    }

    public void setNazivSP(String nazivSP) {
        this.nazivSP = nazivSP;
    }

    public int getSkolskaGodina() {
        return skolskaGodina;
    }

    public void setSkolskaGodina(int skolskaGodina) {
        this.skolskaGodina = skolskaGodina;
    }

    public int getBrojKandidata() {
        return brojKandidata;
    }

    public void setBrojKandidata(int brojKandidata) {
        this.brojKandidata = brojKandidata;
    }
    
    
}
