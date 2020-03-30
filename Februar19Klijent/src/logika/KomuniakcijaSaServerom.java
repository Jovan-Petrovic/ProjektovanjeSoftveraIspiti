/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import forme.KlijentskaForma;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Bron Zilar
 */
public class KomuniakcijaSaServerom {
    private static KomuniakcijaSaServerom instanca;
    Socket s;

    private KomuniakcijaSaServerom() {
        try {
            s = new Socket("localhost", 9000);
        } catch (IOException ex) {
            Logger.getLogger(KomuniakcijaSaServerom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static KomuniakcijaSaServerom getInstanca() {
        if(instanca == null) {
            instanca = new KomuniakcijaSaServerom();
        }
        return instanca;
    }
    
    public ServerskiOdgovor primiOdgovor() {
        ServerskiOdgovor so = new ServerskiOdgovor();
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            so = (ServerskiOdgovor) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(KomuniakcijaSaServerom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KomuniakcijaSaServerom.class.getName()).log(Level.SEVERE, null, ex);
        }
        return so;
    }
    
    public void posaljiZahtev(KlijentskiZahtev kz) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(kz);
        } catch (IOException ex) {
            Logger.getLogger(KomuniakcijaSaServerom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
