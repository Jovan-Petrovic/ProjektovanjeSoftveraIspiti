/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domen.StudijskiProgram;
import java.util.ArrayList;
import logika.Kontroler;
import model.ModelTabele;
import niti.NitOsvezavanje;
import pomocna.PodaciZaTabelu;
import server.PokreniServer;

/**
 *
 * @author KORISNIK
 */
public class ServerskaForma extends javax.swing.JFrame {

    /**
     * Creates new form ServerskaForma
     */
    public ServerskaForma() {
        initComponents();
        PokreniServer ps = new PokreniServer();
        ps.start();
        srediComboBox();
        //srediFormu();
        NitOsvezavanje no = new NitOsvezavanje(this);
        no.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcheckGodina = new javax.swing.JCheckBox();
        jcheckProgram = new javax.swing.JCheckBox();
        jtxtGodina = new javax.swing.JTextField();
        jcmbStudProgram = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblTabela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jcheckGodina.setText("Godina");

        jcheckProgram.setText("Program");

        jcmbStudProgram.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jtblTabela.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtblTabela);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcheckProgram)
                            .addComponent(jcheckGodina))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtGodina, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcmbStudProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcheckGodina)
                    .addComponent(jtxtGodina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcheckProgram)
                    .addComponent(jcmbStudProgram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerskaForma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox jcheckGodina;
    private javax.swing.JCheckBox jcheckProgram;
    private javax.swing.JComboBox jcmbStudProgram;
    private javax.swing.JTable jtblTabela;
    private javax.swing.JTextField jtxtGodina;
    // End of variables declaration//GEN-END:variables

    private void srediComboBox() {
        ArrayList<StudijskiProgram> lista = Kontroler.getInstanca().vratiPrograme();
        jcmbStudProgram.removeAllItems();
        for (StudijskiProgram studijskiProgram : lista) {
            jcmbStudProgram.addItem(studijskiProgram);
        }
    }

    public void srediFormu() {
        String whereUslov="";
        
        if(jcheckGodina.isSelected() && jcheckProgram.isSelected()) {
            StudijskiProgram sp = (StudijskiProgram) jcmbStudProgram.getSelectedItem();
            whereUslov = "where year(p.datumPrijave)="+Integer.parseInt(jtxtGodina.getText().trim())+" and p.studijskiProgramID="+sp.getStudijskiProgramID();
        } else {
            if(jcheckGodina.isSelected()) {
                whereUslov = "where year(p.datumPrijave)="+Integer.parseInt(jtxtGodina.getText().trim());
            } 
            if(jcheckProgram.isSelected()) {
                StudijskiProgram sp = (StudijskiProgram) jcmbStudProgram.getSelectedItem();
                whereUslov = "where p.studijskiProgramID="+sp.getStudijskiProgramID();
            }
        }
        
        ArrayList<PodaciZaTabelu> lista = Kontroler.getInstanca().vratiPodatke(whereUslov);
        
        ModelTabele mt = new ModelTabele(lista);
        jtblTabela.setModel(mt);
    }
}
