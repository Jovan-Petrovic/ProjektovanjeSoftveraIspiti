/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.Advokat;
import domen.Klijent;
import domen.Predmet;
import domen.VrstaPostupka;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pomocneKlase.pretragaKlasa;

/**
 *
 * @author Bron Zilar
 */
public class Kontroler {
    private static Kontroler instance;
    DBBroker db;

    public Kontroler() {
        db = new DBBroker();
    }

    public static Kontroler getInstance() {
        if(instance==null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public ArrayList<Klijent> vratiKlijente() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Klijent> lista = db.vratiKlijente();
        db.zatvoriKonekciju();
        return lista;
    }

    public ArrayList<VrstaPostupka> vratiVrste() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<VrstaPostupka> lista = db.vratiVrste();
        db.zatvoriKonekciju();
        return lista;
    }

    public ArrayList<Advokat> vratiAdvokate() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Advokat> lista = db.vratiAdvokate();
        db.zatvoriKonekciju();
        return lista;
    }

    public boolean sacuvajPredmete(ArrayList<Predmet> lista) {
        boolean sacuvano = false;
        db.ucitajDrajver();
        db.otvoriKonekciju();
        try {
            for (Predmet predmet : lista) {
                int predmetId = db.vratiIDPredmeta();
                predmet.setPredmetID(predmetId);
                db.sacuvajPredmet(predmet);
            }
            db.commit();
            sacuvano = true;
        } catch (SQLException ex) {
            db.rollback();
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.zatvoriKonekciju();
        return sacuvano;
    }

    public ArrayList<pretragaKlasa> vratiPretragu(String pretraga) {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<pretragaKlasa> lista = db.vratiPretragu(pretraga);
        db.zatvoriKonekciju();
        return lista;
    }
    
    
}
