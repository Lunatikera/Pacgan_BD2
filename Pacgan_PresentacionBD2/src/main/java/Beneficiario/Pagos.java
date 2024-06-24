/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Beneficiario;

import dtos.CuentaBancariaDTO;
import dtos.EstatusDTO;
import dtos.PagoDTO;
import dtos.Pago_EstadoDTO;
import excepciones.NegocioException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import servicios.IConsultarEstadoPagos;
import servicios.IGestionarCuentasBancarias;
import servicios.IGestionarPagos;

/**
 *
 * @author jesus
 */
public class Pagos extends javax.swing.JFrame {

    IGestionarCuentasBancarias gestionarCuentasBancarias;
    IGestionarPagos gestionarPagos;
    IConsultarEstadoPagos consultarEstadoPagos;
    private int pagina = 1;
    private final int LIMITE = 10;

    public Pagos() {
        initComponents();
        this.gestionarPagos = gestionarPagos;
        this.gestionarCuentasBancarias = gestionarCuentasBancarias;
        this.consultarEstadoPagos = consultarEstadoPagos;
        personalizador();
        agregarOpcionesMenu();
    }

    public void personalizador() {
        panelMenu.setBackground(Color.decode("#142132"));
        btnBuscar.setBackground(Color.decode("#142132"));
        btnCrearPago.setBackground(Color.decode("#142132"));
        btnAtras.setBackground(Color.decode("#142132"));
        btnSiguiente.setBackground(Color.decode("#142132"));

    }

    public void cargarPagosEnTabla() {
        try {
            List<PagoDTO> pagoLista = this.gestionarPagos.listaPagosPaginado(this.LIMITE, this.pagina);
            //this.llenarTablaAlumnos(pagoLista);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
            pagina--;
        }
    }

    private void llenarTablaPagos(List<PagoDTO> pagoLista) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblPagos.getModel();

        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }

        if (pagoLista != null) {
            pagoLista.forEach(row
                    -> {
                try {
                    CuentaBancariaDTO cuentaBancaria = gestionarCuentasBancarias.consultarCuentaBancariaPorID(row.getCuentaBancariaId());
                    Pago_EstadoDTO pago_EstadoDTO = consultarEstadoPagos.obtenerEstadoDelPago(row.getPagoId());
                    EstatusDTO estatusDTO= consultarEstadoPagos.consultarEstatusPorID(pago_EstadoDTO.getIdEstatus());
                    
                    Object[] fila = new Object[4];
                    fila[0] = cuentaBancaria.getNumeroCuenta();
                    fila[1] = row.getMonto();
                    fila[2] = estatusDTO.getNombre();
                    fila[3] = pago_EstadoDTO.getMensaje();

                    modeloTabla.addRow(fila);
                } catch (NegocioException ex) {
                    Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }

    public void agregarOpcionesMenu() {

        JMenu menuPagos = new JMenu("Pagos");
        JMenuItem misPagos = new JMenuItem("Mis pagos");
        misPagos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pagos Pagos = new Pagos();
                Pagos.setVisible(true);
                dispose();

            }
        });

        menuPagos.add(misPagos);

        JMenu menuAbonos = new JMenu("Abonos");
        JMenuItem misAbonos = new JMenuItem("Mis Abonos");
        misAbonos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Abonos Abonos = new Abonos();
                Abonos.setVisible(true);
                dispose();

            }
        });

        menuAbonos.add(misAbonos);

        JMenu menuCuentas = new JMenu("Cuentas");
        JMenuItem misCuentas = new JMenuItem("Mis cuentas");
        misCuentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cuentas Cuentas = new Cuentas();
                Cuentas.setVisible(true);
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

        MenuBarAdmin.add(menuAbonos);
        MenuBarAdmin.add(menuPagos);
        MenuBarAdmin.add(menuCuentas);
        MenuBarAdmin.add(menuSalir);

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
        panelMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPagos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        btnCrearPago = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        lblPagina = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        MenuBarAdmin = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Agrupador.setBackground(new java.awt.Color(255, 255, 255));
        Agrupador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelMenu.setBackground(new java.awt.Color(0, 51, 102));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/LogoEsquina.jpg"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Pagos");

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
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGap(353, 353, 353)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 407, Short.MAX_VALUE))
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

        Agrupador.add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 60));

        tblPagos.setBackground(new java.awt.Color(234, 234, 234));
        tblPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Cuenta", "Monto", "Estatus", "Comentarios", "", ""
            }
        ));
        jScrollPane1.setViewportView(tblPagos);

        Agrupador.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 880, 350));

        jLabel5.setText("Mis Pagos");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Agrupador.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 140, 30));

        btnCrearPago.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearPago.setText("Crear Pago");
        btnCrearPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPagoActionPerformed(evt);
            }
        });
        Agrupador.add(btnCrearPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, 100, 30));

        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Actualizar");
        Agrupador.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 130, 100, 30));

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
            .addComponent(Agrupador, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPagoActionPerformed
        // TODO add your handling code here:
        CrearPago crearPago = new CrearPago();

        crearPago.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCrearPagoActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSiguienteActionPerformed

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
            java.util.logging.Logger.getLogger(Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pagos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Agrupador;
    private javax.swing.JMenuBar MenuBarAdmin;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCrearPago;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JTable tblPagos;
    // End of variables declaration//GEN-END:variables
}
