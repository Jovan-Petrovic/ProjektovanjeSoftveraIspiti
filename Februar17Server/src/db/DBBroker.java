/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.KursnaLista;
import domen.StavkaKursneListe;
import domen.Zemlja;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bron Zilar
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
            konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/prosoftfeb17", "root", "");
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

    public ArrayList<Zemlja> vratiZemlje() {
        ArrayList<Zemlja> lista = new ArrayList<>();
        String upit = "select * from zemlja";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int zemljaId = rs.getInt("ZemljaID");
                String naziv = rs.getString("Naziv");
                String valuta = rs.getString("Valuta");
                Zemlja zemlja = new Zemlja(zemljaId, naziv, valuta);
                lista.add(zemlja);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<Date> vratiDatume() {
        ArrayList<Date> lista = new ArrayList<>();
        String upit = "select Dan from kursnalista";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                Date datum = new Date(rs.getDate("Dan").getTime());
                lista.add(datum);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public int sacuvajKl(KursnaLista kl) throws SQLException {
        String upit = "insert into kursnalista (Izvor,Dan) values (?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit,Statement.RETURN_GENERATED_KEYS);
        ps.setTimestamp(2, new Timestamp(kl.getDan().getTime()));
        ps.setString(1, kl.getIzvor());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        int id = 0;
        if(rs.next()) {
            id = rs.getInt(1);
        }
        return id;
    }

    public void sacuvajStavku(StavkaKursneListe s, int id) throws SQLException {
        String upit = "insert into stavkakursneliste values (?,?,?,?,?,?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ps.setInt(1, id);
        ps.setInt(2, s.getRb());
        ps.setInt(3, s.getZemlja().getZemljaID());
        ps.setInt(4, s.getVaziZa());
        ps.setDouble(5, s.getKupovni());
        ps.setDouble(6, s.getProdajni());
        ps.setDouble(7, s.getSrednji());
        ps.executeUpdate();
    }

    public ArrayList<StavkaKursneListe> vratiStavkeServer(String where) {
        ArrayList<StavkaKursneListe> lista = new ArrayList<>();
        String upit = "SELECT z.*, s.VaziZa, AVG(s.Kupovni) AS kupovni, AVG(s.Prodajni) AS prodajni, AVG(s.Srednji) AS srednji FROM stavkakursneliste s JOIN zemlja z ON s.ZemljaID=z.ZemljaID JOIN kursnalista kl ON s.KursnaListaID=kl.KursnaListaID"+where+" GROUP BY s.ZemljaID";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int zemljaId = rs.getInt("ZemljaID");
                String naziv = rs.getString("Naziv");
                String valuta = rs.getString("Valuta");
                Zemlja zemlja = new Zemlja(zemljaId, naziv, valuta);
                
                int vaziZa = rs.getInt("VaziZa");
                double kupovni = rs.getDouble("kupovni");
                double prodajni = rs.getDouble("prodajni");
                double srednji = rs.getDouble("srednji");
                
                StavkaKursneListe skl = new StavkaKursneListe(vaziZa, zemlja, vaziZa, kupovni, srednji, prodajni);
                lista.add(skl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
