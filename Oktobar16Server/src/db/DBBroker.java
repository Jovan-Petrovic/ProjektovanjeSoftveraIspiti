/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Fakultet;
import domen.Kandidat;
import domen.Prijava;
import domen.StudijskiProgram;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import pomocna.PodaciZaTabelu;
import transfer.ServerskiOdgovor;

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
            konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/oktobar2016","root","");
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

    public Kandidat vratiKandidata(Kandidat k) {
        Kandidat kan = null;
        String upit = "select * from kandidat where korisnickoIme='"+k.getKorisnickoIme()+"' and lozinka='"+k.getLozinka()+"'";
        Statement s;
        try {
            s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int kandidatID = rs.getInt("kandidatID");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String korisnickoIme = rs.getString("korisnickoIme");
                String lozinka = rs.getString("lozinka");
                kan = new Kandidat(kandidatID, ime, prezime, korisnickoIme, lozinka);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kan;
    }

    public ArrayList<Fakultet> vratiFakultete() {
        ArrayList<Fakultet> listaF = new ArrayList<>();
        String upit = "select * from fakultet";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int fakultetID = rs.getInt("fakultetID");
                String naziv = rs.getString("naziv");
                String adresa = rs.getString("adresa");
                String telefon = rs.getString("telefon");
                Fakultet f = new Fakultet(fakultetID, naziv, adresa, telefon);
                listaF.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaF;
    }

    public ArrayList<StudijskiProgram> vratiPrograme() {
        ArrayList<StudijskiProgram> listaSP = new ArrayList<>();
        String upit = "select * from studijskiprogram";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int spID = rs.getInt("studijskiProgramID");
                String naziv = rs.getString("naziv");
                StudijskiProgram sp = new StudijskiProgram(spID, naziv);
                listaSP.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaSP;
    }

    public ArrayList<Prijava> vratiPrijave() {
        ArrayList<Prijava> listaPrijava = new ArrayList<>();
        String upit = "select * from prijava p join fakultet f on p.fakultetID=f.fakultetID join kandidat k on p.kandidatID=k.kandidatID join studijskiprogram sp on p.studijskiProgramID=sp.studijskiProgramID";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                int kandidatID = rs.getInt("kandidatID");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String korisnickoIme = rs.getString("korisnickoIme");
                String lozinka = rs.getString("lozinka");
                Kandidat k = new Kandidat(kandidatID, ime, prezime, korisnickoIme, lozinka);
                
                int fakultetID = rs.getInt("fakultetID");
                String naziv = rs.getString("f.naziv");
                String telefon = rs.getString("telefon");
                String adresa = rs.getString("adresa");
                Fakultet f = new Fakultet(fakultetID, naziv, adresa, telefon);
                
                int studijskiProgramId = rs.getInt("studijskiProgramID");
                String nazivSP = rs.getString("sp.naziv");
                StudijskiProgram sp = new StudijskiProgram(studijskiProgramId, nazivSP);
                
                int prijavaId = rs.getInt("prijavaID");
                int godinaUpisa = rs.getInt("godinaUpisa");
                int godinaDiplomiranja = rs.getInt("godinaDiplomiranja");
                double prosecnaOcena = rs.getDouble("prosecnaOcena");
                Date datumPrijave = new Date(rs.getDate("datumPrijave").getTime());
                boolean oslobodjenPrijemnog = rs.getBoolean("oslobodjenPrijemnog");
                Prijava p = new Prijava(prijavaId, godinaUpisa, godinaDiplomiranja, prosecnaOcena, datumPrijave, oslobodjenPrijemnog, f, sp, k);
                listaPrijava.add(p);               
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPrijava;
    }

    public void sacuvajPrijavu(Prijava p) throws SQLException {
        String upit = "insert into prijava(godinaUpisa,godinaDiplomiranja,prosecnaOcena,datumPrijave,oslobodjenPrijemnog,fakultetID,studijskiProgramID,kandidatID) values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ps.setInt(1, p.getGodinaUpisa());
        ps.setInt(2, p.getGodinaDIplomiranja());
        ps.setDouble(3, p.getProsecnaOcena());
        ps.setDate(4, new java.sql.Date(p.getDatumPrijave().getTime()));
        ps.setBoolean(5, p.isOslobodjenPrijemnog());
        ps.setInt(6, p.getFakultet().getFakultetID());
        ps.setInt(7, p.getStudijskiProgram().getStudijskiProgramID());
        ps.setInt(8, p.getKandidat().getKandidatID());
        ps.executeUpdate();
    }

    public ArrayList<PodaciZaTabelu> vratiPodatke(String whereUslov) {
        ArrayList<PodaciZaTabelu> lista = new ArrayList<>();
        String upit = "select sp.naziv, year(p.datumPrijave) as godina, count(k.kandidatID) as broj, count(k.kandidatID)*10000 as prihod from prijava p join studijskiprogram sp on p.studijskiProgramID=sp.studijskiProgramID join kandidat k on p.kandidatID=k.kandidatID "+whereUslov+" GROUP BY sp.studijskiProgramID, year(p.datumPrijave) ORDER BY godina desc, broj desc";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while(rs.next()) {
                String sp = rs.getString("sp.naziv");
                int skolskaGodina = rs.getInt("godina");
                int brojKandidata = rs.getInt("broj");
                int prihod = rs.getInt("prihod");
                PodaciZaTabelu pzt = new PodaciZaTabelu(sp, skolskaGodina, brojKandidata, prihod);
                lista.add(pzt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
