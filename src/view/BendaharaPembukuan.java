/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import controller.Controller;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import model.Transaksi;

/**
 *
 * @author user
 */
public class BendaharaPembukuan extends javax.swing.JPanel {

    private JScrollPane contentScrollPane;
    /**
     * Creates new form BendaharaPembukuan
     */
    public BendaharaPembukuan() {
        initComponents();
    }

    BendaharaPembukuan(JScrollPane contentScrollPane) {
        this.contentScrollPane = contentScrollPane;
        initComponents();
        loadTableData();
    }
    
    private void loadTableData() {
        Controller controller = new Controller();
        List<Transaksi> transaksiList = controller.getAllTransaksi();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Transaksi");
        model.addColumn("ID User");
        model.addColumn("Tanggal");
        model.addColumn("Pemasukan");
        model.addColumn("Pengeluaran");
        model.addColumn("Saldo");
        model.addColumn("Keterangan");

        for (Transaksi transaksi : transaksiList) {
            model.addRow(new Object[] {
                transaksi.getIdTransaksi(),
                transaksi.getIdUser(),
                transaksi.getTimestamp(),
                transaksi.getPemasukan(),
                transaksi.getPengeluaran(),
                transaksi.getSaldo(),
                transaksi.getKeterangan()
            });
        }

        jTable1.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        downloadButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 204, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No Transaksi", "Waktu", "ID User", "Pemasukan", "Pengeluaran", "Saldo", "Keterangan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 6, 550, -1));

        downloadButton.setText("Download");
        downloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadButtonActionPerformed(evt);
            }
        });
        add(downloadButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(585, 77, -1, -1));

        backButton.setText("Kembali");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(585, 118, 90, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void downloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadButtonActionPerformed
        // TODO add your handling code here:
        Controller controller = new Controller();
        String filePath = "pembukuan.csv";
        controller.exportPembukuanToCSV(filePath);
    }//GEN-LAST:event_downloadButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        contentScrollPane.setViewportView(new BendaharaView(contentScrollPane));
    }//GEN-LAST:event_backButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton downloadButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}