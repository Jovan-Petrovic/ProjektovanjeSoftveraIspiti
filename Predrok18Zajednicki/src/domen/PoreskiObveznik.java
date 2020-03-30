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
public class PoreskiObveznik implements Serializable{
    private int obveznikID;

    public PoreskiObveznik() {
    }

    public PoreskiObveznik(int obveznikID) {
        this.obveznikID = obveznikID;
    }

    public int getObveznikID() {
        return obveznikID;
    }

    public void setObveznikID(int obveznikID) {
        this.obveznikID = obveznikID;
    }
    
    
}
