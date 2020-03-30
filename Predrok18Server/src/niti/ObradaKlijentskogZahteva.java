/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.PoreskaPrijava;
import domen.PoreskiInspektor;
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
public class ObradaKlijentskogZahteva extends Thread{
    Socket s;

    public ObradaKlijentskogZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {            
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            
            switch(kz.getOperacija()) {
                case Operacije.PRIJAVA:
                    PoreskiInspektor inspektor = (PoreskiInspektor) kz.getParametar();
                    so = Kontroler.getInstanca().prijava(inspektor);
                    break;
                case Operacije.VRATI_PORESKE_OBVEZNIKE:
                    so = Kontroler.getInstanca().vratiObveznike();
                    break;
                case Operacije.VRATI_VRSTE_POREZA:
                    so = Kontroler.getInstanca().vratiVrstePoreza();
                    break;
                case Operacije.VRATI_REDNI_BROJ:
                    so = Kontroler.getInstanca().vratiRedniBroj();
                    break;
                case Operacije.SACUVAJ_PORESKU_PRIJAVU:
                    PoreskaPrijava prijava = (PoreskaPrijava) kz.getParametar();
                    so = Kontroler.getInstanca().sacuvajPoreskuPrijavu(prijava);
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
