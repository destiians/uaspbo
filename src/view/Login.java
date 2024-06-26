/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import controller.Controller;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author user
 */
public class Login extends javax.swing.JPanel {

    private JScrollPane contentScrollPane;

    /**
     * Creates new form Login
     */
    public Login(JScrollPane contentScrollPane) {
        this.contentScrollPane = contentScrollPane;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginButton = new javax.swing.JButton();
        usernameTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 204, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, -1, -1));
        add(usernameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 132, -1));

        jLabel1.setText("Username :");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, -1, -1));

        jLabel2.setText("Password :");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, -1, -1));
        add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 130, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/Screenshot (22).png"))); // NOI18N
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 270, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        String username = usernameTextField.getText();
        String password = new String(passwordField.getPassword());

        Controller user = new Controller();
        String userId = user.authenticate(username, password);
        if (userId != null) {
            // Redirect ke BendaharaView atau MahasiswaView berdasarkan id
            if (userId.equals("000")) {
                contentScrollPane.setViewportView(new BendaharaView(contentScrollPane));
            } else {
                contentScrollPane.setViewportView(new MahasiswaView(contentScrollPane, userId));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_loginButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
