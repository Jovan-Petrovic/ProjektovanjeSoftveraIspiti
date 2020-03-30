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
public class FizickoLice extends PoreskiObveznik implements Serializable {
    private String jmbg;
    private String imePrezime;

    public FizickoLice() {
    }

    public FizickoLice(int obveznikID, String jmbg, String imePrezime) {
        super(obveznikID);
        this.jmbg = jmbg;
        this.imePrezime = imePrezime;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    @Override
    public String toString() {
        return imePrezime;
    }

    
    
    
}
