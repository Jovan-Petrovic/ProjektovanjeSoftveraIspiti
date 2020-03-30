/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Reprezentacija;
import domen.Utakmica;
import helper.DBHelper;
import helper.Operacije;
import java.io.IOException;
import java.sql.Connection;
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
 * @author Bron Zilar
 */
public class DBBroker {

    Connection konekcija;
    DBHelper dBHelper;

    public DBBroker() {
        try {
            dBHelper = new DBHelper();
        } catch (IOException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ucitajDrajver() {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName(dBHelper.vratiVrednost(Operacije.DRIVER));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void otvoriKonekciju() {
        try {
//            konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/prosoftjul16g1", "root", "");
            konekcija = DriverManager.getConnection(dBHelper.vratiVrednost(Operacije.URL), dBHelper.vratiVrednost(Operacije.USER), dBHelper.vratiVrednost(Operacije.PASS));
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

    public ArrayList<Utakmica> vratiUtakmice() {
        ArrayList<Utakmica> lista = new ArrayList<>();
        String upit = "SELECT * FROM utakmica u JOIN reprezentacija d ON u.DomacinID = d.ReprezentacijaID JOIN reprezentacija g ON u.GostID = g.ReprezentacijaID";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {
                int domacinID = rs.getInt("d.ReprezentacijaID");
                System.out.println("domacinID " + domacinID);
                String nazivD = rs.getString("d.Naziv");
                Reprezentacija domacin = new Reprezentacija(domacinID, nazivD);

                int gostID = rs.getInt("g.ReprezentacijaID");
                System.out.println("gostId " + gostID);
                String nazivG = rs.getString("g.Naziv");
                Reprezentacija gost = new Reprezentacija(gostID, nazivG);

                int utakmicaID = rs.getInt("UtakmicaID");
                String grupa = rs.getString("Grupa");
                int goloviDomacin = rs.getInt("GolovaDomacin");
                int goloviGost = rs.getInt("GolovaGost");

                Utakmica u = new Utakmica(utakmicaID, grupa, domacin, gost, goloviDomacin, goloviGost, "");
                lista.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<Reprezentacija> vratiRepke() {
        ArrayList<Reprezentacija> lista = new ArrayList<>();
        String upit = "select * from reprezentacija";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {
                int id = rs.getInt("ReprezentacijaID");
                String naziv = rs.getString("Naziv");
                Reprezentacija r = new Reprezentacija(id, naziv);
                lista.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void izmeni(Utakmica utakmica) throws SQLException {
        String upit = "update utakmica set Grupa=?,DomacinID=?,GostID=?,GolovaDomacin=?,GolovaGost=? where UtakmicaID=?";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ps.setString(1, utakmica.getGrupa());
        ps.setInt(2, utakmica.getDomacin().getReprezentacijaID());
        ps.setInt(3, utakmica.getGost().getReprezentacijaID());
        ps.setInt(4, utakmica.getBrojGlovaDomacina());
        ps.setInt(5, utakmica.getBrojGolovaGost());
        ps.setInt(6, utakmica.getUtakmicaID());
        ps.executeUpdate();
    }

    public void obrisi(Utakmica utakmica) throws SQLException {
        String upit = "delete from utakmica where UtakmicaId=" + utakmica.getUtakmicaID();
        Statement s = konekcija.createStatement();
        s.executeUpdate(upit);
    }
}
