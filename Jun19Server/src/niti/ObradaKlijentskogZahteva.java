/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Linija;
import domen.Stanica;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
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
                case Operacije.VRATI_STANICE:
                    so = Kontroler.getInstanca().vratiStanice();
                    ArrayList<Stanica> stanice = (ArrayList<Stanica>) so.getOdgovor();
                    break;
                case Operacije.VRATI_LINIJE:
                    so = Kontroler.getInstanca().vratiLinije();
                    ArrayList<Linija> linije = (ArrayList<Linija>) so.getOdgovor();
                    break;
                case Operacije.VRATI_MEDJUSTANICE_ZA_LINIJU:
                    int id = (int) kz.getParametar();
                    so = Kontroler.getInstanca().vratiMedjustaniceZaLiniju(id);
                    ArrayList<Integer> lista = (ArrayList<Integer>) so.getOdgovor();
                    break;
                case Operacije.SACUVAJ:
                    Map<String,Object> mapa = (Map<String,Object>) kz.getParametar();
                    so = Kontroler.getInstanca().sacuvaj(mapa);
                    break;
            }
            
            posaljiOdgovor(so);
        }
    }
    
    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex);
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
}
