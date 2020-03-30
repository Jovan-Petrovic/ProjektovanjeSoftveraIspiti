/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomocne;

import domen.Linija;

/**
 *
 * @author Bron Zilar
 */
public class PomocnaKlasa {
    private Linija linija;
    private String medjustanice;

    public PomocnaKlasa() {
    }

    public PomocnaKlasa(Linija linija, String medjustanice) {
        this.linija = linija;
        this.medjustanice = medjustanice;
    }

    public String getMedjustanice() {
        return medjustanice;
    }

    public void setMedjustanice(String medjustanice) {
        this.medjustanice = medjustanice;
    }

    public Linija getLinija() {
        return linija;
    }

    public void setLinija(Linija linija) {
        this.linija = linija;
    }
    
    
}
