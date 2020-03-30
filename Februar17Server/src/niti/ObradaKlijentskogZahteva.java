/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import com.sun.corba.se.impl.io.IIOPOutputStream;
import domen.KursnaLista;
import domen.Zemlja;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
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
    boolean kraj;

    public ObradaKlijentskogZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while(!kraj) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            
            switch(kz.getOperacija()) {
                case Operacije.VRATI_ZEMLJE:
                    ArrayList<Zemlja> zemlje = Kontroler.getInstanca().vratiZemlje();
                    so.setOdgovor(zemlje);
                    break;
                case Operacije.VRATI_DATUME_ZA_PROVERU:
                    ArrayList<Date> datumi = Kontroler.getInstanca().vratiDatume();
                    so.setOdgovor(datumi);
                    break;
                case Operacije.SACUVAJ:
                    KursnaLista kl = (KursnaLista) kz.getParametar();
                    so = Kontroler.getInstanca().sacuvaj(kl);
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
