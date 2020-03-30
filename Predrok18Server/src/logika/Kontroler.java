/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.PoreskaPrijava;
import domen.PoreskiInspektor;
import domen.PoreskiObveznik;
import domen.VrstaPoreza;
import java.util.ArrayList;
import niti.PokretanjeServera;
import pomocne.PomocnaKlasa;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Bron Zilar
 */
public class Kontroler {
    DBBroker db;
    private static Kontroler instanca;

    private Kontroler() {
        db = new DBBroker();
    }

    public static Kontroler getInstanca() {
        if(instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public ServerskiOdgovor prijava(PoreskiInspektor inspektor) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDriver();
        db.pokreniKonekciju();
        PoreskiInspektor inspektor1 = db.prijava(inspektor);
        so.setOdgovor(inspektor1);
        db.zaustaviKonekciju();
        return so;
    }

    public ServerskiOdgovor vratiObveznike() {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDriver();
        db.pokreniKonekciju();
        ArrayList<PoreskiObveznik> lista = db.vratiObveznike();
        so.setOdgovor(lista);
        db.zaustaviKonekciju();
        return so;  
    }

    public ServerskiOdgovor vratiVrstePoreza() {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDriver();
        db.pokreniKonekciju();
        ArrayList<VrstaPoreza> lista = db.vratiVrstePoreza();
        so.setOdgovor(lista);
        db.zaustaviKonekciju();
        return so; 
    }

    public ServerskiOdgovor vratiRedniBroj() {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDriver();
        db.pokreniKonekciju();
        int rb = db.vratiRedniBroj();
        so.setOdgovor(rb);
        db.zaustaviKonekciju();
        return so;
    }

    public ServerskiOdgovor sacuvajPoreskuPrijavu(PoreskaPrijava prijava) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDriver();
        db.pokreniKonekciju();
        int id = db.vratiPrijavaID();
        prijava.setPrijavaID(id);
        boolean status = db.sacuvajPoreskuPrijavu(prijava);
        if(status) {
            so.setPoruka("Sve je uspesno sacuvano");
            db.commit();
        } else {
            so.setPoruka("Doslo je do greske prilikom cuvanja");
            db.rollback();
        }
        db.zaustaviKonekciju();
        return so;
    }

    public ArrayList<PomocnaKlasa> vratiZaServer(String filter) {
        db.ucitajDriver();
        db.pokreniKonekciju();
        ArrayList<PomocnaKlasa> lista = db.vratiZaServer(filter);
        db.zaustaviKonekciju();
        return lista;
    }

    
    
    
}
