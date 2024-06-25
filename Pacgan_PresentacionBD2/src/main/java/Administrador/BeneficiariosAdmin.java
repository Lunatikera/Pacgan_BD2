/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Administrador;

import dtos.BeneficiarioDTO;
import excepciones.NegocioException;
import interfaces.IConsultarBeneficiarioBO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.table.DefaultTableModel;
import servicios.IGestionarBeneficiarios;

/**
 *
 * @author jesus
 */
public class BeneficiariosAdmin extends javax.swing.JFrame {

    private int numeroPaginaActual = 1; // Variable para almacenar el número de página actual
    private int tamanoPagina = 10;

    IGestionarBeneficiarios gestionarBeneficiarios;

    /**
     * Creates new form BeneficiariosAdmin
     */
    public BeneficiariosAdmin() {
        initComponents();
        personalizador();
        agregarOpcionesMenu();
    }

    public void personalizador() {
        panelMenu.setBackground(Color.decode("#142132"));
        btnBuscar.setBackground(Color.decode("#142132"));
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
                ReportesAdmin reportes = new ReportesAdmin();
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
                AutorizarPagos autorizar = new AutorizarPagos();
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
                PagarAdmin pagados = new PagarAdmin();
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
                // Open your frame here
                BeneficiariosAdmin benef = new BeneficiariosAdmin();
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

    public void cargarBeneficiariosPaginado(int numeroPagina, int tamanoPagina) {
        try {
            // Llamar al método correspondiente en la capa de negocio para obtener la lista paginada de DTO
            List<BeneficiarioDTO> listaBeneficiarios = gestionarBeneficiarios.listaBeneficiariosPaginado(numeroPaginaActual, tamanoPagina);

            // Limpiar la tabla antes de cargar nuevos datos
            DefaultTableModel model = (DefaultTableModel) tblBeneficiarios.getModel();
            model.setRowCount(0);

            // Agregar los datos a la tabla
            for (BeneficiarioDTO beneficiarioDTO : listaBeneficiarios) {
                Object[] row = {beneficiarioDTO.getNombre(), beneficiarioDTO.getApellidoPA(), beneficiarioDTO.getApellidoMA()};
                model.addRow(row);
            }
        } catch (NegocioException ex) {
            Logger.getLogger(BeneficiariosAdmin.class.getName()).log(Level.SEVERE, null, ex);
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
        btnBuscar = new javax.swing.JButton();
        panelMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBeneficiarios = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        lblPagina = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        MenuBarAdmin = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Agrupador.setBackground(new java.awt.Color(255, 255, 255));
        Agrupador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBuscar.setBackground(new java.awt.Color(0, 102, 153));
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Crear nuevo Beneficiario");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        Agrupador.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 130, 180, 30));

        panelMenu.setBackground(new java.awt.Color(0, 51, 102));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/LogoEsquina.jpg"))); // NOI18N

        jLabel2.setText("Administracion Beneficarios");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));

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
                        .addGap(247, 247, 247)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 276, Short.MAX_VALUE))
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

        tblBeneficiarios.setBackground(new java.awt.Color(234, 234, 234));
        tblBeneficiarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Clave Contrato", "Nombre Usuario", "Nombre", "Apellido Paterno", "Apellido Materno", "Saldo", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblBeneficiarios);

        Agrupador.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 880, 360));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Beneficiarios");
        Agrupador.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 150, -1));

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
        lblPagina.setForeground(new java.awt.Color(0, 0, 0));
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

        CrearBeneficiario crear = new CrearBeneficiario();

        crear.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:

        cargarBeneficiariosPaginado(numeroPaginaActual - 1, tamanoPagina);

    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:

        cargarBeneficiariosPaginado(numeroPaginaActual + 1, tamanoPagina);
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
            java.util.logging.Logger.getLogger(BeneficiariosAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BeneficiariosAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BeneficiariosAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BeneficiariosAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BeneficiariosAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Agrupador;
    private javax.swing.JMenuBar MenuBarAdmin;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JTable tblBeneficiarios;
    // End of variables declaration//GEN-END:variables
}
