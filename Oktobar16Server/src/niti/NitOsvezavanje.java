/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import forme.ServerskaForma;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KORISNIK
 */
public class NitOsvezavanje extends Thread{
    ServerskaForma sf;

    public NitOsvezavanje(ServerskaForma sf) {
        this.sf = sf;
    }

    @Override
    public void run() {
        while(true) {
            sf.srediFormu();
            System.out.println("Osvezio");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(NitOsvezavanje.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
