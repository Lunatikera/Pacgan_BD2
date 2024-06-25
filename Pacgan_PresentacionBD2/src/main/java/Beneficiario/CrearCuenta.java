/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Beneficiario;

import convertidores.ConvertidorCuentaBancaria;
import daos.BeneficiarioDAO;
import dtos.CuentaBancariaDTO;
import excepciones.NegocioException;
import interfaces.IAgregarCuentaBancariaBO;
import interfaces.IBeneficiarioDAO;
import interfaces.IConsultarBeneficiarioBO;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import negocio.AgregarCuentaBancariaBO;
import negocio.ConsultarBeneficiarioBO;

/**
 *
 * @author jesus
 */
public class CrearCuenta extends javax.swing.JFrame {

    IAgregarCuentaBancariaBO agregarCuenta = new AgregarCuentaBancariaBO();
    IBeneficiarioDAO bene = new BeneficiarioDAO();
    IConsultarBeneficiarioBO beneficiario = new ConsultarBeneficiarioBO(bene);
    ConvertidorCuentaBancaria conv = new ConvertidorCuentaBancaria(bene);

    /**
     * Creates new form CrearCuenta
     */
    public CrearCuenta() {
        initComponents();
        personalizador();
    }

    public void personalizador() {
        Agrupador.setBackground(Color.decode("#142132"));
    }

    public void creacionCuenta() {

        String numeroCuenta = txtNumeroCuenta.getText();
        String clabe = txtClabe.getText();
        String nombreBanco = txtBanco.getText();

        try {
            CuentaBancariaDTO cuenta = new CuentaBancariaDTO();
            cuenta.setNumeroCuenta(numeroCuenta);
            cuenta.setClabe(clabe);
            cuenta.setNombreBanco(nombreBanco);
            cuenta.setEstaEliminada(false);
            cuenta.setBeneficiarioId(Long.valueOf("1"));
            List<Long> lista = new ArrayList<>();
            cuenta.setPagoIds(lista);
            agregarCuenta.agregarCuentaBancaria(cuenta);
            JOptionPane.showMessageDialog(this, "Cuenta registrada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            Cuentas cuentas = new Cuentas();

            cuentas.setVisible(true);
            dispose();
        } catch (NegocioException ex) {
            Logger.getLogger(CrearCuenta.class.getName()).log(Level.SEVERE, null, ex);

            txtNumeroCuenta.setText("");
            txtClabe.setText("");
            txtBanco.setText("");
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
        btnCancelar3 = new javax.swing.JButton();
        btnCrear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNumeroCuenta = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtClabe = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtBanco = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Agrupador.setBackground(new java.awt.Color(0, 51, 102));
        Agrupador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCancelar3.setText("Cancelar");
        btnCancelar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar3ActionPerformed(evt);
            }
        });
        Agrupador.add(btnCancelar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 490, 140, 40));

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        Agrupador.add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 490, 130, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Crear Cuenta");
        Agrupador.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 310, 50));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nomre Nombre Nombre Nombre");
        Agrupador.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 380, 50));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Clabe*");
        Agrupador.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 160, 30));

        txtNumeroCuenta.setBackground(new java.awt.Color(242, 242, 242));
        Agrupador.add(txtNumeroCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 390, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Numero de cuenta");
        Agrupador.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 160, 30));
        Agrupador.add(txtClabe, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 390, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/PacGanCrear.jpg"))); // NOI18N
        Agrupador.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 170, 170));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Banco*");
        Agrupador.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 370, 210, 30));
        Agrupador.add(txtBanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 400, 390, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Beneficiario:");
        Agrupador.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 150, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Agrupador, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Agrupador, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar3ActionPerformed
        Cuentas Cuentas = new Cuentas();

        Cuentas.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelar3ActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed

        creacionCuenta();
    }//GEN-LAST:event_btnCrearActionPerformed

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
            java.util.logging.Logger.getLogger(CrearCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearCuenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Agrupador;
    private javax.swing.JButton btnCancelar3;
    private javax.swing.JButton btnCrear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField txtBanco;
    private javax.swing.JPasswordField txtClabe;
    private javax.swing.JTextField txtNumeroCuenta;
    // End of variables declaration//GEN-END:variables
}
