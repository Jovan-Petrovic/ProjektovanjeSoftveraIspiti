package db;

import domen.Prevod;
import domen.Rec;
import domen.Recnik;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
            konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/prosoftjanuar20", "root", "");
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

    public ArrayList<Recnik> vratiRecnike() {
        ArrayList<Recnik> lista = new ArrayList<>();
        String upit = "select * from recnik";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int id = rs.getInt("recnikID");
                String jezik = rs.getString("jezik");
                Recnik r = new Recnik(id, jezik);
                lista.add(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public Rec sacuvajRec(Rec r) throws SQLException {
        String upit = "insert into rec(rec,recnikID) values(?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, r.getRec());
        ps.setInt(2, r.getRecnik().getRecnikID());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()) {
            r.setRecID(rs.getInt(1));
        }
        return r;
    }

    public void sacuvajPrevod(Prevod p, int recID) throws SQLException {
        String upit = "insert into prevod(prevod,saJezika,naJezik,recID) values(?,?,?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ps.setString(1, p.getPrevod());
        ps.setInt(2, p.getSaJezika().getRecnikID());
        ps.setInt(3, p.getNaJezik().getRecnikID());
        ps.setInt(4, recID);
        ps.executeUpdate();
        
    }

    public ArrayList<Rec> vratiReci() {
        ArrayList<Rec> lista = new ArrayList<>();
        String upit = "select * from rec r join recnik rec on r.recnikID=rec.recnikID";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int recnikID = rs.getInt("recnikID");
                String jezik = rs.getString("jezik");
                Recnik recnik = new Recnik(recnikID, jezik);
                int recID = rs.getInt("recID");
                String rec = rs.getString("rec");
                Rec r = new Rec(recID, rec, recnik, null);
                lista.add(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<Prevod> vratiPrevode() {
        ArrayList<Prevod> lista = new ArrayList<>();
        String upit = "select * from prevod p join recnik r1 on p.saJezika = r1.recnikID join recnik r2 on p.naJezik=r2.recnikID join rec r on p.recID=r.recID";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int recnik1ID = rs.getInt("r1.recnikID");
                String jezik1 = rs.getString("r1.jezik");
                Recnik saJezika = new Recnik(recnik1ID, jezik1);
                
                int recnik2ID = rs.getInt("r1.recnikID");
                String jezik2 = rs.getString("r1.jezik");
                Recnik naJezik = new Recnik(recnik1ID, jezik1);
                
                int recID=rs.getInt("r.recID");
                String rec=rs.getString("r.rec");
                Rec r = new Rec(recID, rec, saJezika, null);
         
                int prevodID = rs.getInt("p.prevodID");
                String prevod = rs.getString("p.prevod");
                Prevod p = new Prevod(prevodID, prevod, saJezika, naJezik,r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<Rec> vratiReciUslov(int broj) {
        ArrayList<Rec> lista = new ArrayList<>();
        String upit = "select * from rec r join recnik rec on r.recnikID=rec.recnikID where rec.recnikID="+broj;
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int recnikID = rs.getInt("recnikID");
                String jezik = rs.getString("jezik");
                Recnik recnik = new Recnik(recnikID, jezik);
                int recID = rs.getInt("recID");
                String rec = rs.getString("rec");
                Rec r = new Rec(recID, rec, recnik, null);
                lista.add(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<Prevod> vratiPrevodeUslov(Rec rec) {
        ArrayList<Prevod> lista = new ArrayList<>();
        String upit = "select * from prevod p join recnik r1 on p.saJezika = r1.recnikID join recnik r2 on p.naJezik=r2.recnikID where p.recID="+rec.getRecID();
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int recnik1ID = rs.getInt("r1.recnikID");
                String jezik1 = rs.getString("r1.jezik");
                Recnik saJezika = new Recnik(recnik1ID, jezik1);
                
                int recnik2ID = rs.getInt("r2.recnikID");
                String jezik2 = rs.getString("r2.jezik");
                Recnik naJezik = new Recnik(recnik2ID, jezik2);
         
                int prevodID = rs.getInt("p.prevodID");
                String prevod = rs.getString("p.prevod");
                Prevod p = new Prevod(prevodID, prevod, saJezika, naJezik,rec);
                lista.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
