/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Beneficiario;

import convertidores.ConvertidorCuentaBancaria;
import daos.BeneficiarioDAO;
import daos.CuentaBancariaDAO;
import dtos.BeneficiarioDTO;
import dtos.CuentaBancariaDTO;
import excepciones.NegocioException;
import interfaces.IAgregarCuentaBancariaBO;
import interfaces.IBeneficiarioDAO;
import interfaces.IConsultarBeneficiarioBO;
import interfaces.IConsultarCuentaBancariaBO;
import interfaces.ICuentaBancariaDAO;
import interfaces.IEditarCuentaBancariaBO;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import negocio.AgregarCuentaBancariaBO;
import negocio.ConsultarBeneficiarioBO;
import negocio.ConsultarCuentaBancariaBO;
import negocio.EditarCuentaBancariaBO;
import servicios.IConsultarEstadoPagos;
import servicios.IGestionarAbonos;
import servicios.IGestionarCuentasBancarias;
import servicios.IGestionarPagos;

/**
 *
 * @author jesus
 */
public class ModificarCuenta extends javax.swing.JFrame {

    IGestionarCuentasBancarias gestionarCuentasBancarias;
    IGestionarPagos gestionarPagos;
    IConsultarEstadoPagos consultarEstadoPagos;
    IGestionarAbonos gestionarAbonos;
    BeneficiarioDTO beneficiario;
    CuentaBancariaDTO cuentaBancaria;

    /**
     * Creates new form ModificarCuenta
     */
    public ModificarCuenta(IGestionarCuentasBancarias gestionarCuentasBancarias, IGestionarPagos gestionarPagos, IConsultarEstadoPagos consultarEstadoPagos, IGestionarAbonos gestionarAbonos,
            BeneficiarioDTO beneficiario, CuentaBancariaDTO cuentaBancaria) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.gestionarCuentasBancarias = gestionarCuentasBancarias;
        this.gestionarPagos = gestionarPagos;
        this.consultarEstadoPagos = consultarEstadoPagos;
        this.gestionarAbonos = gestionarAbonos;
        this.beneficiario = beneficiario;
        this.cuentaBancaria = cuentaBancaria;

        personalizador();
        agregarOpcionesMenu();

        lblNombre.setText(beneficiario.getNombreUsuario());
        this.txtNumeroCuenta.setText(cuentaBancaria.getNumeroCuenta());
        this.txtBanco.setText(cuentaBancaria.getNombreBanco());
        this.txtClabe.setText(cuentaBancaria.getNumeroCuenta());
    }

    public void personalizador() {
        Agrupador.setBackground(Color.decode("#142132"));
    }

    public void agregarOpcionesMenu() {

        JMenu menuPagos = new JMenu("Pagos");
        JMenuItem misPagos = new JMenuItem("Mis pagos");
        misPagos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Pagos pagos = new Pagos(gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos, gestionarAbonos, beneficiario);

                pagos.setVisible(true);
                dispose();

            }
        });

        menuPagos.add(misPagos);

        JMenu menuCuentas = new JMenu("Cuentas");
        JMenuItem misCuentas = new JMenuItem("Mis cuentas");
        misCuentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cuentas cuentas = new Cuentas(gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos, gestionarAbonos, beneficiario);
                cuentas.setVisible(true);
                dispose();

            }
        });

        menuCuentas.add(misCuentas);

        JMenu menuSalir = new JMenu("Salir");
        JMenuItem salir = new JMenuItem("Salir");
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(
                        null,
                        "¿Desea Continuar a Cerrar Sesion?",
                        "Cerrar Sesion",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                // Verificar la respuesta del usuario
                if (response == JOptionPane.YES_OPTION) {
                    for (Window window : Window.getWindows()) {
                        window.dispose();
                        System.exit(0);
                    }

                }
            }
        });

        menuSalir.add(salir);

        MenuBarAdmin.add(menuPagos);
        MenuBarAdmin.add(menuCuentas);
        MenuBarAdmin.add(menuSalir);

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Agrupador = new javax.swing.JPanel();
        btnCancelar3 = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNumeroCuenta = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtClabe = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtBanco = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        MenuBarAdmin = new javax.swing.JMenuBar();

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

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        Agrupador.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 490, 130, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Modificar Cuenta");
        Agrupador.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 400, 50));

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("Nomre Nombre Nombre Nombre");
        Agrupador.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 380, 50));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Clabe*");
        Agrupador.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 160, 30));

        txtNumeroCuenta.setBackground(new java.awt.Color(242, 242, 242));
        Agrupador.add(txtNumeroCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 390, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Numero de cuenta");
        Agrupador.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 210, 30));
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

        setJMenuBar(MenuBarAdmin);

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
        Cuentas Cuentas = new Cuentas(gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos, gestionarAbonos, beneficiario);

        Cuentas.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelar3ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        if (txtNumeroCuenta.getText().equals("")
                || txtClabe.getText().equals("")
                || txtBanco.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Favor de llenar los campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String numeroCuenta = txtNumeroCuenta.getText();
        String clabe = txtClabe.getText();
        String nombreBanco = txtBanco.getText();

        try {

            CuentaBancariaDTO cuenta = new CuentaBancariaDTO();
            cuenta.setCuentaBancariaId(Long.valueOf(this.cuentaBancaria.getCuentaBancariaId()));
            cuenta.setNumeroCuenta(numeroCuenta);
            cuenta.setClabe(clabe);
            cuenta.setNombreBanco(nombreBanco);
            cuenta.setEstaEliminada(false);
            cuenta.setBeneficiarioId(beneficiario.getBeneficiarioId());

            gestionarCuentasBancarias.editarCuentaBancaria(cuenta);
            JOptionPane.showMessageDialog(this, "Cuenta editada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            Cuentas cuentas = new Cuentas(gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos, gestionarAbonos, beneficiario);
            cuentas.setVisible(true);
            this.dispose();
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "Error al editar Cuenta", "Error", JOptionPane.ERROR_MESSAGE);

            txtNumeroCuenta.setText("");
            txtClabe.setText("");
            txtBanco.setText("");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Agrupador;
    private javax.swing.JMenuBar MenuBarAdmin;
    private javax.swing.JButton btnCancelar3;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JPasswordField txtBanco;
    private javax.swing.JPasswordField txtClabe;
    private javax.swing.JTextField txtNumeroCuenta;
    // End of variables declaration//GEN-END:variables
}
