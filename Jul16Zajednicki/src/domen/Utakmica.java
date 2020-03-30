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
public class Utakmica implements Serializable {
    private int utakmicaID;
    private String grupa;
    private Reprezentacija domacin;
    private Reprezentacija gost;
    private int brojGlovaDomacina;
    private int brojGolovaGost;
    private String status; 

    public Utakmica() {
    }

    public Utakmica(int utakmicaID, String grupa, Reprezentacija domacin, Reprezentacija gost, int brojGlovaDomacina, int brojGolovaGost, String status) {
        this.utakmicaID = utakmicaID;
        this.grupa = grupa;
        this.domacin = domacin;
        this.gost = gost;
        this.brojGlovaDomacina = brojGlovaDomacina;
        this.brojGolovaGost = brojGolovaGost;
        this.status = status;
    }

    

    public int getBrojGolovaGost() {
        return brojGolovaGost;
    }

    public void setBrojGolovaGost(int brojGolovaGost) {
        this.brojGolovaGost = brojGolovaGost;
    }

    public int getUtakmicaID() {
        return utakmicaID;
    }

    public void setUtakmicaID(int utakmicaID) {
        this.utakmicaID = utakmicaID;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public Reprezentacija getDomacin() {
        return domacin;
    }

    public void setDomacin(Reprezentacija domacin) {
        this.domacin = domacin;
    }

    public Reprezentacija getGost() {
        return gost;
    }

    public void setGost(Reprezentacija gost) {
        this.gost = gost;
    }

    public int getBrojGlovaDomacina() {
        return brojGlovaDomacina;
    }

    public void setBrojGlovaDomacina(int brojGlovaDomacina) {
        this.brojGlovaDomacina = brojGlovaDomacina;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utakmica other = (Utakmica) obj;
        if (this.utakmicaID != other.utakmicaID) {
            return false;
        }
        return true;
    }
    
    
}
