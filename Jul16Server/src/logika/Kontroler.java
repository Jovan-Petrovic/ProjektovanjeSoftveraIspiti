/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.Reprezentacija;
import domen.Utakmica;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public ArrayList<Utakmica> vratiUtakmice() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Utakmica> lista = db.vratiUtakmice();
        db.zatvoriKonekciju();
        return lista;
    }

    public ArrayList<Reprezentacija> vratiRepke() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Reprezentacija> lista = db.vratiRepke();
        db.zatvoriKonekciju();
        return lista;
    }

    public ServerskiOdgovor sacuvajPromene(ArrayList<Utakmica> lista) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDrajver();
        db.otvoriKonekciju();
        try {
            for (Utakmica utakmica : lista) {
                if(utakmica.getStatus().equals("izmena")) {
                    db.izmeni(utakmica);
                }
                if(utakmica.getStatus().equals("brisanje")) {
                        db.obrisi(utakmica);
                }
            }
            db.commit();
            so.setOdgovor(true);
            so.setPoruka("Uspesno sacuvano sve");
        } catch (SQLException ex) {
            db.rollback();
            so.setOdgovor(false);
            so.setPoruka("Nije uspesno sacuvano sve");
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.zatvoriKonekciju();
        return so;
    }
    
    
}
