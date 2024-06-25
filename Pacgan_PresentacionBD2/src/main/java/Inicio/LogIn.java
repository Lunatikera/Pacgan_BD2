/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Inicio;

import Beneficiario.Pagos;
import excepciones.NegocioException;
import insertadores.InsertarEstatusPago;
import insertadores.InsertarBeneficiario;
import interfaces.IIniciarSesionBO;
import interfaces.IinsertarBeneficiario;
import interfaces.IinsertarEstatusPago;
import java.awt.Color;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import negocio.IniciarSesionBO;
import servicios.IConsultarEstadoPagos;
import servicios.IGestionarAbonos;
import servicios.IGestionarBeneficiarios;
import servicios.IGestionarCuentasBancarias;
import servicios.IGestionarPagos;

/**
 *
 * @author jesus
 */
public class LogIn extends javax.swing.JFrame {

    IGestionarAbonos gestionarAbonos;
    IGestionarBeneficiarios gestionarBeneficiarios;
    IGestionarCuentasBancarias gestionarCuentasBancarias;
    IGestionarPagos gestionarPagos;
    IConsultarEstadoPagos consultarEstadoPagos;
    IIniciarSesionBO iniciarSesionBO;
    IinsertarBeneficiario insertarBeneficiarios;
    IinsertarEstatusPago insertPagoEstatus;

    public LogIn(IIniciarSesionBO iniciarSesionBO, IinsertarBeneficiario insertarBeneficiarios,IinsertarEstatusPago insertPagoEstatus, IGestionarAbonos gestionarAbonos, IGestionarBeneficiarios gestionarBeneficiarios,
            IGestionarCuentasBancarias gestionarCuentasBancarias, IGestionarPagos gestionarPagos, IConsultarEstadoPagos consultarEstadoPagos) {

        initComponents();
        this.gestionarAbonos = gestionarAbonos;
        this.gestionarBeneficiarios = gestionarBeneficiarios;
        this.gestionarCuentasBancarias = gestionarCuentasBancarias;
        this.gestionarPagos = gestionarPagos;
        this.consultarEstadoPagos = consultarEstadoPagos;
        this.iniciarSesionBO = iniciarSesionBO;
        this.insertarBeneficiarios=insertarBeneficiarios;
        this.insertPagoEstatus=insertPagoEstatus;
        personalizador();
    }

    public void personalizador() {
        Agrupador.setBackground(Color.decode("#142132"));
        checkboxVer.setOpaque(false);
        radModoAdmin.setOpaque(false);

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
        etiquetaUsuario = new javax.swing.JLabel();
        etiquetaContrasena = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnIniciarSesion = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        txtContrasena = new javax.swing.JPasswordField();
        checkboxVer = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        radModoAdmin = new javax.swing.JRadioButton();
        btnInsertsMasivos = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Agrupador.setBackground(new java.awt.Color(0, 51, 102));
        Agrupador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        etiquetaUsuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etiquetaUsuario.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaUsuario.setText("Usuario:");
        Agrupador.add(etiquetaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 270, -1, -1));

        etiquetaContrasena.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etiquetaContrasena.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaContrasena.setText("Contraseña:");
        Agrupador.add(etiquetaContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, -1, -1));

        txtUsuario.setBackground(new java.awt.Color(234, 234, 234));
        txtUsuario.setForeground(new java.awt.Color(51, 51, 51));
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        Agrupador.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, 180, 30));

        btnIniciarSesion.setText("Iniciar sesion");
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });
        Agrupador.add(btnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 440, 120, 40));

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        Agrupador.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 440, 120, 40));
        Agrupador.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 330, 170, 30));

        checkboxVer.setForeground(new java.awt.Color(255, 255, 255));
        checkboxVer.setText("Ver");
        checkboxVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxVerActionPerformed(evt);
            }
        });
        Agrupador.add(checkboxVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 340, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/LogoPacGan.jpg"))); // NOI18N
        Agrupador.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 270, 240));

        radModoAdmin.setForeground(new java.awt.Color(255, 255, 255));
        radModoAdmin.setText("Modo Administrador");
        Agrupador.add(radModoAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 380, -1, -1));

        btnInsertsMasivos.setText("Inserts masivos");
        btnInsertsMasivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertsMasivosActionPerformed(evt);
            }
        });
        Agrupador.add(btnInsertsMasivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, -1, -1));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Contraseña: contrasena123");
        Agrupador.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nota: puede ingresar con:");
        Agrupador.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("beneficiario usuario juanito123");
        Agrupador.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Agrupador, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Agrupador, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed

        String nombreUsuario = txtUsuario.getText(); // Suponiendo que tienes un campo de texto para el nombre de usuario
        String contraseña = String.valueOf(txtContrasena.getPassword()); // Suponiendo que tienes un campo de contraseña

        // Validar que se haya ingresado un nombre de usuario y una contraseña
        if (nombreUsuario.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa el nombre de usuario y la contraseña.", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si falta información
        }

        // Crear una instancia de IniciarSesionBO
        try {
            // Intentar iniciar sesión
            if (iniciarSesionBO.iniciarSesion(nombreUsuario, contraseña)) {
                // Inicio de sesión exitoso
                JOptionPane.showMessageDialog(this, "Inicio de sesion exitoso.", "Inicio de sesion", JOptionPane.INFORMATION_MESSAGE);

                Pagos pagos = new Pagos(gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos);
                pagos.setVisible(true);
                dispose();

            } else {
                // Credenciales incorrectas
                JOptionPane.showMessageDialog(this, "Nombre de usuario o contraseña incorrectos.", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NegocioException ex) {
            // Manejo de excepciones de negocio
            JOptionPane.showMessageDialog(this, "Error al intentar iniciar sesión.", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void checkboxVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxVerActionPerformed
        // TODO add your handling code here:

        if (checkboxVer.isSelected()) {
            txtContrasena.setEchoChar((char) 0);
        } else {
            txtContrasena.setEchoChar('*');
        }
    }//GEN-LAST:event_checkboxVerActionPerformed

    private void btnInsertsMasivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertsMasivosActionPerformed
        // TODO add your handling code here:
        try {

            insertarBeneficiarios.insertarBeneficiarios();
            insertPagoEstatus.insertarTiposDeEstatusPredeterminados();
            insertPagoEstatus.insertarTiposDePagoPredeterminados();

            JOptionPane.showMessageDialog(this, "Beneficiarios insertados exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al insertar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnInsertsMasivosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Agrupador;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JToggleButton btnInsertsMasivos;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox checkboxVer;
    private javax.swing.JLabel etiquetaContrasena;
    private javax.swing.JLabel etiquetaUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JRadioButton radModoAdmin;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
