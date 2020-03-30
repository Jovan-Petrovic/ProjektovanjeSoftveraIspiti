/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.Lek;
import domen.Lekar;
import domen.OsiguranoLice;
import domen.Recept;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pomocneklase.PomocnaKlasa;
import transfer.ServerskiOdgovor;

/**
 *
 * @author KORISNIK
 */
public class Kontroler {
    private static Kontroler instanca;
    DBBroker db;

    public Kontroler() {
        db = new DBBroker();
    }

    public static Kontroler getInstanca() {
        if(instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public ArrayList<Lekar> vratiLekare() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Lekar> lista = db.vratiLekare();
        db.zatvoriKonekciju();
        return lista;
    }

    public ArrayList<OsiguranoLice> vratiOsiguranaLica() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<OsiguranoLice> lista = db.vratiOsLica();
        db.zatvoriKonekciju();
        return lista;
    }

    public ArrayList<Lek> vratiLekove() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Lek> lista = db.vratiLekove();
        db.zatvoriKonekciju();
        return lista;
    }

    public ServerskiOdgovor sacuvajRecepte(ArrayList<Recept> listaRecepata) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDrajver();
        db.otvoriKonekciju();
        try {
            for (Recept recept : listaRecepata) {
                    db.sacuvajRecept(recept);
            }
            db.commit();
            so.setPoruka("Svi recepti uspesno sacuvani");
            so.setOdgovor(true);
        } catch (SQLException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
                db.rollback();
                so.setPoruka("Nije sacuvano");
                so.setOdgovor(false);
        }
        db.zatvoriKonekciju();
        return so;
    }

    public ArrayList<PomocnaKlasa> vratiListu(String where) {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<PomocnaKlasa> lista = db.vratiListu(where);
        db.zatvoriKonekciju();
        return lista;
    }
    
    
}
