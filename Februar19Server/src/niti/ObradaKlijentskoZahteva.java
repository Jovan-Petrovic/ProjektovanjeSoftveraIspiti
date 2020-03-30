/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import com.sun.corba.se.impl.io.IIOPOutputStream;
import domen.Dogadjaj;
import domen.Korisnik;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Operacije;
import logika.Kontroler;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Bron Zilar
 */
public class ObradaKlijentskoZahteva extends Thread{
    Socket s;

    public ObradaKlijentskoZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while(true) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            
            switch(kz.getOperacija()) {
                case Operacije.ULOGUJ_SE:
                    Korisnik k = (Korisnik) kz.getParametar();
                    so = Kontroler.getInstanca().ulogujSe(k);
                    break;
                case Operacije.SACUVAJ_DOGADJAJ:
                    Dogadjaj d = (Dogadjaj) kz.getParametar();
                    so = Kontroler.getInstanca().sacuvajDogadjaj(d);
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
            Logger.getLogger(ObradaKlijentskoZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskoZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kz;
    }
    
    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskoZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
