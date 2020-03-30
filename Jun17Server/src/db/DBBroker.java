/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Oprema;
import domen.Proizvodjac;
import domen.Rentiranje;
import domen.StavkaRentiranja;
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
import pomocneKlase.PomocnaKlasa;

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
            konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/prosoftjun17", "root", "");
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

    public ArrayList<Oprema> vratiOpreme() {
        ArrayList<Oprema> lista = new ArrayList<>();
        String upit = "select * from oprema o join proizvodjac p on o.ProizvodjacID=p.ProizvodjacID";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int proizvodjacID = rs.getInt("p.ProizvodjacID");
                String nazivP = rs.getString("p.Naziv");
                String adresa = rs.getString("p.Adresa");
                Proizvodjac p = new Proizvodjac(proizvodjacID, nazivP, adresa);
                
                int opremaID = rs.getInt("o.OpremaID");
                String naziv = rs.getString("o.Naziv");
                double cenaPoDanu = rs.getDouble("o.CenaPoDanu");
                Oprema o = new Oprema(opremaID, naziv, cenaPoDanu, p);
                
                lista.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void unesiRentiranje(Rentiranje r) throws SQLException {
        String upit = "insert into rentiranje(RentiranjeID,Klijent,DatumOd,DatumDo,UkupanIznos) values(?,?,?,?,?)";
        PreparedStatement ps =konekcija.prepareStatement(upit);
        ps.setInt(1, r.getRentiranjeID());
        ps.setString(2, r.getKlijent());
        ps.setDate(3, new Date(r.getDatumOd().getTime()));
        ps.setDate(4, new Date(r.getDatumDo().getTime()));
        ps.setDouble(5, r.getUkupanIznos());
        ps.executeUpdate();
        
    }

    public int vratiID() throws SQLException {
        int maks = 0;
        String upit = "select max(RentiranjeID) as maks from rentiranje";

            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                maks = rs.getInt("maks");
            }

        return maks+1;
    }

    public void unesiStavku(StavkaRentiranja s, int id) throws SQLException {
        String upit = "insert into stavkarentiranja values(?,?,?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ps.setInt(1, id);
        ps.setInt(2, s.getRb());
        ps.setInt(3, s.getOprema().getOpremaID());
        ps.setDouble(4, s.getIznos());
        ps.executeUpdate();
    }

    public ArrayList<Proizvodjac> vratiProizvodjace() {
        ArrayList<Proizvodjac> lista = new ArrayList<>();
        String upit = "select * from proizvodjac";
        Statement s;
        try {
            s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int pID = rs.getInt("ProizvodjacID");
                String naziv = rs.getString("Naziv");
                String adresa = rs.getString("Adresa");
                Proizvodjac p = new Proizvodjac(pID, naziv, adresa);
                lista.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
        
    }

    public ArrayList<PomocnaKlasa> vratiListuZaServer(Proizvodjac p) {
        ArrayList<PomocnaKlasa> lista = new ArrayList<>();
//        String upit = "select *,sum(s.Iznos/o.CenaPoDanu) as brojRentiranihDana, sum(s.Iznos) as prihod from oprema o join proizvodjac p on o.ProizvodjacID=p.ProizvodjacID join stavkarentiranja s on o.OpremaID=s.OpremaID group by o.OpremaID order by prihod desc, brojRentiranihDana desc";
        String upit = "SELECT *, SUM(DATEDIFF(r.DatumDo,r.DatumOd)) AS brojRentiranihDana ,SUM(DATEDIFF(r.DatumDo,r.DatumOd)*o.CenaPoDanu) AS prihod FROM rentiranje r JOIN stavkarentiranja s ON r.RentiranjeID=s.RentiranjeID JOIN oprema o ON s.OpremaId=o.OpremaID JOIN proizvodjac p ON o.ProizvodjacID=p.ProizvodjacID GROUP BY o.OpremaID ORDER BY prihod DESC, brojRentiranihDana DESC";
        if(p!=null) {
            upit = "SELECT *, SUM(DATEDIFF(r.DatumDo,r.DatumOd)) AS brojRentiranihDana ,SUM(DATEDIFF(r.DatumDo,r.DatumOd)*o.CenaPoDanu) AS prihod FROM rentiranje r JOIN stavkarentiranja s ON r.RentiranjeID=s.RentiranjeID JOIN oprema o ON s.OpremaId=o.OpremaID JOIN proizvodjac p ON o.ProizvodjacID=p.ProizvodjacID where p.ProizvodjacID="+p.getProizvodjacID()+" GROUP BY o.OpremaID ORDER BY prihod DESC, brojRentiranihDana DESC";
        }
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int proizvodjacID = rs.getInt("p.ProizvodjacID");
                String nazivP = rs.getString("p.Naziv");
                String adresa = rs.getString("p.Adresa");
                Proizvodjac proizvodjac = new Proizvodjac(proizvodjacID, nazivP, adresa);
                
                int opremaID = rs.getInt("o.OpremaID");
                String naziv = rs.getString("o.Naziv");
                double cenaPoDanu = rs.getDouble("o.CenaPoDanu");
                Oprema o = new Oprema(opremaID, naziv, cenaPoDanu, proizvodjac);
                int brojRentiranihDana = rs.getInt("brojRentiranihDana");
                double suma = rs.getDouble("prihod");
                PomocnaKlasa pm = new PomocnaKlasa(o, brojRentiranihDana, suma);
                lista.add(pm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
