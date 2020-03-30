/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author KORISNIK
 */
public class StudijskiProgram implements Serializable {
    private int studijskiProgramID;
    private String naziv;

    public StudijskiProgram() {
    }

    public StudijskiProgram(int studijskiProgramID, String naziv) {
        this.studijskiProgramID = studijskiProgramID;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getStudijskiProgramID() {
        return studijskiProgramID;
    }

    public void setStudijskiProgramID(int studijskiProgramID) {
        this.studijskiProgramID = studijskiProgramID;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    
}
