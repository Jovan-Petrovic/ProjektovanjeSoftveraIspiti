/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.KursnaLista;
import domen.StavkaKursneListe;
import domen.Zemlja;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Bron Zilar
 */
public class Kontroler {
    private static Kontroler instanca;
    DBBroker db;

    private Kontroler() {
        db = new DBBroker();
    }

    public static Kontroler getInstanca() {
        if(instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public ArrayList<Zemlja> vratiZemlje() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Zemlja> zemlje = db.vratiZemlje();
        db.zatvoriKonekciju();
        return zemlje;
    }

    public ArrayList<Date> vratiDatume() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Date> datumi = db.vratiDatume();
        db.zatvoriKonekciju();
        return datumi;
    }

    public ServerskiOdgovor sacuvaj(KursnaLista kl) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDrajver();
        db.otvoriKonekciju();
        int id=0;
        try {
            id = db.sacuvajKl(kl);
            for (StavkaKursneListe s : kl.getListaStavki()) {
                db.sacuvajStavku(s,id);
            }
            db.commit();
            so.setOdgovor(true);
            so.setPoruka("Uspesno sacuvano");
        } catch (SQLException ex) {
            db.rollback();
            so.setOdgovor(false);
            so.setPoruka("Neuspesno sacuvano");
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.zatvoriKonekciju();
        return so;
    }

    public ArrayList<StavkaKursneListe> vratiStavkeServer(String where) {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<StavkaKursneListe> stavke = db.vratiStavkeServer(where);
        db.zatvoriKonekciju();
        return stavke;
    }

    
    
    
}
