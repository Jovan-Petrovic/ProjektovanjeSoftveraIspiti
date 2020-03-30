/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.Prevod;
import domen.Rec;
import domen.Recnik;
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

    public Kontroler() {
        db = new DBBroker();
    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public ArrayList<Recnik> vratiRecnike() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Recnik> lista = db.vratiRecnike();
        db.zatvoriKonekciju();
        return lista;
    }

    public ServerskiOdgovor sacuvaj(Rec r) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Rec> reci = db.vratiReci();
        try {
            if(!reci.contains(r)) {
                Rec rec = db.sacuvajRec(r);
                for (Prevod p : r.getPrevodi()) {
                    db.sacuvajPrevod(p, rec.getRecID());
                }
            } else {
                Rec rec = null;
                for(Rec r1 : reci) {
                    if(r1.equals(r)) {
                        rec = r1;
                    }
                }
                ArrayList<Prevod> prevodi = db.vratiPrevode();

                for (Prevod p : r.getPrevodi()) {
                    if(!prevodi.contains(p)) {
                        db.sacuvajPrevod(p, rec.getRecID());
                    }
                }
            }
            db.commit();
            so.setPoruka("Sve je uspesno sacuvano");
        } catch (SQLException ex) {
            db.rollback();
            so.setPoruka("Nije sve uspesno sacuvano");
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return so;
    }

    public ArrayList<Rec> vratiReciUslov(int broj) {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Rec> lista = db.vratiReciUslov(broj);
        for (Rec rec : lista) {
            ArrayList<Prevod> prevodi = db.vratiPrevodeUslov(rec);
            rec.setPrevodi(prevodi);
        }
        db.zatvoriKonekciju();
        return lista;
    }

    public ServerskiOdgovor sacuvajVol2(ArrayList<Rec> reci) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Rec> reciIzBaze = db.vratiReci();
        try {
            for(Rec r : reci) {
                if(!reciIzBaze.contains(r)) {
                    Rec rec = db.sacuvajRec(r);
                    for (Prevod p : r.getPrevodi()) {
                        db.sacuvajPrevod(p, rec.getRecID());
                    }
                } else {
                    Rec rec = null;
                    for(Rec r1 : reciIzBaze) {
                        if(r1.equals(r)) {
                            rec = r1;
                        }
                    }
                    ArrayList<Prevod> prevodi = db.vratiPrevode();

                    for (Prevod p : r.getPrevodi()) {
                        if(!prevodi.contains(p)) {
                            db.sacuvajPrevod(p, rec.getRecID());
                        }
                    }
                }
            }
            db.commit();
            so.setPoruka("Sve je uspesno sacuvano");
        } catch (SQLException ex) {
            db.rollback();
            so.setPoruka("Nije sve uspesno sacuvano");
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return so;
    }




}
