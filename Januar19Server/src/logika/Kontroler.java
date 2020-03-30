package logika;

import db.DBBroker;
import domen.Kviz;
import java.util.ArrayList;
import transfer.ServerskiOdgovor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bron Zilar
 */
public class Kontroler {
    private static  Kontroler instanca;
    DBBroker db;
    private ArrayList<String> igraci;
    private int brojKlijenata;

    private Kontroler() {
        db = new DBBroker();
        igraci = new ArrayList<>();
        brojKlijenata = 0;
    }

    public static Kontroler getInstanca() {
        if(instanca == null)
            instanca = new Kontroler();
        return instanca;
    }
    
    

    public ArrayList<Kviz> vratiKvizove() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Kviz> lista = db.vratiKvizove();
        db.zatvoriKonekciju();
        return lista;
    }

    public boolean posaljiIgraca(String korisnickoIme) {
        if(!igraci.contains(korisnickoIme)) {
            Kontroler.getInstanca().igraci.add(korisnickoIme);
            brojKlijenata++;
            return true;
        } else {
            brojKlijenata--;
            return false;
        }
    }

    public ArrayList<String> getIgraci() {
        return igraci;
    }

    public void setIgraci(ArrayList<String> igraci) {
        this.igraci = igraci;
    }

    public int getBrojKlijenata() {
        return brojKlijenata;
    }

    public void setBrojKlijenata(int brojKlijenata) {
        this.brojKlijenata = brojKlijenata;
    }
    
    
}
