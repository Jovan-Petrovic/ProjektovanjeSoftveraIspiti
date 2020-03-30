/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomocne;

/**
 *
 * @author Bron Zilar
 */
public class PomocnaKlasa {
    private int obveznikID;
    private String ime;
    private int godina;
    private double dug;

    public PomocnaKlasa() {
    }

    public PomocnaKlasa(int obveznikID, String ime, int godina, double dug) {
        this.obveznikID = obveznikID;
        this.ime = ime;
        this.godina = godina;
        this.dug = dug;
    }

    public double getDug() {
        return dug;
    }

    public void setDug(double dug) {
        this.dug = dug;
    }

    public int getObveznikID() {
        return obveznikID;
    }

    public void setObveznikID(int obveznikID) {
        this.obveznikID = obveznikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }
    
    
}
