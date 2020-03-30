/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Reprezentacija;
import domen.Utakmica;
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
 * @author Bron Zilar
 */
public class ObradaKlijentskoZahteva extends Thread {
    Socket s;

    public ObradaKlijentskoZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {            
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            
            switch(kz.getOperacija()) {
                case Operacije.VRATI_UTAKMICE:
                    ArrayList<Utakmica> utakmice = Kontroler.getInstanca().vratiUtakmice();
                    so.setOdgovor(utakmice);
                    break;
                case Operacije.VRATI_REPREZENTACIJE:
                    ArrayList<Reprezentacija> repke = Kontroler.getInstanca().vratiRepke();
                    so.setOdgovor(repke);
                    break;
                case Operacije.SACUVAJ_PROMENE:
                    ArrayList<Utakmica> lista = (ArrayList<Utakmica>) kz.getParametar();
                    so = Kontroler.getInstanca().sacuvajPromene(lista);
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
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskoZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
