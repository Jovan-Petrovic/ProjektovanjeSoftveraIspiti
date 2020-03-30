/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.Linija;
import domen.Stanica;
import java.util.ArrayList;
import java.util.Map;
import pomocne.PomocnaKlasa;
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
        if(instanca==null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public ServerskiOdgovor vratiStanice() {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Stanica> stanice = db.vratiStanice();
        so.setOdgovor(stanice);
        db.zatvoriKonekciju();
        return so;
    }

    public ServerskiOdgovor vratiLinije() {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Linija> linije = db.vratiLinije();
        so.setOdgovor(linije);
        db.zatvoriKonekciju();
        return so;
    }

    public ServerskiOdgovor vratiMedjustaniceZaLiniju(int id) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Integer> linije = db.vratiMedjustaniceZaLiniju(id);
        so.setOdgovor(linije);
        db.zatvoriKonekciju();
        return so;
    }

    public ServerskiOdgovor sacuvaj(Map<String, Object> mapa) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDrajver();
        db.otvoriKonekciju();
        boolean status = db.sacuvaj(mapa);
        if(status) {
            so.setPoruka("Sve je uspesno sacuvano");
            so.setOdgovor(true);
            db.commit();
        } else {
            so.setPoruka("doslo je do greske. Nije sacuvano.");
            so.setOdgovor(false);
            db.rollback();
        }
        return so;
    }

    public ArrayList<PomocnaKlasa> vratiZaServer(String where) {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<PomocnaKlasa> linije = db.vratiZaServer(where);
        db.zatvoriKonekciju();
        return linije;
    }

    
}
