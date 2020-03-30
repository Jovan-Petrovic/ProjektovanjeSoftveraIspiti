/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import logika.Kontroler;

/**
 *
 * @author Bron Zilar
 */
public class PokretanjeServera extends Thread {

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(9000);
            System.out.println("Server pokrenut");
            Socket[] niz = new Socket[10];
//          while(true) {
//                Socket s = ss.accept();
//                System.out.println("Povezao se klijent broj: " + Kontroler.getInstanca().getBrojKlijenata());
//                if(Kontroler.getInstanca().getBrojKlijenata()>3) {
//                    break;
//                }
//                ObradaKlijentskogZahteva okz = new ObradaKlijentskogZahteva(s);
//                okz.start();
//            }
            for(int i = 0; i < 3; i++) {
                niz[i] = ss.accept();
                System.out.println("Povezao se klijent broj: " + (i+1));
            }
            ObradaKlijentskogZahteva okz = new ObradaKlijentskogZahteva(niz[0]);
            okz.start();
            ObradaKlijentskogZahteva okz1 = new ObradaKlijentskogZahteva(niz[1]);
            okz1.start();
            ObradaKlijentskogZahteva okz2 = new ObradaKlijentskogZahteva(niz[2]);
            okz2.start();
//            int i = 0; 
//            int br = 0;
//            while(true) {
//                niz[i++] = ss.accept();
//                System.out.println("Povezao se klijent broj: " + i);
//                br = Kontroler.getInstanca().getBrojKlijenata();
//                if(br>3) {
//                    break;
//                }
//            }
//            ObradaKlijentskogZahteva okz = new ObradaKlijentskogZahteva(niz[0]);
//            okz.start();
//            ObradaKlijentskogZahteva okz1 = new ObradaKlijentskogZahteva(niz[1]);
//            okz1.start();
//            ObradaKlijentskogZahteva okz2 = new ObradaKlijentskogZahteva(niz[2]);
//            okz2.start();
        } catch (IOException ex) {
            Logger.getLogger(PokretanjeServera.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
