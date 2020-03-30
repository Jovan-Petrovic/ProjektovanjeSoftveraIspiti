/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.Investicija;
import domen.Investitior;
import domen.Kompanija;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ServerskiOdgovor;

/**
 *
 * @author KORISNIK
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

    public ServerskiOdgovor nadjiInvestitora(Investitior i) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDrajver();
        db.otvoriKonekciju();
        Investitior investitior = db.nadjiInvestitora(i);
        so.setOdgovor(investitior);
        if(investitior != null) {
            so.setPoruka("Uspesno ste se ulogovali");
        } else {
            so.setPoruka("Pogresni podaci");
        }   
        db.zatvoriKonekciju();
        return so;
    }

    public ArrayList<Kompanija> vratiKompanije() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Kompanija> listaKompanija = db.vratiKompanije();
        db.zatvoriKonekciju();
        return listaKompanija;
    }

    public ServerskiOdgovor sacuvajInvesticiju(Investicija inv) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDrajver();
        db.otvoriKonekciju();
        try {
//            db.sacuvajInvesticiju(inv);
//            String tip = db.srediTip(inv.getKompanija());
//            db.commit();
            String tip = db.srediTip(inv.getKompanija(), inv.getIznos());
            
            db.sacuvajInvesticiju(inv);
            db.commit();
            so.setPoruka("Ispeso sacuvano. Kompanija "+inv.getKompanija()+" ima tip "+tip);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            db.rollback();
            so.setPoruka("Neuspesno sacuvano");
        }
        db.zatvoriKonekciju();
        return so;
    }

    

    
}
