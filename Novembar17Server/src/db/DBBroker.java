/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Investicija;
import domen.Investitior;
import domen.Kompanija;
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

/**
 *
 * @author KORISNIK
 */
public class DBBroker {

    Connection konekcija;

    public void ucitajDrajver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void otvoriKonekciju() {
        try {
            konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/prosoftnov17", "root", "");
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

    public Investitior nadjiInvestitora(Investitior i) {
        Investitior investitior = null;
        String upit = "select * from investitor where username = ? and password = ? LIMIT 1";
        try {
            PreparedStatement ps = konekcija.prepareStatement(upit);
            ps.setString(1, i.getUsername());
            ps.setString(2, i.getPassword());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int invID = rs.getInt("InvestitorID");
                String naziv = rs.getString("Naziv");
                String adresa = rs.getString("Adresa");
                String username = rs.getString("Username");
                String pass = rs.getString("Password");
                investitior = new Investitior(invID, naziv, adresa, username, pass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return investitior;
    }

    public ArrayList<Kompanija> vratiKompanije() {
        ArrayList<Kompanija> listaKompanija = new ArrayList<>();
        String upit = "SELECT k.*, SUM(i.Iznos) AS ukupanIznos FROM kompanija k LEFT JOIN investicija i ON k.KompanijaID=i.KompanijaID GROUP BY k.KompanijaID ORDER BY ukupanIznos desc, Tip asc";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {
                int kompanijaID = rs.getInt("k.KompanijaID");
                String naziv = rs.getString("k.Naziv");
                String oblastPoslovanja = rs.getString("k.OblastPoslovanja");
                boolean ipo = rs.getBoolean("IPO");
                String tip = rs.getString("Tip");
                double ukupanIznos = rs.getDouble("ukupanIznos");

                Kompanija k = new Kompanija(kompanijaID, naziv, oblastPoslovanja, ipo, tip, ukupanIznos);
                listaKompanija.add(k);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaKompanija;
    }

    public void sacuvajInvesticiju(Investicija inv) throws SQLException {
        String upit = "insert into investicija(InvestitorID,KompanijaID,Datum,Iznos) values(?,?,?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ps.setInt(1, inv.getInvestitior().getInvestitorID());
        ps.setInt(2, inv.getKompanija().getKompanijaID());
        ps.setDate(3, new Date(inv.getDatum().getTime()));
        ps.setDouble(4, inv.getIznos());
//            System.out.println("ps"+ps);
        ps.executeUpdate();
    }

    public String srediTip(Kompanija kompanija) throws SQLException {
        String upit = "SELECT SUM(i.Iznos) AS ukupanIznos FROM kompanija k LEFT JOIN investicija i ON k.KompanijaID=i.KompanijaID WHERE k.KompanijaID = " + kompanija.getKompanijaID();
        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(upit);
        double suma = 0;
        while (rs.next()) {
            suma = rs.getDouble("ukupanIznos");
        }
        String tip = odrediTip(suma);
        String upit2 = "update kompanija set Tip=? where KompanijaID=?";
        PreparedStatement ps2 = konekcija.prepareStatement(upit2);
        ps2.setString(1, tip);
        ps2.setInt(2, kompanija.getKompanijaID());
        ps2.executeUpdate();
        return tip;
    }

    private String odrediTip(double suma) {
        if (suma > 0 && suma <= 1000000) {
            return "A";
        }
        if (suma > 1000000 && suma <= 1000000000) {
            return "B";
        }
        return "C";
    }

    public String srediTip(Kompanija kompanija, double iznosNoveInvesticije) throws SQLException {
        String upit = "SELECT SUM(i.Iznos) AS ukupanIznos FROM kompanija k LEFT JOIN investicija i ON k.KompanijaID=i.KompanijaID WHERE k.KompanijaID = " + kompanija.getKompanijaID();
        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(upit);
        double suma = 0;
        while (rs.next()) {
            suma = rs.getDouble("ukupanIznos");
        }
        suma+=iznosNoveInvesticije;
        String tip = odrediTip(suma);
        String upit2 = "update kompanija set Tip=? where KompanijaID=?";
        PreparedStatement ps2 = konekcija.prepareStatement(upit2);
        ps2.setString(1, tip);
        ps2.setInt(2, kompanija.getKompanijaID());
        ps2.executeUpdate();
        return tip;
    }

}
