/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Linija;
import domen.Stanica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import pomocne.PomocnaKlasa;

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
            konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/prosoftjun19", "root", "");
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

    public ArrayList<Stanica> vratiStanice() {
        ArrayList<Stanica> lista = new ArrayList<>();
        String upit = "select * from stanica";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int stanicaId = rs.getInt("StanicaID");
                String naziv = rs.getString("NazivStanice");
                Stanica stanica = new Stanica(stanicaId, naziv);
                lista.add(stanica);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<Linija> vratiLinije() {
        ArrayList<Linija> lista = new ArrayList<>();
        String upit = "select * from linija l join stanica ps on l.PocetnaStanica=ps.StanicaID join stanica ks on l.KrajnjaStanica=ks.StanicaID";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int pocetnastanicaId = rs.getInt("ps.StanicaID");
                String pocetnaStanicaNzaiv = rs.getString("ps.NazivStanice");
                Stanica pocetnaStanica = new Stanica(pocetnastanicaId, pocetnaStanicaNzaiv);
                
                int krajnjastanicaId = rs.getInt("ks.StanicaID");
                String krajnjaStanicaNzaiv = rs.getString("ks.NazivStanice");
                Stanica krajnjaStanica = new Stanica(krajnjastanicaId, krajnjaStanicaNzaiv);
                
                int linijaID = rs.getInt("l.LinijaID");
                String naziv = rs.getString("l.NazivLinije");
                Linija linija = new Linija(linijaID, naziv, pocetnaStanica, krajnjaStanica);
                lista.add(linija);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<Integer> vratiMedjustaniceZaLiniju(int id) {
        ArrayList<Integer> lista = new ArrayList<>();
        String upit = "select StanicaID from linijastanica where LinijaID="+id;
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int stanicaID = rs.getInt("StanicaID");
                lista.add(stanicaID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean sacuvaj(Map<String, Object> mapa) {
       Linija linija = (Linija) mapa.get("linija");
       ArrayList<Integer> lista = (ArrayList<Integer>) mapa.get("medjustanice");
       String upit = "insert into linija(NazivLinije,PocetnaStanica,KrajnjaStanica) values(?,?,?)";
        try {
            PreparedStatement ps = konekcija.prepareStatement(upit,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, linija.getNazivLinije());
            ps.setInt(2, linija.getPocetnaStanica().getStanicaID());
            ps.setInt(3, linija.getKrajnjaStanica().getStanicaID());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                linija.setLinijaID(rs.getInt(1));
            }
            
            upit = "insert into linijastanica values(?,?)";
            for(Integer i : lista) {
                ps = konekcija.prepareStatement(upit);
                ps.setInt(1, linija.getLinijaID());
                ps.setInt(2, i);
                ps.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ArrayList<PomocnaKlasa> vratiZaServer(String where) {
        ArrayList<PomocnaKlasa> lista = new ArrayList<>();
        String upit;
        if(where != "") {
            upit = "SELECT * FROM linijastanica ls JOIN linija lp ON ls.LinijaID=lp.LinijaID JOIN stanica s ON ls.StanicaID=s.StanicaID JOIN stanica s1 ON lp.PocetnaStanica=s1.StanicaID JOIN stanica s2 ON lp.KrajnjaStanica=s2.StanicaID WHERE s.NazivStanice LIKE '%"+where+"%' OR s1.NazivStanice LIKE '%"+where+"%' OR s2.NazivStanice LIKE '%"+where+"%'";
        } else {
            upit = "SELECT * FROM linijastanica ls JOIN linija lp ON ls.LinijaID=lp.LinijaID JOIN stanica s ON ls.StanicaID=s.StanicaID JOIN stanica s1 ON lp.PocetnaStanica=s1.StanicaID JOIN stanica s2 ON lp.KrajnjaStanica=s2.StanicaID";
        }
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int pocStanID = rs.getInt("s1.StanicaID");
                String nazivPocStan = rs.getString("s1.NazivStanice");
                Stanica pocetnaStanica = new Stanica(pocStanID, nazivPocStan);
                
                int krajStanID = rs.getInt("s2.StanicaID");
                String krajStanNaziv = rs.getString("s2.NazivStanice");
                Stanica krajnjaStanica = new Stanica(krajStanID, krajStanNaziv);
                
                int linijaID = rs.getInt("lp.LinijaID");
                String nazivLinije = rs.getString("lp.NazivLinije");
                Linija linija = new Linija(linijaID, nazivLinije, pocetnaStanica, krajnjaStanica);
                
                String medjuStanica = rs.getString("s.NazivStanice");
                
                PomocnaKlasa pk = new PomocnaKlasa(linija, medjuStanica);
                lista.add(pk);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
