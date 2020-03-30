/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import domen.Korisnik;
import domen.Rec;
import java.io.IOException;
import java.io.ObjectInputStream;
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
public class ObradaZahteva extends Thread {
    Socket s;

    public ObradaZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while(true) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            
            switch(kz.getOperacija()) {
                case Operacije.ULOGUJ_SE:
                    String korisnickoIme = (String) kz.getParametar();
                    Korisnik k = Kontroler.getInstanca().nadjiKorisnika(korisnickoIme);
                    so.setOdgovori(k);
                    posaljiOdgovor(so);
                    break;
                case Operacije.UBACI_REC:                    
                    Rec rec = (Rec) kz.getParametar();
                    ArrayList<Rec> listaReci = Kontroler.getInstanca().nadjiReci();
                    if(!listaReci.contains(rec)) {
                        so = Kontroler.getInstanca().unesiNovuRec(rec);
                    } else {
                        so = Kontroler.getInstanca().izmeniReci(rec,listaReci);
                    }
                    ArrayList<Socket> listaKl = Kontroler.getInstanca().getListaKlijenata();
                    for(Socket sok : listaKl) {
                        posaljiOdgovorSvima(so, sok);
                    }
                    break;
            }
            
        }
    }

    private KlijentskiZahtev primiZahtev() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            kz = (KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kz;
    }
    
    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void posaljiOdgovorSvima(ServerskiOdgovor so, Socket soket) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(soket.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
