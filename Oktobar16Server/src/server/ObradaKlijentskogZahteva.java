/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import domen.Fakultet;
import domen.Kandidat;
import domen.Prijava;
import domen.StudijskiProgram;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Operacije;
import logika.Kontroler;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author KORISNIK
 */
public class ObradaKlijentskogZahteva extends Thread {
    Socket s;

    public ObradaKlijentskogZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while(true) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();

            switch(kz.getOperacija()) {
                case Operacije.ULOGUJ_SE:
                    Kandidat k = (Kandidat) kz.getParametar();
                    Kandidat kan = Kontroler.getInstanca().nadjiKandidata(k);
                    so.setOdgovor(kan);
                    break;
                case Operacije.VRATI_FAKULTETE:
                    ArrayList<Fakultet> listaF = Kontroler.getInstanca().vratiFakultete();
                    so.setOdgovor(listaF);
                    break;
                case Operacije.VRATI_PROGRAME:
                    ArrayList<StudijskiProgram> listaSP = Kontroler.getInstanca().vratiPrograme();
                    so.setOdgovor(listaSP);
                    break;
                case Operacije.VRATI_PRIJAVE:
                    ArrayList<Prijava> listaPrijava = Kontroler.getInstanca().vratiPrijave();
                    so.setOdgovor(listaPrijava);
                    break;
                case Operacije.SACUVAJ:
                    Prijava p = (Prijava) kz.getParametar();
                    so = Kontroler.getInstanca().sacuvajPrijavu(p);
                    break;
            }
            posaljiOdgovor(so);
        }
    }
    
    
    
    private KlijentskiZahtev primiZahtev() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            kz = (KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kz;
    }
    
    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
