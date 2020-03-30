/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import forme.GlavnaForma;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KORISNIK
 */
public class OsveziNit extends Thread {
    GlavnaForma gf;

    public OsveziNit(GlavnaForma gf) {
        this.gf = gf;
    }

    @Override
    public void run() {
        while(true) {
            gf.srediFormu();
            System.out.println("Osvezio");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OsveziNit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
