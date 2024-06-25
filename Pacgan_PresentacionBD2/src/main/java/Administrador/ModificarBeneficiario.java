/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Administrador;

import dtos.BeneficiarioDTO;
import interfaces.IEditarBeneficiarioBO;
import java.awt.Color;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import negocio.EditarBeneficiarioBO;

/**
 *
 * @author jesus
 */
public class ModificarBeneficiario extends javax.swing.JFrame {

    IEditarBeneficiarioBO editarBeneficiarioBO = new EditarBeneficiarioBO();

    /**
     * Creates new form ModificarBeneficiario
     */
    public ModificarBeneficiario() {
        initComponents();
        personalizador();
    }

    public void personalizador() {
        Agrupador.setBackground(Color.decode("#142132"));
        checkBoxVer.setOpaque(false);

    }

    public void modificarBeneficiario() {

        String claveContrato = txtClaveContrato.getText();
        String nombre = txtNombre.getText();
        String aPaterno = txtAPaterno.getText();
        String aMaterno = txtAMaterno.getText();
        String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());
        String contrasenaRepetida = new String(txtContrasena1.getPassword());

        if (!contrasena.equals(contrasenaRepetida)) {
            // Mostrar mensaje de error
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            BeneficiarioDTO beneficiario = new BeneficiarioDTO();
            beneficiario.setClaveContrato(claveContrato);
            beneficiario.setNombre(nombre);
            beneficiario.setApellidoPA(aPaterno);
            beneficiario.setApellidoMA(aMaterno);
            beneficiario.setNombreUsuario(usuario);
            beneficiario.setContraseña(contrasena);

            editarBeneficiarioBO.editarBeneficiario(beneficiario);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Beneficiario modificado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar campos del formulario
            txtClaveContrato.setText("");
            txtNombre.setText("");
            txtAPaterno.setText("");
            txtAMaterno.setText("");
            txtUsuario.setText("");
            txtContrasena.setText("");
            txtContrasena1.setText("");

        } catch (Exception e) {
            // Mostrar mensaje de error
            System.out.println("ERROR:" + e);
            JOptionPane.showMessageDialog(this, "Error al modificar el beneficiario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Agrupador = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtClaveContrato = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtAPaterno = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        checkBoxVer = new javax.swing.JCheckBox();
        txtAMaterno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtContrasena1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Agrupador.setBackground(new java.awt.Color(0, 51, 102));
        Agrupador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        Agrupador.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 490, 140, 40));

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setForeground(new java.awt.Color(0, 0, 0));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        Agrupador.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 490, 120, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Modificar Beneficiario");
        Agrupador.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 400, 50));

        txtClaveContrato.setBackground(new java.awt.Color(242, 242, 242));
        txtClaveContrato.setForeground(new java.awt.Color(0, 0, 0));
        Agrupador.add(txtClaveContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, 240, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellido Materno");
        Agrupador.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 160, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre:*");
        Agrupador.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 90, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Contraseña*");
        Agrupador.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 160, 30));

        txtUsuario.setBackground(new java.awt.Color(242, 242, 242));
        txtUsuario.setForeground(new java.awt.Color(0, 0, 0));
        Agrupador.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 390, 30));

        txtAPaterno.setBackground(new java.awt.Color(242, 242, 242));
        txtAPaterno.setForeground(new java.awt.Color(0, 0, 0));
        Agrupador.add(txtAPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 180, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Apellido Paterno*");
        Agrupador.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 160, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Clave Contrato*");
        Agrupador.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 160, 30));

        txtNombre.setBackground(new java.awt.Color(242, 242, 242));
        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        Agrupador.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 390, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Usuario*");
        Agrupador.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 160, 30));

        txtContrasena.setBackground(new java.awt.Color(255, 255, 255));
        txtContrasena.setForeground(new java.awt.Color(0, 0, 0));
        Agrupador.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, 390, 30));

        checkBoxVer.setForeground(new java.awt.Color(255, 255, 255));
        checkBoxVer.setText("Ver");
        checkBoxVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxVerActionPerformed(evt);
            }
        });
        Agrupador.add(checkBoxVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 390, -1, -1));

        txtAMaterno.setBackground(new java.awt.Color(242, 242, 242));
        txtAMaterno.setForeground(new java.awt.Color(0, 0, 0));
        Agrupador.add(txtAMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, 180, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/PacGanCrear.jpg"))); // NOI18N
        Agrupador.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 170, 170));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Confirmar Contraseña*");
        Agrupador.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 410, 210, 30));

        txtContrasena1.setBackground(new java.awt.Color(255, 255, 255));
        txtContrasena1.setForeground(new java.awt.Color(0, 0, 0));
        Agrupador.add(txtContrasena1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 440, 390, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Agrupador, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Agrupador, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

//        BeneficiariosAdmin benef = new BeneficiariosAdmin();
        //      benef.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        modificarBeneficiario();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void checkBoxVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxVerActionPerformed
        // TODO add your handling code here:

        if (checkBoxVer.isSelected()) {
            txtContrasena.setEchoChar((char) 0);
            txtContrasena1.setEchoChar((char) 0);

        } else {
            txtContrasena.setEchoChar('*');
            txtContrasena1.setEchoChar('*');

        }
    }//GEN-LAST:event_checkBoxVerActionPerformed

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
            java.util.logging.Logger.getLogger(ModificarBeneficiario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarBeneficiario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarBeneficiario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarBeneficiario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModificarBeneficiario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Agrupador;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JCheckBox checkBoxVer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtAMaterno;
    private javax.swing.JTextField txtAPaterno;
    private javax.swing.JTextField txtClaveContrato;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JPasswordField txtContrasena1;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
