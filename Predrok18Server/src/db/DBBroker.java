/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.FizickoLice;
import domen.PoreskaPrijava;
import domen.PoreskiInspektor;
import domen.PoreskiObveznik;
import domen.PravnoLice;
import domen.StavkaPoreskePrijave;
import domen.VrstaPoreza;
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
import pomocne.PomocnaKlasa;

/**
 *
 * @author Bron Zilar
 */
public class DBBroker {
    Connection konekcija;
    
    public void ucitajDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void pokreniKonekciju() {
        try {
            konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/prosoftjun18predrok", "root", "");
            konekcija.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void zaustaviKonekciju() {
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

    public PoreskiInspektor prijava(PoreskiInspektor inspektor) {
        String upit = "select * from poreskiinspektor";
        Statement s;
        try {
            s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int inspektorID = rs.getInt("InspektorID");
                String imePrezime = rs.getString("ImePrezime");
                String korisnickoIme = rs.getString("KorisnickoIme");
                String lozinka = rs.getString("Lozinka");
                
                if(inspektor.getKorisnickoIme().equals(korisnickoIme) && inspektor.getLozinka().equals(lozinka)) {
                    inspektor = new PoreskiInspektor(inspektorID, imePrezime, korisnickoIme, lozinka);
                    return inspektor;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<PoreskiObveznik> vratiObveznike() {
        ArrayList<PoreskiObveznik> lista = new ArrayList<>();
        String upit = "select * from fizickolice";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int obveznikID = rs.getInt("ObveznikID");
                String jmbg = rs.getString("JMBG");
                String imePrezime = rs.getString("ImePrezime");
                FizickoLice fizickoLice = new FizickoLice(obveznikID, jmbg, imePrezime);
                lista.add(fizickoLice);
            }
            
            upit = "select * from pravnolice";
            s = konekcija.createStatement();
            rs = s.executeQuery(upit);
            while(rs.next()) {
                int obveznikID = rs.getInt("ObveznikID");
                String pib = rs.getString("PIB");
                String maticniBroj = rs.getString("MaticniBroj");
                String naziv = rs.getString("Naziv");
                PravnoLice pravnoLice = new PravnoLice(obveznikID, pib, maticniBroj, naziv);
                lista.add(pravnoLice);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<VrstaPoreza> vratiVrstePoreza() {
        ArrayList<VrstaPoreza> lista = new ArrayList<>();
        String upit = "select * from vrstaporeza";
        Statement s;
        try {
            s = konekcija.createStatement();
             ResultSet rs = s.executeQuery(upit);
             while(rs.next()) {
                 int vrstaID = rs.getInt("VrstaID");
                 String naziv = rs.getString("Naziv");
                 double procenat = rs.getDouble("ProcenatPoreza");
                 VrstaPoreza vrstaPoreza = new VrstaPoreza(vrstaID, naziv, procenat);
                 lista.add(vrstaPoreza);
             }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
       return lista;
    }

    public int vratiRedniBroj() {
        String upit = "select max(RB) as max from stavkaporeskeprijave";
        int rb = -1;
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            //System.out.println("RS "+rs);
            if(rs !=null && rs.next()) {
                rb = rs.getInt("max");
            }
            else {
                rb = 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ++rb;
    }

    public boolean sacuvajPoreskuPrijavu(PoreskaPrijava prijava) {
        String upit = "insert into poreskaprijava values(?,?,?,?)";
        boolean status = false;
        try {
            PreparedStatement ps = konekcija.prepareStatement(upit);
            ps.setInt(1, prijava.getPrijavaID());
            ps.setInt(2, prijava.getGodina());
            ps.setInt(3, prijava.getObveznik().getObveznikID());
            ps.setInt(4, prijava.getInspektor().getInspektorID());
            ps.executeUpdate();
            
            upit = "insert into stavkaporeskeprijave values(?,?,?,?,?,?)";
            ps = konekcija.prepareStatement(upit);
            for (StavkaPoreskePrijave stavka : prijava.getStavke()) {
                ps.setInt(1, prijava.getPrijavaID());
                ps.setInt(2, stavka.getRb());
                ps.setDate(3, new Date(stavka.getDatumPrometa().getTime()));
                ps.setDouble(4, stavka.getVrednost());
                ps.setString(5, stavka.getNapomena());
                ps.setInt(6, stavka.getVrsta().getVrstaID());
                ps.executeUpdate();
            }
            status = true;
        } catch (SQLException ex) {
            status = false;
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public int vratiPrijavaID() {
        String upit = "select max(PrijavaID) as max from poreskaprijava";
        int rb = -1;
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            //System.out.println("RS "+rs);
            if(rs !=null && rs.next()) {
                rb = rs.getInt("max");
            }
            else {
                rb = 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ++rb;
    }

    public ArrayList<PomocnaKlasa> vratiZaServer(String filter) {
        ArrayList<PomocnaKlasa> lista = new ArrayList<>();
        // SELECT  po.ObveznikID, pp.Godina, SUM(spp.Vrednost*vp.ProcenatPoreza) AS dug FROM poreskiobveznik po JOIN poreskaprijava pp ON po.ObveznikID=pp.ObveznikID JOIN stavkaporeskeprijave spp ON pp.PrijavaID=spp.PrijavaID JOIN vrstaporeza vp ON spp.VrstaID = vp.VrstaID GROUP BY po.ObveznikID
        String upit = "";
        if(filter.equals("fizicko")) {
            upit = "SELECT  pl.ObveznikID, pl.Naziv as naz, pp.Godina, SUM(spp.Vrednost*vp.ProcenatPoreza) AS Dug FROM pravnolice pl JOIN poreskaprijava pp ON pl.ObveznikID=pp.ObveznikID JOIN stavkaporeskeprijave spp ON pp.PrijavaID=spp.PrijavaID JOIN vrstaporeza vp ON spp.VrstaID = vp.VrstaID GROUP BY pl.ObveznikID ORDER BY dug DESC";
        } else  {
            upit = "SELECT  fl.ObveznikID, fl.ImePrezime, pp.Godina, SUM(spp.Vrednost*vp.ProcenatPoreza) AS dug FROM fizickolice fl JOIN poreskaprijava pp ON fl.ObveznikID=pp.ObveznikID JOIN stavkaporeskeprijave spp ON pp.PrijavaID=spp.PrijavaID JOIN vrstaporeza vp ON spp.VrstaID = vp.VrstaID GROUP BY fl.ObveznikID ORDER BY dug DESC";
        }
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int obveznikID = rs.getInt("ObveznikID");
                String naziv = rs.getString("naz");
                int godina = rs.getInt("Godina");
                double dug = rs.getDouble("Dug");
                PomocnaKlasa pk = new PomocnaKlasa(obveznikID, naziv, godina, dug);
                lista.add(pk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
