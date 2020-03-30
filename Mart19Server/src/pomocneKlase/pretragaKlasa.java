/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomocneKlase;

/**
 *
 * @author Bron Zilar
 */
public class pretragaKlasa {
    private int brojpredmeta;
    private String imePrezime;

    public pretragaKlasa() {
    }

    public pretragaKlasa(int brojpredmeta, String imePrezime) {
        this.brojpredmeta = brojpredmeta;
        this.imePrezime = imePrezime;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public int getBrojpredmeta() {
        return brojpredmeta;
    }

    public void setBrojpredmeta(int brojpredmeta) {
        this.brojpredmeta = brojpredmeta;
    }
    
    
}
