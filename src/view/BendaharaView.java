/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import controller.Controller;
import javax.swing.JScrollPane;

/**
 *
 * @author user
 */
public class BendaharaView extends javax.swing.JPanel {

    private JScrollPane contentScrollPane;
    private Controller controller;
    
    /**
     * Creates new form BendaharaView
     * @param contentScrollPane
     */
    public BendaharaView() {
        initComponents();
    }
    
    BendaharaView(JScrollPane contentScrollPane) {
        this.contentScrollPane = contentScrollPane;
        initComponents();
        this.controller = new Controller();
        setSaldoTerakhir();
    }
    
    private void setSaldoTerakhir() {
        double saldoTerakhir = controller.getSaldoTerakhir();
        saldoTextField.setText(String.valueOf(saldoTerakhir));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        saldoTextField = new javax.swing.JTextField();
        pencatatanButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pembukuanButton = new javax.swing.JButton();
        mahasiswaButton = new javax.swing.JButton();
        pembayaranButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 204, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        saldoTextField.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        saldoTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        saldoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saldoTextFieldActionPerformed(evt);
            }
        });
        add(saldoTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 292, 56));

        pencatatanButton.setText("Catat Transaksi");
        pencatatanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pencatatanButtonActionPerformed(evt);
            }
        });
        add(pencatatanButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 137, -1));

        jLabel1.setText("Jumlah saldo saat ini :");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, -1, -1));

        pembukuanButton.setText("Pembukuan");
        pembukuanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pembukuanButtonActionPerformed(evt);
            }
        });
        add(pembukuanButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, 137, -1));

        mahasiswaButton.setText("Mahasiswa");
        mahasiswaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mahasiswaButtonActionPerformed(evt);
            }
        });
        add(mahasiswaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 137, -1));

        pembayaranButton.setText("Pembayaran Kas");
        pembayaranButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pembayaranButtonActionPerformed(evt);
            }
        });
        add(pembayaranButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, 143, -1));

        logoutButton.setText("Keluar");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        add(logoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 400, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/Screenshot (22).png"))); // NOI18N
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 270, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void saldoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saldoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saldoTextFieldActionPerformed

    private void pembukuanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pembukuanButtonActionPerformed
        // TODO add your handling code here:
        contentScrollPane.setViewportView(new BendaharaPembukuan(contentScrollPane));
    }//GEN-LAST:event_pembukuanButtonActionPerformed

    private void pencatatanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pencatatanButtonActionPerformed
        // TODO add your handling code here:
        contentScrollPane.setViewportView(new Pencatatan(contentScrollPane));
    }//GEN-LAST:event_pencatatanButtonActionPerformed

    private void mahasiswaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mahasiswaButtonActionPerformed
        // TODO add your handling code here:
        contentScrollPane.setViewportView(new TabelMahasiswa(contentScrollPane));
    }//GEN-LAST:event_mahasiswaButtonActionPerformed

    private void pembayaranButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pembayaranButtonActionPerformed
        // TODO add your handling code here:
        contentScrollPane.setViewportView(new Pembayaran(contentScrollPane));
    }//GEN-LAST:event_pembayaranButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        // TODO add your handling code here:
        contentScrollPane.setViewportView(new Login(contentScrollPane));
    }//GEN-LAST:event_logoutButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton mahasiswaButton;
    private javax.swing.JButton pembayaranButton;
    private javax.swing.JButton pembukuanButton;
    private javax.swing.JButton pencatatanButton;
    private javax.swing.JTextField saldoTextField;
    // End of variables declaration//GEN-END:variables
}
