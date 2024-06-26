/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Beneficiario;

import daos.CuentaBancariaDAO;
import dtos.AbonoDTO;
import dtos.BeneficiarioDTO;
import dtos.CuentaBancariaDTO;
import dtos.PagoDTO;
import dtos.TipoPagoDTO;
import excepciones.NegocioException;
import interfaces.IConsultarAbonoBO;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import servicios.IConsultarEstadoPagos;
import servicios.IGestionarAbonos;
import servicios.IGestionarCuentasBancarias;
import servicios.IGestionarPagos;

/**
 *
 * @author filor
 */
public class CrearAbono extends javax.swing.JFrame {

    PagoDTO pagoDTO;
    BeneficiarioDTO beneficiarioDTO;
    IGestionarCuentasBancarias gestionarCuentasBancarias;
    IGestionarPagos gestionarPagos;
    IGestionarAbonos gestionarAbonos;
    IConsultarEstadoPagos consultarEstadoPagos;

    /**
     * Creates new form CrearAbono
     */
    public CrearAbono(BeneficiarioDTO beneficiarioDTO, PagoDTO pagoDTO, IGestionarCuentasBancarias gestionarCuentasBancarias, IGestionarPagos gestionarPagos, IGestionarAbonos gestionarAbonos, IConsultarEstadoPagos consultarEstadoPagos) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.pagoDTO = pagoDTO;
        this.gestionarCuentasBancarias = gestionarCuentasBancarias;
        this.gestionarPagos = gestionarPagos;
        this.gestionarAbonos = gestionarAbonos;
        this.consultarEstadoPagos = consultarEstadoPagos;
        this.beneficiarioDTO = beneficiarioDTO;
        this.metodosInciales();
    }

    private void metodosInciales() {
        this.llenarCampos();
        this.configurarMonto();
        this.agregarOpcionesMenu();
    }

    public void agregarOpcionesMenu() {

        JMenu menuPagos = new JMenu("Pagos");
        JMenuItem misPagos = new JMenuItem("Mis pagos");
        misPagos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pagos pagos = new Pagos(gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos, gestionarAbonos, beneficiarioDTO);
                pagos.setVisible(true);
                dispose();

            }
        });

        menuPagos.add(misPagos);

        JMenu menuAbonos = new JMenu("Abonos");
        JMenuItem misAbonos = new JMenuItem("Mis Abonos");
        misAbonos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Abonos abonos = new Abonos(gestionarAbonos, gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos, pagoDTO, beneficiarioDTO);
                abonos.setVisible(true);
                dispose();

            }
        });

        menuAbonos.add(misAbonos);

        JMenu menuCuentas = new JMenu("Cuentas");
        JMenuItem misCuentas = new JMenuItem("Mis cuentas");
        misCuentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cuentas cuentas = new Cuentas(gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos, gestionarAbonos, beneficiarioDTO);
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

        MenuBarAdmin.add(menuAbonos);
        MenuBarAdmin.add(menuPagos);
        MenuBarAdmin.add(menuCuentas);
        MenuBarAdmin.add(menuSalir);

    }

    private void llenarCampos() {
        try {
            CuentaBancariaDTO cuentaDePagoDTO = gestionarCuentasBancarias.consultarCuentaBancariaPorID(pagoDTO.getCuentaBancariaId());
            TipoPagoDTO tipoPagoDTO = gestionarPagos.consultarTipoPagoPorID(pagoDTO.getTipoPagoId());
            Integer parcialidadesActuales = Integer.valueOf(pagoDTO.getAbonoIds().size());
            Integer parcialidadesRestantes = tipoPagoDTO.getNumeroParcialidades() - parcialidadesActuales;

            lblCuenta.setText(cuentaDePagoDTO.getNumeroCuenta());
            lblNumeroRestantes.setText(parcialidadesRestantes.toString());
            lblTipoDePago.setText(tipoPagoDTO.getNombreTipo());
            lblNumeroParcialidades.setText(tipoPagoDTO.getNumeroParcialidades().toString());
        } catch (NegocioException ex) {
            Logger.getLogger(CrearAbono.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    private void configurarMonto() {
        SpinnerNumberModel numberModel = new SpinnerNumberModel(0.00, 0.00, Double.MAX_VALUE, 0.01);
        spinnerMonto.setModel(numberModel);

        // Configurar el editor del Spinner para mostrar dos decimales
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinnerMonto, "#,##0.00");
        spinnerMonto.setEditor(editor);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblTipoDePago = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblNumeroParcialidades = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblNumeroRestantes = new javax.swing.JLabel();
        lblCuenta = new javax.swing.JLabel();
        spinnerMonto = new javax.swing.JSpinner();
        MenuBarAdmin = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelMenu.setBackground(new java.awt.Color(0, 51, 102));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/LogoEsquina.jpg"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Crear Abono");

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1))
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 60));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Cuenta");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 72, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Tipo de Pago");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 141, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Ingrese el monto*");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, -1, -1));

        btnAceptar.setBackground(new java.awt.Color(0, 102, 153));
        btnAceptar.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 382, -1, -1));

        btnCancelar.setBackground(new java.awt.Color(0, 102, 153));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 382, -1, -1));

        lblTipoDePago.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTipoDePago.setText("tipoDePago");
        getContentPane().add(lblTipoDePago, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 169, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Numero de parcialidad:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 198, -1, -1));

        lblNumeroParcialidades.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNumeroParcialidades.setText("jLabel8");
        getContentPane().add(lblNumeroParcialidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 227, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Numero de parcialidades restantes:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 256, -1, -1));

        lblNumeroRestantes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNumeroRestantes.setText("jLabel9");
        getContentPane().add(lblNumeroRestantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 279, -1, -1));

        lblCuenta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCuenta.setText("cuenta");
        getContentPane().add(lblCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 106, 257, -1));
        getContentPane().add(spinnerMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 156, -1));
        setJMenuBar(MenuBarAdmin);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try {

            Number valorNumber = (Number) spinnerMonto.getValue();
            BigDecimal monto = BigDecimal.valueOf(valorNumber.doubleValue());

            if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(this, "Por favor, Ingrese un monto valido", "Monto Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            AbonoDTO abono = new AbonoDTO(monto, pagoDTO.getPagoId());
            System.out.println(abono);
            gestionarAbonos.agregarAbono(abono);
            JOptionPane.showMessageDialog(this, "Se ha agregado el abono correctamente", "Exito en el pago", JOptionPane.INFORMATION_MESSAGE);

            Abonos abonos = new Abonos(gestionarAbonos, gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos, pagoDTO, beneficiarioDTO);
            abonos.setVisible(true);
            this.dispose();

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar el abono", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Abonos abono = new Abonos(gestionarAbonos, gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos, pagoDTO, beneficiarioDTO);
        abono.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MenuBarAdmin;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblCuenta;
    private javax.swing.JLabel lblNumeroParcialidades;
    private javax.swing.JLabel lblNumeroRestantes;
    private javax.swing.JLabel lblTipoDePago;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JSpinner spinnerMonto;
    // End of variables declaration//GEN-END:variables
}
