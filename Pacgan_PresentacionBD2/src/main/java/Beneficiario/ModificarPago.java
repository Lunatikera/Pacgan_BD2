/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Beneficiario;

import dtos.BeneficiarioDTO;
import dtos.CuentaBancariaDTO;
import dtos.PagoDTO;
import dtos.TipoPagoDTO;
import excepciones.NegocioException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import servicios.IConsultarEstadoPagos;
import servicios.IGestionarAbonos;
import servicios.IGestionarCuentasBancarias;
import servicios.IGestionarPagos;

/**
 *
 * @author filor
 */
public class ModificarPago extends javax.swing.JFrame {

    IGestionarPagos gestionarPagos;
    IGestionarCuentasBancarias gestionarCuentasBancarias;
    IConsultarEstadoPagos consultarEstadoPagos;
    IGestionarAbonos gestionarAbonos;
    List<CuentaBancariaDTO> listaCuentas;
    List<TipoPagoDTO> listaTipoPagos;
    PagoDTO pagoDTO;
    BeneficiarioDTO beneficiario;
    Long id = 1L;

    public ModificarPago(IGestionarPagos gestionarPagos, IGestionarCuentasBancarias gestionarCuentasBancarias, IConsultarEstadoPagos consultarEstadoPagos, IGestionarAbonos gestionarAbonos, PagoDTO pagoDTO, BeneficiarioDTO beneficiario
    ) {
        initComponents();
        this.gestionarPagos = gestionarPagos;
        this.gestionarCuentasBancarias = gestionarCuentasBancarias;
        this.consultarEstadoPagos = consultarEstadoPagos;
        this.beneficiario = beneficiario;
        this.gestionarAbonos = gestionarAbonos;
        this.pagoDTO = pagoDTO;
        personalizador();
        agregarOpcionesMenu();
        metodosIniciales();
    }

    public void metodosIniciales() {
        llenarComboBoxCuenta(id);
        llenarComboBoxTipoPago();
        configurarMonto();
        llenarInformacion();
    }

    public void llenarInformacion() {
        try {
            TipoPagoDTO tipoPagoDTO = gestionarPagos.consultarTipoPagoPorID(pagoDTO.getTipoPagoId());
            CuentaBancariaDTO cuentaBancariaDTO = gestionarCuentasBancarias.consultarCuentaBancariaPorID(pagoDTO.getCuentaBancariaId());

            // Seleccionar tipo de pago en el combobox
            seleccionarItemEnComboBox(cbxTiposPago1, tipoPagoDTO);

            // Seleccionar cuenta bancaria en el combobox
            seleccionarItemEnComboBox(cbxCuentas1, cuentaBancariaDTO);

            spinnerMonto.setValue(pagoDTO.getMonto());

        } catch (NegocioException ex) {
            Logger.getLogger(ModificarPago.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void seleccionarItemEnComboBox(JComboBox<?> comboBox, Object itemSeleccionado) {
        ComboBoxModel<?> model = comboBox.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            Object item = model.getElementAt(i);
            if (item.equals(itemSeleccionado)) {
                comboBox.setSelectedIndex(i);
                return;
            }
        }
    }

    public void personalizador() {
        panelMenu.setBackground(Color.decode("#142132"));
        btnCancelar.setBackground(Color.decode("#142132"));
        btnEditar.setBackground(Color.decode("#142132"));
    }

    public void agregarOpcionesMenu() {

        JMenu menuPagos = new JMenu("Pagos");
        JMenuItem misPagos = new JMenuItem("Mis pagos");
        misPagos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Pagos Pagos = new Pagos(gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos, gestionarAbonos, beneficiario);

                Pagos.setVisible(true);
                dispose();

            }
        });

        menuPagos.add(misPagos);

        JMenu menuCuentas = new JMenu("Cuentas");
        JMenuItem misCuentas = new JMenuItem("Mis cuentas");
        misCuentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open your frame here
//                Cuentas Cuentas = new Cuentas();
//                Cuentas.setVisible(true);
                dispose();

            }
        });

        menuCuentas.add(misCuentas);

        JMenu menuSalir = new JMenu("Salir");
        JMenuItem salir = new JMenuItem("Salir");
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });

        menuSalir.add(salir);
        MenuBarAdmin.add(menuSalir);

        MenuBarAdmin.add(menuPagos);
        MenuBarAdmin.add(menuCuentas);

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Agrupador = new javax.swing.JPanel();
        panelMenu = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        cbxCuentas1 = new javax.swing.JComboBox<>();
        jLabel59 = new javax.swing.JLabel();
        cbxTiposPago1 = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        spinnerMonto = new javax.swing.JSpinner();
        MenuBarAdmin = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Agrupador.setBackground(new java.awt.Color(255, 255, 255));
        Agrupador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelMenu.setBackground(new java.awt.Color(0, 51, 102));

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/LogoEsquina.jpg"))); // NOI18N

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Crear pago");

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel56)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel55))
        );

        Agrupador.add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, -1));

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel58.setText("Seleccione la cuenta*");
        Agrupador.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, -1));

        cbxCuentas1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Agrupador.add(cbxCuentas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, -1));

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel59.setText("Tipo de Pago*");
        Agrupador.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));

        cbxTiposPago1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Agrupador.add(cbxTiposPago1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, -1, -1));

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel60.setText("Ingrese el monto*");
        Agrupador.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, -1, -1));

        btnCancelar.setBackground(new java.awt.Color(0, 102, 153));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        Agrupador.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, 90, 40));

        btnEditar.setBackground(new java.awt.Color(0, 102, 153));
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        Agrupador.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 90, 40));
        Agrupador.add(spinnerMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 160, -1));

        setJMenuBar(MenuBarAdmin);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Agrupador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Agrupador, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void llenarComboBoxCuenta(Long id) {
        try {
            listaCuentas = gestionarCuentasBancarias.listaCuentasPorBeneficiario(id);

            for (CuentaBancariaDTO cuenta : listaCuentas) {
                cbxCuentas1.addItem(cuenta);
            }
        } catch (NegocioException ex) {
            Logger.getLogger(ModificarPago.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void llenarComboBoxTipoPago() {
        try {
            listaTipoPagos = gestionarPagos.listaTiposPago();

            for (TipoPagoDTO tipoPago : listaTipoPagos) {
                cbxTiposPago1.addItem(tipoPago);
            }
        } catch (NegocioException ex) {
            Logger.getLogger(ModificarPago.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void configurarMonto() {
        SpinnerNumberModel numberModel = new SpinnerNumberModel(0.00, 0.00, Double.MAX_VALUE, 0.01);
        spinnerMonto.setModel(numberModel);

        // Configurar el editor del Spinner para mostrar dos decimales
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinnerMonto, "#,##0.00");
        spinnerMonto.setEditor(editor);
    }


    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        Pagos pagos = new Pagos(gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos, gestionarAbonos, beneficiario);

        pagos.setVisible(true);

        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        try {
            TipoPagoDTO tipoPago = cbxTiposPago1.getSelectedItem() != null ? (TipoPagoDTO) cbxTiposPago1.getSelectedItem() : null;
            CuentaBancariaDTO cuenta = cbxCuentas1.getSelectedItem() != null ? (CuentaBancariaDTO) cbxCuentas1.getSelectedItem() : null;

            Number valorNumber = (Number) spinnerMonto.getValue();
            BigDecimal monto = BigDecimal.valueOf(valorNumber.doubleValue());

            if (tipoPago == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un tipo de pago.", "Tipo de pago no seleccionada", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (cuenta == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una cuenta.", "Cuenta no seleccionada", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(this, "Por favor, Ingrese un monto valido", "Monto Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            pagoDTO.setMonto(monto);
            pagoDTO.setTipoPagoId(tipoPago.getId());
            pagoDTO.setBeneficiarioId(cuenta.getBeneficiarioId());
            pagoDTO.setCuentaBancariaId(cuenta.getCuentaBancariaId());

            int confirmacion = JOptionPane.showOptionDialog(this,
                    "¿Está seguro de que deseas editar este Pago?",
                    "Confirmación de eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Confirmar", "Cancelar"},
                    "Confirmar");

            // Si el usuario selecciona "Cancelar", no se hace nada
            if (confirmacion != JOptionPane.YES_OPTION) {
                return;
            }

            gestionarPagos.editarPago(pagoDTO);

            JOptionPane.showMessageDialog(this, "Se ha editado el pago correctamente", "Exito en el pago", JOptionPane.INFORMATION_MESSAGE);

            Pagos pagos = new Pagos(gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos, gestionarAbonos, beneficiario);

            pagos.setVisible(true);
            this.dispose();

        } catch (NegocioException ex) {

            Logger.getLogger(ModificarPago.class.getName()).log(Level.SEVERE, null, ex);

            //JOptionPane.showMessageDialog(this, "Error al editar el pago", "Error", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_btnEditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Agrupador;
    private javax.swing.JMenuBar MenuBarAdmin;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JComboBox<CuentaBancariaDTO> cbxCuentas1;
    private javax.swing.JComboBox<TipoPagoDTO> cbxTiposPago1;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JSpinner spinnerMonto;
    // End of variables declaration//GEN-END:variables
}
