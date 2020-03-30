/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.Fakultet;
import domen.Kandidat;
import domen.Prijava;
import domen.StudijskiProgram;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pomocna.PodaciZaTabelu;
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

    public Kandidat nadjiKandidata(Kandidat k) {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        Kandidat kan = db.vratiKandidata(k);
        db.zatvoriKonekciju();
        return kan;
    }

    public ArrayList<Fakultet> vratiFakultete() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Fakultet> listaF = db.vratiFakultete();
        db.zatvoriKonekciju();
        return listaF;
    }

    public ArrayList<StudijskiProgram> vratiPrograme() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<StudijskiProgram> listaF = db.vratiPrograme();
        db.zatvoriKonekciju();
        return listaF;
    }

    public ArrayList<Prijava> vratiPrijave() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Prijava> listaPrijava = db.vratiPrijave();
        db.zatvoriKonekciju();
        return listaPrijava;
    }

    public ServerskiOdgovor sacuvajPrijavu(Prijava p) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDrajver();
        db.otvoriKonekciju();
        try {
            db.sacuvajPrijavu(p);
            db.commit();
            so.setOdgovor(true);
            so.setPoruka("Uspesno sacubano");
        } catch (SQLException ex) {
            db.rollback();
            so.setOdgovor(false);
            so.setPoruka("Nespesno sacuvano");
        } finally {
            db.zatvoriKonekciju();
        }
        return so;
    }

    public ArrayList<PodaciZaTabelu> vratiPodatke(String whereUslov) {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<PodaciZaTabelu> listaPodataka= db.vratiPodatke(whereUslov);
        db.zatvoriKonekciju();
        return listaPodataka;
    }
    
    
}
