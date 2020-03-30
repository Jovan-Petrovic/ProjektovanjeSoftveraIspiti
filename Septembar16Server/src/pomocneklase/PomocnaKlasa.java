/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomocneklase;

/**
 *
 * @author KORISNIK
 */
public class PomocnaKlasa {
    private String nazivLeka;
    private double cena;
    private int brojKomada;
    private double prihodi;
    private double rashodi;

    public PomocnaKlasa() {
    }

    public PomocnaKlasa(String nazivLeka, double cena, int brojKomada, double prihodi, double rashodi) {
        this.nazivLeka = nazivLeka;
        this.cena = cena;
        this.brojKomada = brojKomada;
        this.prihodi = prihodi;
        this.rashodi = rashodi;
    }

    public double getRashodi() {
        return rashodi;
    }

    public void setRashodi(double rashodi) {
        this.rashodi = rashodi;
    }

    public String getNazivLeka() {
        return nazivLeka;
    }

    public void setNazivLeka(String nazivLeka) {
        this.nazivLeka = nazivLeka;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getBrojKomada() {
        return brojKomada;
    }

    public void setBrojKomada(int brojKomada) {
        this.brojKomada = brojKomada;
    }

    public double getPrihodi() {
        return prihodi;
    }

    public void setPrihodi(double prihodi) {
        this.prihodi = prihodi;
    }
    
    
}
