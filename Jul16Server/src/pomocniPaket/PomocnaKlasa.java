/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomocniPaket;

/**
 *
 * @author Bron Zilar
 */
public class PomocnaKlasa {
    private String nazivReprezentacije;
    private int brojDatihGolova;
    private int brojPrimljenihGolova;
    private int golRazlika;

    public PomocnaKlasa() {
    }

    public PomocnaKlasa(String nazivReprezentacije, int brojDatihGolova, int brojPrimljenihGolova, int golRazlika) {
        this.nazivReprezentacije = nazivReprezentacije;
        this.brojDatihGolova = brojDatihGolova;
        this.brojPrimljenihGolova = brojPrimljenihGolova;
        this.golRazlika = golRazlika;
    }

    public int getGolRazlika() {
        return golRazlika;
    }

    public void setGolRazlika(int golRazlika) {
        this.golRazlika = golRazlika;
    }

    public String getNazivReprezentacije() {
        return nazivReprezentacije;
    }

    public void setNazivReprezentacije(String nazivReprezentacije) {
        this.nazivReprezentacije = nazivReprezentacije;
    }

    public int getBrojDatihGolova() {
        return brojDatihGolova;
    }

    public void setBrojDatihGolova(int brojDatihGolova) {
        this.brojDatihGolova = brojDatihGolova;
    }

    public int getBrojPrimljenihGolova() {
        return brojPrimljenihGolova;
    }

    public void setBrojPrimljenihGolova(int brojPrimljenihGolova) {
        this.brojPrimljenihGolova = brojPrimljenihGolova;
    }
    
    
}
