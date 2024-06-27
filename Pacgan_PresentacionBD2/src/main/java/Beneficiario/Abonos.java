/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Beneficiario;

import Administrador.AutorizarPagos;
import Administrador.BeneficiariosAdmin;
import Administrador.PagarAdmin;
import Administrador.ReportesAdmin;
import Inicio.LogIn;
import dtos.AbonoDTO;
import dtos.BeneficiarioDTO;
import dtos.CuentaBancariaDTO;
import dtos.PagoDTO;
import dtos.TipoPagoDTO;
import excepciones.NegocioException;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
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
import servicios.IGestionarAbonos;
import servicios.IGestionarCuentasBancarias;
import servicios.IGestionarPagos;
import utileria.JButtonCellEditor;
import utileria.JButtonRenderer;

/**
 *
 * @author jesus
 */
public class Abonos extends javax.swing.JFrame {

    private int pagina = 1;
    private final int LIMITE = 7;
    IGestionarAbonos gestionarAbonos;
    IGestionarCuentasBancarias gestionarCuentasBancarias;
    IGestionarPagos gestionarPago;
    IConsultarEstadoPagos consultarEstadoPagos;
    BeneficiarioDTO beneficiarioDTO;
    PagoDTO pagoDTO;
    List<Long> abonosIds;

    public Abonos(IGestionarAbonos gestionarAbonos, IGestionarCuentasBancarias gestionarCuentasBancarias, IGestionarPagos gestionarPago, IConsultarEstadoPagos consultarEstadoPagos, PagoDTO pagoDTO, BeneficiarioDTO beneficiarioDTO
    ) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.gestionarAbonos = gestionarAbonos;
        this.gestionarCuentasBancarias = gestionarCuentasBancarias;
        this.gestionarPago = gestionarPago;
        this.consultarEstadoPagos = consultarEstadoPagos;
        this.beneficiarioDTO = beneficiarioDTO;
        this.pagoDTO = pagoDTO;
        abonosIds = new ArrayList<>();
        personalizador();
        agregarOpcionesMenu();
        cargarMetodosIniciales();
        System.out.println(pagoDTO);
    }

    public void personalizador() {
        panelMenu.setBackground(Color.decode("#142132"));
        btnBuscar.setBackground(Color.decode("#142132"));
        btnAtras.setBackground(Color.decode("#142132"));
        btnSiguiente.setBackground(Color.decode("#142132"));

    }

    public void cargarMetodosIniciales() {
        //this.cargarConfiguracionInicialPantalla();
        this.cargarAbonosEnTabla();
        this.estadoPagina();
        this.configurarBotones();
        this.llenarLabels();
    }

    public void cargarAbonosEnTabla() {
        try {
            abonosIds.clear();
            List<AbonoDTO> abonoLista = this.gestionarAbonos.listaAbonosPaginadoPorPago(this.LIMITE, this.pagina, pagoDTO.getPagoId());
            System.out.println(abonoLista);
            if (abonoLista.isEmpty()) {
                return;
            }
            for (AbonoDTO abonoDTO : abonoLista) {
                abonosIds.add(abonoDTO.getAbonoId());
            }
              if (abonoLista.isEmpty() && pagina == 1) {
                JOptionPane.showMessageDialog(this, "No hay Abonos Registrados", "Información", JOptionPane.ERROR_MESSAGE);

            }

            this.llenarTablaAbonos(abonoLista);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void llenarLabels() {

        lblTotalPagar.setText("Total a Pagar: " + pagoDTO.getMonto().subtract(this.obtenerPagoRestante()));
        lblPagosRestantes.setText("Pagos Restantes: " + this.obtenerPagosRestante());
    }

    private Integer obtenerPagosRestante() {
        try {
            TipoPagoDTO tipoPagoDTO = gestionarPago.consultarTipoPagoPorID(pagoDTO.getTipoPagoId());
            Integer pagosRestantes = tipoPagoDTO.getNumeroParcialidades() - abonosIds.size();
            return pagosRestantes;
        } catch (NegocioException ex) {
            Logger.getLogger(Abonos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    private void llenarTablaAbonos(List<AbonoDTO> abonoLista) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblAbonos.getModel();

        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }

        if (abonoLista != null) {
            abonoLista.forEach(row
                    -> {
                try {

                    CuentaBancariaDTO cuentaBancaria = gestionarCuentasBancarias.consultarCuentaBancariaPorID(pagoDTO.getCuentaBancariaId());

                    Object[] fila = new Object[4];
                    fila[0] = cuentaBancaria.getNumeroCuenta();
                    fila[1] = row.getMonto();
                    fila[2] = row.getFecha();
                    fila[3] = row.getHora();

                    modeloTabla.addRow(fila);
                } catch (NegocioException ex) {
                    Logger.getLogger(Abonos.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }

    private void configurarBotones() {

        // ActionListener para botón Eliminar
        ActionListener eliminarListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblAbonos.getSelectedRow();
                if (selectedRow != -1) {
                    Long selectedId = abonosIds.get(selectedRow);
                    eliminar(selectedId);
                }
            }
        };

        // Configurar columna de Editar
        TableColumnModel modeloColumnas = tblAbonos.getColumnModel();
        int indiceColumnaEliminar = 4; // Suponiendo que esta es la cuarta columna (índice 3)
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellRenderer(new JButtonRenderer("Eliminar"));
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellEditor(new JButtonCellEditor("Eliminar", eliminarListener));
    }

    private void eliminar(Long id) {
        try {

            if (id == 0) {
                throw new NegocioException("Por favor seleccione un Abono");
            }

            int confirmacion = JOptionPane.showOptionDialog(this,
                    "¿Está seguro de que desea eliminar este Abono?",
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
            gestionarAbonos.eliminarAbono(id);

            if (this.gestionarAbonos.listaAbonosPaginadoPorPago(this.LIMITE, this.pagina, pagoDTO.getPagoId()) == null || this.gestionarAbonos.listaAbonosPaginadoPorPago(this.LIMITE, this.pagina, pagoDTO.getPagoId()).isEmpty()) {
                this.btnAtrasActionPerformed(null);
            } else {
                cargarAbonosEnTabla();
            }
            llenarLabels();
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private BigDecimal obtenerPagoRestante() {
        BigDecimal cantidadAcumulada = BigDecimal.ZERO; // Initialize BigDecimal variable outside try block
        System.out.println(abonosIds);
        try {
            for (Long abonosId : abonosIds) {
                AbonoDTO abono = gestionarAbonos.consultarAbonoPorID(abonosId);
                cantidadAcumulada = cantidadAcumulada.add(abono.getMonto());
            }
            return cantidadAcumulada; // Return the accumulated amount
        } catch (NegocioException ex) {
            Logger.getLogger(Abonos.class.getName()).log(Level.SEVERE, null, ex);
            return BigDecimal.ZERO; // Return default value or handle the exception accordingly
        }
    }

    public void agregarOpcionesMenu() {

        JMenu menuPagos = new JMenu("Pagos");
        JMenuItem misPagos = new JMenuItem("Mis pagos");
        misPagos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pagos pagos = new Pagos(gestionarCuentasBancarias, gestionarPago, consultarEstadoPagos, gestionarAbonos, beneficiarioDTO);
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
                Abonos abonos = new Abonos(gestionarAbonos, gestionarCuentasBancarias, gestionarPago, consultarEstadoPagos, pagoDTO, beneficiarioDTO);
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
                Cuentas cuentas = new Cuentas(gestionarCuentasBancarias, gestionarPago, consultarEstadoPagos, gestionarAbonos, beneficiarioDTO);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAbonos = new javax.swing.JTable();
        lblPagosRestantes = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        lblPagina = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lblTotalPagar = new javax.swing.JLabel();
        MenuBarAdmin = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Agrupador.setBackground(new java.awt.Color(255, 255, 255));
        Agrupador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelMenu.setBackground(new java.awt.Color(0, 51, 102));
        panelMenu.setForeground(new java.awt.Color(0, 51, 102));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/LogoEsquina.jpg"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Abonos");

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

        tblAbonos.setBackground(new java.awt.Color(234, 234, 234));
        tblAbonos.setForeground(new java.awt.Color(0, 51, 102));
        tblAbonos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cuenta", "Monto", "Fecha", "Hora", ""
            }
        ));
        jScrollPane1.setViewportView(tblAbonos);

        Agrupador.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 880, 350));

        lblPagosRestantes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPagosRestantes.setForeground(new java.awt.Color(0, 51, 102));
        lblPagosRestantes.setText("Pagos Restantes:");
        Agrupador.add(lblPagosRestantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 230, 30));

        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Crear Abono");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        Agrupador.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 130, 160, 30));

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

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 102));
        jLabel6.setText("Mis Abonos");
        Agrupador.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 200, 30));

        lblTotalPagar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotalPagar.setForeground(new java.awt.Color(0, 51, 102));
        lblTotalPagar.setText("Total a Pagar:");
        Agrupador.add(lblTotalPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 300, 30));

        setJMenuBar(MenuBarAdmin);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Agrupador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Agrupador, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        this.pagina = this.pagina + 1;
        this.cargarAbonosEnTabla();
        this.estadoPagina();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.pagina = this.pagina - 1;
        this.cargarAbonosEnTabla();
        this.estadoPagina();    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        CrearAbono nuevoAbono = new CrearAbono(beneficiarioDTO, pagoDTO, gestionarCuentasBancarias, gestionarPago, gestionarAbonos, consultarEstadoPagos);
        nuevoAbono.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void estatusBotonAtras() {
        if (this.pagina > 1) {
            btnAtras.setEnabled(true);
            return;
        }
        btnAtras.setEnabled(false);
    }

    private void estadoPagina() {
        String numPagina = String.valueOf(pagina);
        lblPagina.setText("Pagina " + numPagina);
        estatusBotonAtras();
        estatusBotonSiguiente();
    }

    private void estatusBotonSiguiente() {

        try {
            btnSiguiente.setEnabled(true);
            if (this.gestionarAbonos.listaAbonosPaginadoPorPago(this.LIMITE, this.pagina + 1, pagoDTO.getPagoId()) == null
                    || this.gestionarAbonos.listaAbonosPaginadoPorPago(this.LIMITE, this.pagina + 1, pagoDTO.getPagoId()).isEmpty()) {
                btnSiguiente.setEnabled(false);
            }
        } catch (NegocioException ex) {
            System.out.println(ex);
        }

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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JLabel lblPagosRestantes;
    private javax.swing.JLabel lblTotalPagar;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JTable tblAbonos;
    // End of variables declaration//GEN-END:variables
}
