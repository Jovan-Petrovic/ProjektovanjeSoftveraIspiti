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

/**
 *
 * @author Bron Zilar
 */
public class PokretanjeServera extends Thread{

    ServerSocket ss;
    
    @Override
    public void run() {
        try {
            ss = new ServerSocket(9000);
            System.out.println("Server pokrenut");
            while (true) {                
                Socket s = ss.accept();
                System.out.println("Klijent se povezao");
                ObradaKlijentskogZahteva okz = new ObradaKlijentskogZahteva(s);
                okz.start();
            }
        } catch (IOException ex) {
//            Logger.getLogger(PokretanjeServera.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }

    public ServerSocket getSs() {
        return ss;
    }

    public void zaustaviServerskuNit() {
        try {
            ss.close();
            System.out.println("Server zaustavljen");
        } catch (IOException ex) {
            Logger.getLogger(PokretanjeServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
