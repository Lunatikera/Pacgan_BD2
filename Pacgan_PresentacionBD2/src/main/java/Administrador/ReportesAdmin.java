/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Administrador;

import Reportes.ReportePago;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import servicios.IConsultarEstadoPagos;
import servicios.IGestionarBeneficiarios;
import servicios.IGestionarCuentasBancarias;
import servicios.IGestionarPagos;

/**
 *
 * @author jesus
 */
public class ReportesAdmin extends javax.swing.JFrame {

    IGestionarCuentasBancarias gestionarCuentasBancarias;
    IGestionarPagos gestionarPagos;
    IConsultarEstadoPagos consultarEstadoPagos;
    IGestionarBeneficiarios gestionarBeneficiarios;

    public ReportesAdmin(IGestionarCuentasBancarias gestionarCuentasBancarias,
            IGestionarPagos gestionarPagos,
            IConsultarEstadoPagos consultarEstadoPagos,
            IGestionarBeneficiarios gestionarBeneficiarios) {
        initComponents();
        personalizador();
        this.gestionarPagos = gestionarPagos;
        this.gestionarCuentasBancarias = gestionarCuentasBancarias;
        this.consultarEstadoPagos = consultarEstadoPagos;
        this.gestionarBeneficiarios = gestionarBeneficiarios;
        agregarOpcionesMenu();
    }

    public void personalizador() {
        panelMenu.setBackground(Color.decode("#142132"));
        btnBuscar.setBackground(Color.decode("#142132"));
        btnGenerarReporte.setBackground(Color.decode("#142132"));
        btnAtras.setBackground(Color.decode("#142132"));
        btnSiguiente.setBackground(Color.decode("#142132"));

    }

    public void agregarOpcionesMenu() {

        JMenu menuReportes = new JMenu("Reportes");
        JMenuItem generarReporte = new JMenuItem("Generar Reportes");
        generarReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open your frame here
                ReportesAdmin reportes = new ReportesAdmin(gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos, gestionarBeneficiarios);
                reportes.setVisible(true);
                dispose();

            }
        });

        menuReportes.add(generarReporte);

        JMenu menuPagos = new JMenu("Pagos");
        JMenuItem pagoAutorizado = new JMenuItem("Autorizados");
        pagoAutorizado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open your frame here
                AutorizarPagos autorizar = new AutorizarPagos(gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos, gestionarBeneficiarios);
                autorizar.setVisible(true);
                dispose();

            }
        });

        menuPagos.add(pagoAutorizado);

        JMenuItem pagosPagados = new JMenuItem("Pagados");
        pagosPagados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open your frame here
                PagarAdmin pagados = new PagarAdmin(gestionarCuentasBancarias, consultarEstadoPagos, gestionarBeneficiarios, gestionarPagos);
                pagados.setVisible(true);
                dispose();

            }
        });

        menuPagos.add(pagosPagados);

        JMenu menuBeneficiario = new JMenu("Beneficiarios");
        JMenuItem adminBenef = new JMenuItem("Administrar Beneficiarios");
        adminBenef.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 BeneficiariosAdmin benef = new BeneficiariosAdmin(gestionarBeneficiarios, consultarEstadoPagos, gestionarPagos, gestionarCuentasBancarias);
                 benef.setVisible(true);
                dispose();

            }
        });

        menuBeneficiario.add(adminBenef);

        JMenu menuSalir = new JMenu("Salir");
        JMenuItem salir = new JMenuItem("Salir");
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });

        menuSalir.add(salir);

        MenuBarAdmin.add(menuReportes);
        MenuBarAdmin.add(menuPagos);
        MenuBarAdmin.add(menuBeneficiario);
        MenuBarAdmin.add(menuSalir);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Agrupador = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        panelMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPagos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbAbonos = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        datePicker1 = new com.github.lgooddatepicker.components.DatePicker();
        jLabel8 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        btnGenerarReporte = new javax.swing.JButton();
        cmbEstatus = new raven.combobox.ComboBoxMultiSelection();
        datePicker3 = new com.github.lgooddatepicker.components.DatePicker();
        datePicker4 = new com.github.lgooddatepicker.components.DatePicker();
        btnAtras = new javax.swing.JButton();
        lblPagina = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        MenuBarAdmin = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Agrupador.setBackground(new java.awt.Color(255, 255, 255));
        Agrupador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBuscar.setBackground(new java.awt.Color(0, 102, 153));
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        Agrupador.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 170, 130, 30));

        panelMenu.setBackground(new java.awt.Color(0, 51, 102));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/LogoEsquina.jpg"))); // NOI18N

        jLabel9.setText("Reportes");
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));

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
                        .addGap(0, 874, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(213, 213, 213))))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1))
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Agrupador.add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 60));

        tblPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tipo", "Monto", "Nombre Beneficiario", "Abonos Terminados", "Cuenta Deposito", "Estatus"
            }
        ));
        tblPagos.setBackground(new java.awt.Color(234, 234, 234));
        tblPagos.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(tblPagos);

        Agrupador.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 880, 330));

        jLabel2.setText("Tipos");
        Agrupador.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jLabel4.setText("Estatus:");
        Agrupador.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jLabel5.setText("Filtros");
        Agrupador.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel6.setText("a");
        Agrupador.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 110, 20, -1));

        cmbAbonos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbAbonos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAbonosActionPerformed(evt);
            }
        });
        Agrupador.add(cmbAbonos, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 90, 20));

        jLabel7.setText("Abonos Terminados");
        Agrupador.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, -1));
        Agrupador.add(datePicker1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 190, 30));

        jLabel8.setText("Periodo:");
        Agrupador.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, -1, -1));

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });
        Agrupador.add(cmbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 90, 20));

        btnGenerarReporte.setText("Generar Reporte");
        btnGenerarReporte.setBackground(new java.awt.Color(0, 102, 153));
        btnGenerarReporte.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });
        Agrupador.add(btnGenerarReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 170, 130, 30));

        cmbEstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hola", "como", "estas", " " }));
        Agrupador.add(cmbEstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 170, -1));
        Agrupador.add(datePicker3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 190, 30));
        Agrupador.add(datePicker4, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 190, 30));

        btnAtras.setBackground(new java.awt.Color(0, 102, 153));
        btnAtras.setForeground(new java.awt.Color(255, 255, 255));
        btnAtras.setText("Atras");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        Agrupador.add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 560, 130, 30));

        lblPagina.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPagina.setText("Pagina 1");
        Agrupador.add(lblPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 570, -1, -1));

        btnSiguiente.setBackground(new java.awt.Color(0, 102, 153));
        btnSiguiente.setForeground(new java.awt.Color(255, 255, 255));
        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        Agrupador.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 560, 130, 30));

        setJMenuBar(MenuBarAdmin);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Agrupador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Agrupador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cmbAbonosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAbonosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAbonosActionPerformed

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
        // TODO add your handling code here:

        // Obtener los datos necesarios de la interfaz de usuario
        String tipo = cmbTipo.getSelectedItem().toString();
        String estatus = cmbEstatus.getSelectedItem().toString();
        String abonos = cmbAbonos.getSelectedItem().toString();
        DefaultTableModel model = (DefaultTableModel) tblPagos.getModel();

        // Llamar al método de negocio para generar el PDF
        ReportePago reporteBO = new ReportePago();
        reporteBO.ReportePagos(tipo, estatus, abonos, model);
    }//GEN-LAST:event_btnGenerarReporteActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSiguienteActionPerformed

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Agrupador;
    private javax.swing.JMenuBar MenuBarAdmin;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox<String> cmbAbonos;
    private raven.combobox.ComboBoxMultiSelection cmbEstatus;
    private javax.swing.JComboBox<String> cmbTipo;
    private com.github.lgooddatepicker.components.DatePicker datePicker1;
    private com.github.lgooddatepicker.components.DatePicker datePicker3;
    private com.github.lgooddatepicker.components.DatePicker datePicker4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JTable tblPagos;
    // End of variables declaration//GEN-END:variables
}
