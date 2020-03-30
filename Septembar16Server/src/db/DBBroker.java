/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Lek;
import domen.Lekar;
import domen.OsiguranoLice;
import domen.Recept;
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
import pomocneklase.PomocnaKlasa;

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
            konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/septembar16", "root", "");
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

    public ArrayList<Lekar> vratiLekare() {
        ArrayList<Lekar> lista = new ArrayList<>();
        String upit = "select * from lekar";
        Statement s;
        try {
            s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int lekarId = rs.getInt("lekarID");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String specijalnost = rs.getString("specijalnost");
                Lekar lekar = new Lekar(lekarId, ime, prezime, specijalnost);
                lista.add(lekar);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<OsiguranoLice> vratiOsLica() {
        ArrayList<OsiguranoLice> lista = new ArrayList<>();
        String upit = "select * from osiguranolice";
        Statement s;
        try {
            s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int osLiceID = rs.getInt("osiguranoLiceID");
                int lbo = rs.getInt("lbo");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String osnovOsiguranja = rs.getString("osnovOsiguranja");
                OsiguranoLice ol = new OsiguranoLice(osLiceID, lbo, ime, prezime, osnovOsiguranja);
                lista.add(ol);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<Lek> vratiLekove() {
        ArrayList<Lek> lista = new ArrayList<>();
        String upit = "select * from lek";
        Statement s;
        try {
            s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int lekID = rs.getInt("lekID");
                String naziv = rs.getString("naziv");
                double cena = rs.getDouble("cena");
                String listaLek = rs.getString("lista");
                Lek l = new Lek(lekID, naziv, cena, listaLek);
                lista.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void sacuvajRecept(Recept recept) throws SQLException {
        String upit = "insert into recept(datum,kolicina,ukupanIznos,lekarID,osiguranoLiceID,lekID) values (?,?,?,?,?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ps.setDate(1, new Date(recept.getDatum().getTime()));
        ps.setInt(2, recept.getKolicina());
        ps.setDouble(3, recept.getUkupanIznos());
        ps.setInt(4, recept.getLekar().getLekarID());
        ps.setInt(5, recept.getOsiguranoLice().getOsiguranoLiceID());
        ps.setInt(6, recept.getLek().getLekID());
        ps.executeUpdate();
    }

    public ArrayList<PomocnaKlasa> vratiListu(String where) {
        String upit = "SELECT l.naziv, l.cena, COUNT(r.lekID) AS broj, COUNT(r.lekID)*l.cena AS rashodi, SUM(r.ukupanIznos) AS prihodi FROM recept r JOIN lek l ON r.lekID=l.lekID"+where+" GROUP BY r.lekID ORDER BY rashodi DESC";
        ArrayList<PomocnaKlasa> lista = new ArrayList<>();
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            
            while(rs.next()) {
                String naziv = rs.getString("naziv");
                double cena = rs.getDouble("cena");
                int broj = rs.getInt("broj");
                double rashodi = rs.getDouble("rashodi");
                double prihodi = rs.getDouble("prihodi");
                PomocnaKlasa pk = new PomocnaKlasa(naziv, cena, broj, prihodi, rashodi);
                lista.add(pk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
        
    }

    
}
