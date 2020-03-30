/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Dogadjaj;
import domen.Korisnik;
import helper.Helper;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bron Zilar
 */
public class DBBroker {
    Connection konekcija;
    helper.Helper helper;

    public DBBroker() {
        try {
            helper = new Helper();
        } catch (IOException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void ucitajDrajver() {
        try {
            Class.forName(helper.vratiVrednost("driver"));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void otvoriKonekciju() {
        try {
            String url = helper.vratiVrednost("url");
            String username = helper.vratiVrednost("username");
            String pass = helper.vratiVrednost("pass");
            konekcija = DriverManager.getConnection(url, username, pass);
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

    public Korisnik ulogusSe(Korisnik k) {
        Korisnik korisnik = null;
        String upit = "select * from korisnik where korisnickoIme="+"'"+k.getKorisnickoIme()+"'"+" and lozinka="+"'"+k.getLozinka()+"'";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int sifra = rs.getInt("sifra");
                String imePrez = rs.getString("imePrezime");
                korisnik = new Korisnik(sifra, imePrez, k.getKorisnickoIme(), k.getLozinka());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return korisnik;
    }

    public boolean sacuvajDogadjaj(Dogadjaj d) {
        String upit = "insert into dogadjaj(naziv,pocetak,kraj,tip,nazivLokacije) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = konekcija.prepareStatement(upit);
            ps.setString(1, d.getNaziv());
            ps.setTimestamp(2, d.getDatumVremePocetka());
            ps.setTimestamp(3, d.getDatumVremeKraja());
            ps.setString(4, d.getTip());
            ps.setString(5, d.getNazivLokacije());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
