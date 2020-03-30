/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Prevod;
import domen.Rec;
import domen.Recnik;
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
public class ObradaKorisnickogZahteva extends Thread{
    Socket s;

    public ObradaKorisnickogZahteva(Socket s) {
        this.s = s;
    }
    
    @Override
    public void run() {
        while(true) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            
            switch(kz.getOperacija()) {
                case Operacije.VRATI_RECNIKE:
                    ArrayList<Recnik> recnici = Kontroler.getInstanca().vratiRecnike();
                    so.setOdgovor(recnici);
                    break;
                case Operacije.SACUVAJ:
                    Rec r = (Rec) kz.getParametar();
                    so = Kontroler.getInstanca().sacuvaj(r);
                    break;
                case Operacije.SACUVAJ_VOL2:
                    ArrayList<Rec> reci = (ArrayList<Rec>) kz.getParametar();
                    so = Kontroler.getInstanca().sacuvajVol2(reci);
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
            Logger.getLogger(ObradaKorisnickogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKorisnickogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kz;
    }
    
    private void posaljiOdgovor(ServerskiOdgovor so) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(ObradaKorisnickogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
}
