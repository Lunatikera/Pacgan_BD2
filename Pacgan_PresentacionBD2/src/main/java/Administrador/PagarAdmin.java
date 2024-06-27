/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Administrador;

import Beneficiario.ModificarPago;
import Beneficiario.Pagos;
import dtos.BeneficiarioDTO;
import dtos.CuentaBancariaDTO;
import dtos.EstatusDTO;
import dtos.PagoDTO;
import dtos.Pago_EstadoDTO;
import excepciones.NegocioException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import servicios.IConsultarEstadoPagos;
import servicios.IGestionarBeneficiarios;
import servicios.IGestionarCuentasBancarias;
import servicios.IGestionarPagos;
import utileria.JButtonCellEditor;
import utileria.JButtonRenderer;

/**
 *
 * @author jesus
 */
public class PagarAdmin extends javax.swing.JFrame {

    IGestionarCuentasBancarias gestionarCuentasBancarias;
    IGestionarPagos gestionarPagos;
    IConsultarEstadoPagos consultarEstadoPagos;
    IGestionarBeneficiarios gestionarBeneficiarios;
    List<Long> pagoIds;
    private int pagina = 1;
    private final int LIMITE = 10;
    private String estatusFiltro;

    /**
     * Creates new form PagarAdmin
     */
    public PagarAdmin(IGestionarCuentasBancarias gestionarCuentasBancarias, IConsultarEstadoPagos consultarEstadoPagos, IGestionarBeneficiarios gestionarBeneficiarios, IGestionarPagos gestionarPagos) {

        initComponents();
        this.setLocationRelativeTo(this);
        this.gestionarPagos = gestionarPagos;
        this.gestionarCuentasBancarias = gestionarCuentasBancarias;
        this.consultarEstadoPagos = consultarEstadoPagos;
        this.gestionarBeneficiarios = gestionarBeneficiarios;
        this.pagoIds = new ArrayList<>();
        personalizador();
        agregarOpcionesMenu();
        estatusFiltro = "AMBOSPAGAR";
        this.cargarMetodosIniciales();
    }

    public void personalizador() {
        panelMenu.setBackground(Color.decode("#142132"));
        btnAtras.setBackground(Color.decode("#142132"));
        btnSiguiente.setBackground(Color.decode("#142132"));

    }

    public void cargarMetodosIniciales() {
        //this.cargarConfiguracionInicialPantalla();
        this.cargarPagosEnTabla();
        this.estadoPagina();
        configurarBotones();
        this.configuraFiltros();
    }

    public void cargarPagosEnTabla() {
        try {
            pagoIds.clear();
            List<PagoDTO> pagoLista = this.gestionarPagos.listaPagoPaginadoAdmin(LIMITE, pagina, estatusFiltro);

            for (PagoDTO pagoDTO : pagoLista) {
                pagoIds.add(pagoDTO.getPagoId());
            }
            if (pagoLista.isEmpty() && pagina == 1) {
                JOptionPane.showMessageDialog(this, "No hay Pagos Registrados", "Información", JOptionPane.ERROR_MESSAGE);

            }
            this.llenarTablaPagos(pagoLista);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void configuraFiltros() {

        chboxAutorizados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEstatusFiltro();
            }
        });

        chboxPagados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEstatusFiltro();
            }
        });
    }

    private void setEstatusFiltro() {

        if (chboxAutorizados.isSelected() && chboxPagados.isSelected()) {
            estatusFiltro = "AMBOSPAGAR";

        } else if (chboxPagados.isSelected()) {
            estatusFiltro = "PAGADO";

        } else if (chboxAutorizados.isSelected()) {
            estatusFiltro = "AUTORIZADO";

        } else {
            estatusFiltro = "AMBOSPAGAR";
        }
        cargarPagosEnTabla();

    }

    private void configurarBotones() {
        // ActionListener para botón Editar
        ActionListener rechazarListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblPagos.getSelectedRow();
                if (selectedRow != -1) {
                    Long selectedId = pagoIds.get(selectedRow);
                    rechazar(selectedId);
                }
            }
        };

        // ActionListener para botón Eliminar
        ActionListener PagarListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblPagos.getSelectedRow();
                if (selectedRow != -1) {
                    Long selectedId = pagoIds.get(selectedRow);
                    pagar(selectedId);
                }
            }
        };

        // Configurar columna de Editar
        int indiceColumnaEditar = 4; // Suponiendo que esta es la tercera columna (índice 2)
        TableColumnModel modeloColumnas = tblPagos.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellRenderer(new JButtonRenderer("Rechazar"));
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellEditor(new JButtonCellEditor("Rechazar", rechazarListener));

        // Configurar columna de Eliminar
        int indiceColumnaEliminar = 5; // Suponiendo que esta es la cuarta columna (índice 3)
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellRenderer(new JButtonRenderer("Pagar"));
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellEditor(new JButtonCellEditor("Pagar", PagarListener));
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
                    BeneficiarioDTO beneficiario = gestionarBeneficiarios.consultarBeneficiarioPorID(row.getBeneficiarioId());
                    Pago_EstadoDTO pago_EstadoDTO = consultarEstadoPagos.obtenerEstadoDelPago(row.getPagoId());
                    EstatusDTO estatusDTO = consultarEstadoPagos.consultarEstatusPorID(pago_EstadoDTO.getIdEstatus());

                    Object[] fila = new Object[4];
                    fila[0] = beneficiario.getClaveContrato();
                    fila[1] = cuentaBancaria.getNumeroCuenta();
                    fila[2] = row.getMonto();
                    fila[3] = estatusDTO.getNombre();

                    modeloTabla.addRow(fila);
                } catch (NegocioException ex) {
                    Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }

    private void rechazar(Long id) {
        try {

            if (id == 0) {
                throw new NegocioException("Por favor seleccione un Pago");
            }
            PagoDTO pagoDTO = gestionarPagos.consultarPagoPorID(id);
            Pago_EstadoDTO pagoEstado = new Pago_EstadoDTO(pagoDTO.getPagoId(), 3L);

            int confirmacion = JOptionPane.showOptionDialog(this,
                    "¿Está seguro de que desea Rechazar este Pago?",
                    "Confirmación de Rechazo",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Confirmar", "Cancelar"},
                    "Confirmar");

            // Si el usuario selecciona "Cancelar", no se hace nada
            if (confirmacion != JOptionPane.YES_OPTION) {
                return;
            }

            gestionarPagos.agregarPagoEstatus(pagoEstado);

            if (this.gestionarPagos.listaPagoPaginadoAdmin(LIMITE, pagina, estatusFiltro) == null || this.gestionarPagos.listaPagoPaginadoAdmin(LIMITE, pagina, estatusFiltro).isEmpty()) {
                this.btnAtrasActionPerformed(null);
            } else {
                cargarPagosEnTabla();
            }
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pagar(Long id) {
        //Aqui se hace lo de autorizarPago
        try {

            if (id == 0) {
                throw new NegocioException("Por favor seleccione un Pago");
            }
            PagoDTO pagoDTO = gestionarPagos.consultarPagoPorID(id);
            Pago_EstadoDTO pagoEstado = new Pago_EstadoDTO(pagoDTO.getPagoId(), 4L);

            int confirmacion = JOptionPane.showOptionDialog(this,
                    "¿Está seguro de que desea pagar este Pago?",
                    "Confirmación de pago",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Confirmar", "Cancelar"},
                    "Confirmar");

            // Si el usuario selecciona "Cancelar", no se hace nada
            if (confirmacion != JOptionPane.YES_OPTION) {
                return;
            }

            gestionarPagos.agregarPagoEstatus(pagoEstado);

            if (this.gestionarPagos.listaPagoPaginadoAdmin(LIMITE, pagina, estatusFiltro) == null || this.gestionarPagos.listaPagoPaginadoAdmin(LIMITE, pagina, estatusFiltro).isEmpty()) {
                this.btnAtrasActionPerformed(null);
            } else {
                cargarPagosEnTabla();
            }
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void estadoPagina() {
        String numPagina = String.valueOf(pagina);
        lblPagina.setText("Pagina " + numPagina);
        estatusBotonAtras();
        estatusBotonSiguiente();
    }

    private void estatusBotonAtras() {
        if (this.pagina > 1) {
            btnAtras.setEnabled(true);
            return;
        }
        btnAtras.setEnabled(false);
    }

    private void estatusBotonSiguiente() {

        try {
            btnSiguiente.setEnabled(true);
            if (this.gestionarPagos.listaPagoPaginadoAdmin(LIMITE, pagina + 1, estatusFiltro) == null
                    || this.gestionarPagos.listaPagoPaginadoAdmin(LIMITE, pagina + 1, estatusFiltro).isEmpty()) {
                btnSiguiente.setEnabled(false);
            }
        } catch (NegocioException ex) {
            System.out.println(ex);
        }

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
                // Open your frame here
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
        panelMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        lblPagina = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPagos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        chboxAutorizados = new javax.swing.JCheckBox();
        chboxPagados = new javax.swing.JCheckBox();
        MenuBarAdmin = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Agrupador.setBackground(new java.awt.Color(255, 255, 255));
        Agrupador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelMenu.setBackground(new java.awt.Color(0, 51, 102));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/LogoEsquina.jpg"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Realizar Pagos");

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
                        .addGap(0, 844, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(256, 256, 256))))
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

        jLabel5.setForeground(new java.awt.Color(0, 51, 102));
        jLabel5.setText("Adminstracion de pagos:");
        Agrupador.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

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
        lblPagina.setForeground(new java.awt.Color(0, 51, 102));
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

        tblPagos.setBackground(new java.awt.Color(234, 234, 234));
        tblPagos.setForeground(new java.awt.Color(0, 51, 102));
        tblPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave", "Cuenta", "Monto", "Estatus", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPagos);

        Agrupador.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 880, 350));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        chboxAutorizados.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        chboxAutorizados.setForeground(new java.awt.Color(0, 51, 102));
        chboxAutorizados.setText("Autorizados");

        chboxPagados.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        chboxPagados.setForeground(new java.awt.Color(0, 51, 102));
        chboxPagados.setText("Pagados");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chboxPagados, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chboxAutorizados, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chboxAutorizados)
                    .addComponent(chboxPagados, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        Agrupador.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, 280, 60));

        setJMenuBar(MenuBarAdmin);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Agrupador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Agrupador, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        this.pagina = this.pagina + 1;
        this.cargarPagosEnTabla();
        this.estadoPagina();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        this.pagina = this.pagina - 1;
        this.cargarPagosEnTabla();
        this.estadoPagina();
    }//GEN-LAST:event_btnAtrasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Agrupador;
    private javax.swing.JMenuBar MenuBarAdmin;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JCheckBox chboxAutorizados;
    private javax.swing.JCheckBox chboxPagados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JTable tblPagos;
    // End of variables declaration//GEN-END:variables
}
