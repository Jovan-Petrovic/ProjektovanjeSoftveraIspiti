/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import forme.ServerskaForma;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import logika.Kontroler;

/**
 *
 * @author KORISNIK
 */
public class PokreniServer extends Thread {
    ServerskaForma sf;

    public PokreniServer(ServerskaForma sf) {
        this.sf = sf;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(9100);
            sf.serverPokrenut();
            System.out.println("Server je pokrenut");
            NitZaZatvaranje nz = new NitZaZatvaranje(ss, this);
            nz.start();
            while(!isInterrupted()) {
                Socket s = ss.accept();
                Kontroler.getInstanca().getListaKlijenata().add(s);
                System.out.println("Klijent povezan");
                ObradaZahteva oz = new ObradaZahteva(s);
                oz.start();
            }
        } catch (IOException ex) {
            System.out.println("Zaustavljen server");
            sf.serverNijePokrenut();
        }
        
    }
    
    
}
