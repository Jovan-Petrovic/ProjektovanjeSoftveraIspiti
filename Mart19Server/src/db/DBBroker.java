/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Advokat;
import domen.Klijent;
import domen.Predmet;
import domen.VrstaPostupka;
import helper.Helper;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import kons.Konstante;
import pomocneKlase.pretragaKlasa;

/**
 *
 * @author Bron Zilar
 */
public class DBBroker {
    
    Connection konekcija;
    Helper helper;

    public DBBroker() {
        try {
            helper = new Helper();
        } catch (IOException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void ucitajDrajver() {
        try {
            Class.forName(helper.vratiVrednost(Konstante.DRIVER));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void otvoriKonekciju() {
        try {
            String url = helper.vratiVrednost(Konstante.URL);
            String user = helper.vratiVrednost(Konstante.USER);
            String pass = helper.vratiVrednost(Konstante.PASS);
            konekcija = DriverManager.getConnection(url, user, pass);
            konekcija.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void zatvoriKonekciju() {
        try {
            konekcija.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void commit() {
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void rollback() {
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Klijent> vratiKlijente() {
        ArrayList<Klijent> lista = new ArrayList<>();
        String upit = "select * from klijent";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                Klijent k = new Klijent(rs.getInt("klijentID"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("Telefon"), rs.getString("ElPosta"), rs.getString("Adresa"));
                lista.add(k);
            }
            s.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<VrstaPostupka> vratiVrste() {
        ArrayList<VrstaPostupka> lista = new ArrayList<>();
        String upit = "select * from vrstapostuka";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                VrstaPostupka vp = new VrstaPostupka(rs.getInt("VrstaPostukaID"), rs.getString("Naziv"));
                lista.add(vp);
            }
            s.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<Advokat> vratiAdvokate() {
        ArrayList<Advokat> lista = new ArrayList<>();
        String upit = "select * from advokat a join vrstapostuka vp on a.SpecijalnostZaVrstuPostupka=vp.VrstaPostukaID";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                VrstaPostupka vp = new VrstaPostupka(rs.getInt("VrstaPostukaID"), rs.getString("Naziv"));
                Advokat a = new Advokat(rs.getInt("AdvokatID"), rs.getString("Ime"), rs.getString("Prezime"), vp);
                lista.add(a);
            }
            s.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void sacuvajPredmet(Predmet predmet) throws SQLException {
        String upit = "insert into predmet values(?,?,?,?,?,?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ps.setInt(1, predmet.getPredmetID());
        ps.setString(2, predmet.getNaziv());
        ps.setString(3, predmet.getProblem());
        ps.setDate(4, new Date(predmet.getDatum().getTime()));
        ps.setInt(5, predmet.getAdvokat().getAdvokatID());
        ps.setInt(7, predmet.getVp().getVrstaPostupkaID());
        ps.setInt(6, predmet.getKlijent().getKlijentID());
        ps.executeUpdate();
        
    }

    public int vratiIDPredmeta() {
        int max = 0;
        String upit = "select max(PredmetID) as max from predmet";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                max = rs.getInt("max");
            }
            s.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ++max;
    }

    public ArrayList<pretragaKlasa> vratiPretragu(String pretraga) {
        ArrayList<pretragaKlasa> lista = new ArrayList<>();
        String upit = "SELECT a.Ime, a.Prezime, COUNT(p.PredmetID) AS brojPredmeta FROM advokat a JOIN predmet p ON a.AdvokatID = p.AdvokatID WHERE a.Ime LIKE '%"+pretraga+"%' OR a.Prezime LIKE '%"+pretraga+"%' GROUP BY a.AdvokatID ORDER BY brojPredmeta DESC";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                String ime = rs.getString("Ime");
                String prezime = rs.getString("Prezime");
                int brojpredmeta = rs.getInt("brojPredmeta");
                pretragaKlasa pk = new pretragaKlasa(brojpredmeta, ime + " " + prezime);
                lista.add(pk);
            }
            s.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
