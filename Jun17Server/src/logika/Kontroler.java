/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.Oprema;
import domen.Proizvodjac;
import domen.Rentiranje;
import domen.StavkaRentiranja;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pomocneKlase.PomocnaKlasa;
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

    public ArrayList<Oprema> vratiOpreme() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Oprema> lista = db.vratiOpreme();
        db.zatvoriKonekciju();
        return lista;
    }

    public ServerskiOdgovor sacuvajRentiranje(Rentiranje r) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDrajver();
        db.otvoriKonekciju();
        int id;
        try {
            id = db.vratiID();
            r.setRentiranjeID(id);
            db.unesiRentiranje(r);
            for(StavkaRentiranja s : r.getListaStavki()) {
                db.unesiStavku(s,id);
            }
            db.commit();
            so.setOdgovor(true);
            so.setPoruka("Uspesno sacuvano sve");
        } catch (SQLException ex) {
            db.rollback();
            so.setOdgovor(false);
            so.setPoruka("Doslo je do greske prilikom cuvanja");
        }
        db.zatvoriKonekciju();
        return so;
    }

    public ArrayList<Proizvodjac> vratiproizvodjace() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Proizvodjac> lista = db.vratiProizvodjace();
        db.zatvoriKonekciju();
        return lista;
    }

    public ArrayList<PomocnaKlasa> vratiListuZaServer(Proizvodjac p) {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<PomocnaKlasa> lista = db.vratiListuZaServer(p);
        db.zatvoriKonekciju();
        return lista;
    }
    
    
}
