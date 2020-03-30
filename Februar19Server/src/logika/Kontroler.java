/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.Dogadjaj;
import domen.Korisnik;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Bron Zilar
 */
public class Kontroler {
    DBBroker db;
    private static Kontroler instanca;
    ArrayList<Korisnik> korisnici;
    public static final int MAX_BROJ_POKUSAJA = 3;
    int preostalo;

    private Kontroler() {
        db = new DBBroker();
        korisnici = new ArrayList<>();
        preostalo = MAX_BROJ_POKUSAJA;
    }

    public static Kontroler getInstanca() {
        if(instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public ServerskiOdgovor ulogujSe(Korisnik k) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDrajver();
        db.otvoriKonekciju();
        Korisnik korisnik = db.ulogusSe(k);
        Map<String,Object> mapa;
        Korisnik k1 = new Korisnik(0, "", "", "");
        if(korisnik != null && !korisnici.contains(korisnik)) {
            korisnici.add(korisnik);
            so.setPoruka("Uspesno ste se ulogovali");
            mapa = new HashMap<>();
            mapa.put("korisnik", korisnik);
            mapa.put("preostalo", preostalo);
            so.setOdgovor(mapa);
        } else if(korisnik==null) {
            preostalo--;
            so.setPoruka("Korisnik ne postoji u bazi. Preostalo pokusaja: " + preostalo);
            mapa = new HashMap<>();
            mapa.put("korisnik", k1);
            mapa.put("preostalo", preostalo);
            so.setOdgovor(mapa);
        } else {
            preostalo--;
            so.setPoruka("Korisnik je vec ulogovan. Preostalo pokusaja: " + preostalo);
            mapa = new HashMap<>();
            mapa.put("korisnik", k1);
            mapa.put("preostalo", preostalo);
            so.setOdgovor(mapa);
        }
        return so;
    }

    public ServerskiOdgovor sacuvajDogadjaj(Dogadjaj d) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDrajver();
        db.otvoriKonekciju();
        boolean status = db.sacuvajDogadjaj(d);
        if(status) {
            so.setPoruka("uspesno");
            db.commit();
        } else {
            so.setPoruka("greska");
            db.rollback();
        }
        db.zatvoriKonekciju();
        return so;
    }
    
    
}
