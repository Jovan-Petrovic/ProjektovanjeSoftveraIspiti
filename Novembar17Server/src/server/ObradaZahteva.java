/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import domen.Investicija;
import domen.Investitior;
import domen.Kompanija;
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
    Socket socket;

    public ObradaZahteva(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while(true) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();

            switch(kz.getOperacija()) {
                case Operacije.ULOGUJ_SE:
                    Investitior i = (Investitior) kz.getParametar();
                    so = Kontroler.getInstanca().nadjiInvestitora(i);
                    break;
                case Operacije.VRATI_STARTAPE:
                    ArrayList<Kompanija> listaKompanija = Kontroler.getInstanca().vratiKompanije();
                    so.setOdgovor(listaKompanija);
                    break;
                case Operacije.SACUVAJ:
                    Investicija inv = (Investicija) kz.getParametar();
                    so = Kontroler.getInstanca().sacuvajInvesticiju(inv);
                    break;
            }
            posaljiOdgovor(so);
        }
    }
    
    private KlijentskiZahtev primiZahtev() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
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
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
