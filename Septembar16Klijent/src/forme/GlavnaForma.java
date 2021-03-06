/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domen.Lek;
import domen.Lekar;
import domen.OsiguranoLice;
import domen.Recept;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import konstante.Operacije;
import logika.KomunikacijaSaServerom;
import modeli.ModelTabeleRecept;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author KORISNIK
 */
public class GlavnaForma extends javax.swing.JFrame {

    /**
     * Creates new form GlavnaForma
     */
    public GlavnaForma() {
        initComponents();
        srediFormu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jcmbLekari = new javax.swing.JComboBox();
        jcmbOsigLica = new javax.swing.JComboBox();
        jcmbLekovi = new javax.swing.JComboBox();
        jtxtKolicina = new javax.swing.JTextField();
        jtxtDatum = new javax.swing.JTextField();
        jbtnDodaj = new javax.swing.JButton();
        jbtnObrisi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblRecepti = new javax.swing.JTable();
        jbtnSacuvaj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Lekar");

        jLabel2.setText("Osigurano lice");

        jLabel3.setText("Lek");

        jLabel4.setText("Kolicina");

        jLabel5.setText("Datum (dd.MM.yyyy)");

        jcmbLekari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jcmbOsigLica.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jcmbLekovi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jbtnDodaj.setText("Dodaj recept");
        jbtnDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDodajActionPerformed(evt);
            }
        });

        jbtnObrisi.setText("Obrisi recept");
        jbtnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnObrisiActionPerformed(evt);
            }
        });

        jtblRecepti.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtblRecepti);

        jbtnSacuvaj.setText("Sacuvaj recepte");
        jbtnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSacuvajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcmbLekari, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcmbOsigLica, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcmbLekovi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtxtKolicina)
                            .addComponent(jtxtDatum)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtnDodaj)
                                .addGap(66, 66, 66)
                                .addComponent(jbtnObrisi))
                            .addComponent(jbtnSacuvaj))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcmbLekari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jcmbOsigLica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcmbLekovi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtxtKolicina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtxtDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnDodaj)
                    .addComponent(jbtnObrisi))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnSacuvaj)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDodajActionPerformed
        if(jtxtDatum.getText().isEmpty() || jtxtKolicina.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena");
            return;
        }
        
        Lekar lekar = (Lekar) jcmbLekari.getSelectedItem();
        OsiguranoLice osiguranoLice = (OsiguranoLice) jcmbOsigLica.getSelectedItem();
        Lek lek = (Lek) jcmbLekovi.getSelectedItem();
        
        int kolicina = Integer.parseInt(jtxtKolicina.getText().trim());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date datum = null;
        try {
            datum = sdf.parse(jtxtDatum.getText().trim());
        } catch (ParseException ex) {
            Logger.getLogger(GlavnaForma.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int participacija = kolicina*50;
        double suma = 0d;
        if(lek.getLista().equalsIgnoreCase("a")) {
            suma=0.5*lek.getCena()*kolicina;
        }
        if(lek.getLista().equalsIgnoreCase("b")) {
            suma+=0.1*lek.getCena()*kolicina;
        }
        double ukupanIznos = suma + participacija;
        
        ModelTabeleRecept mtr = (ModelTabeleRecept) jtblRecepti.getModel();
        //int receptID = mtr.vratiID();
        Recept recept = new Recept(-1, datum, kolicina, ukupanIznos, lekar, osiguranoLice, lek);
        mtr.dodajRecept(recept);
    }//GEN-LAST:event_jbtnDodajActionPerformed

    private void jbtnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnObrisiActionPerformed
        int red = jtblRecepti.getSelectedRow();
        if(red == -1) {
            JOptionPane.showMessageDialog(this, "Morate selektovati red u tabeli");
            return;
        }
        
        ModelTabeleRecept mtr = (ModelTabeleRecept) jtblRecepti.getModel();
        mtr.obrisiRecept(red);
    }//GEN-LAST:event_jbtnObrisiActionPerformed

    private void jbtnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSacuvajActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        ModelTabeleRecept mtr = (ModelTabeleRecept) jtblRecepti.getModel();
        ArrayList<Recept> lista = mtr.getRecepti();
        Date danasnjiDatum = new Date();
        for (Recept recept : lista) {
            if(danasnjiDatum.getYear()!=recept.getDatum().getYear() || danasnjiDatum.getMonth()!=recept.getDatum().getMonth() || danasnjiDatum.getDay()!=recept.getDatum().getDay()) {
                JOptionPane.showMessageDialog(this, "Datumi moraju biti danasnji");
                return;
            }
            if(recept.getKolicina() <= 0) {
                JOptionPane.showMessageDialog(this, "Kolicine moraju biti vece od nule");
                return;
            }
        }
        for(int i=0; i<lista.size();i++) {
            Recept r1 = lista.get(i);
            int brojac=0;
            for(int j=i+1; j<lista.size(); j++) {
                Recept r2 = lista.get(j);
                if(r1.getLekar().equals(r2.getLekar())) {
                    brojac++;
                }
            }
            if(brojac>=1000) {
                JOptionPane.showMessageDialog(this, "Jedan lekar ne moze uneti preko 1000 recepata na dan");
                return;
            }
        }
        
        KlijentskiZahtev kz= new KlijentskiZahtev();
        kz.setOperacija(Operacije.SACUVAJ);
        kz.setParametar(lista);
        KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().primiOdgovor();
        
        JOptionPane.showMessageDialog(this, so.getPoruka());
        boolean sacuvano = (boolean) so.getOdgovor();
        if(sacuvano) {
            mtr.isprazniTabelu();
        }
    }//GEN-LAST:event_jbtnSacuvajActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GlavnaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GlavnaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GlavnaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GlavnaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GlavnaForma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnDodaj;
    private javax.swing.JButton jbtnObrisi;
    private javax.swing.JButton jbtnSacuvaj;
    private javax.swing.JComboBox jcmbLekari;
    private javax.swing.JComboBox jcmbLekovi;
    private javax.swing.JComboBox jcmbOsigLica;
    private javax.swing.JTable jtblRecepti;
    private javax.swing.JTextField jtxtDatum;
    private javax.swing.JTextField jtxtKolicina;
    // End of variables declaration//GEN-END:variables

    private void srediFormu() {
        srediComboLekari();
        srediComboosigLica();
        srediComboLekovi();
        
        ArrayList<Recept> lista = new ArrayList<>();
        ModelTabeleRecept mtr = new ModelTabeleRecept(lista);
        jtblRecepti.setModel(mtr);
    }

    private void srediComboLekari() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_LEKARE);
        KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().primiOdgovor();
        ArrayList<Lekar> lista = (ArrayList<Lekar>) so.getOdgovor();
        
        jcmbLekari.removeAllItems();
        for (Lekar lekar : lista) {
            jcmbLekari.addItem(lekar);
        }
    }

    private void srediComboosigLica() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_OSIGURANA_LICA);
        KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().primiOdgovor();
        ArrayList<OsiguranoLice> lista = (ArrayList<OsiguranoLice>) so.getOdgovor();
        
        jcmbOsigLica.removeAllItems();
        for (OsiguranoLice ol : lista) {
            jcmbOsigLica.addItem(ol);
        }
    }

    private void srediComboLekovi() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_LEKOVE);
        KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().primiOdgovor();
        ArrayList<Lek> lista = (ArrayList<Lek>) so.getOdgovor();
        
        jcmbLekovi.removeAllItems();
        for (Lek l : lista) {
            jcmbLekovi.addItem(l);
        }
    }
}
