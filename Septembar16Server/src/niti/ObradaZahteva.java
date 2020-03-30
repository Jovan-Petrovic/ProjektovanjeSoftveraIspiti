/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Lek;
import domen.Lekar;
import domen.OsiguranoLice;
import domen.Recept;
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
                case Operacije.VRATI_LEKARE:
                    ArrayList<Lekar> lista = Kontroler.getInstanca().vratiLekare();
                    so.setOdgovor(lista);
                    break;
                case Operacije.VRATI_OSIGURANA_LICA:
                    ArrayList<OsiguranoLice> lista1 = Kontroler.getInstanca().vratiOsiguranaLica();
                    so.setOdgovor(lista1);
                    break;
                case Operacije.VRATI_LEKOVE:
                    ArrayList<Lek> lista2 = Kontroler.getInstanca().vratiLekove();
                    so.setOdgovor(lista2);
                    break;
                case Operacije.SACUVAJ:
                    ArrayList<Recept> listaRecepata = (ArrayList<Recept>) kz.getParametar();
                    so = Kontroler.getInstanca().sacuvajRecepte(listaRecepata);
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
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kz;
    }
   
    private void posaljiOdgovor(ServerskiOdgovor so) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(s.getOutputStream());
             oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
