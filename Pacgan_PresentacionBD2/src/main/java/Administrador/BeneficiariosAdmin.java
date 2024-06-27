/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Administrador;

import Beneficiario.Abonos;
import Beneficiario.Cuentas;
import Beneficiario.Pagos;
import dtos.BeneficiarioDTO;
import dtos.CuentaBancariaDTO;
import dtos.EstatusDTO;
import dtos.PagoDTO;
import dtos.Pago_EstadoDTO;
import excepciones.NegocioException;
import interfaces.IConsultarBeneficiarioBO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
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
public class BeneficiariosAdmin extends javax.swing.JFrame {

    List<Long> beneficiarioIds;

    private int pagina = 1;
    private final int LIMITE = 10;
    IGestionarCuentasBancarias gestionarCuentasBancarias;
    IGestionarPagos gestionarPagos;
    IConsultarEstadoPagos consultarEstadoPagos;
    IGestionarBeneficiarios gestionarBeneficiarios;

    /**
     * Creates new form BeneficiariosAdmin
     */
    public BeneficiariosAdmin(IGestionarBeneficiarios gestionarBeneficiarios, IConsultarEstadoPagos consultarEstadoPagos, IGestionarPagos gestionarPagos, IGestionarCuentasBancarias gestionarCuentasBancarias
    ) {
        initComponents();
        this.gestionarCuentasBancarias = gestionarCuentasBancarias;
        this.gestionarPagos = gestionarPagos;
        this.gestionarBeneficiarios = gestionarBeneficiarios;
        this.consultarEstadoPagos=consultarEstadoPagos;
        beneficiarioIds = new ArrayList<>();

        personalizador();
        agregarOpcionesMenu();
        cargarMetodosIniciales();
    }

    public void personalizador() {
        panelMenu.setBackground(Color.decode("#142132"));
        btnBuscar.setBackground(Color.decode("#142132"));
        btnAtras.setBackground(Color.decode("#142132"));
        btnSiguiente.setBackground(Color.decode("#142132"));

    }

    public void cargarMetodosIniciales() {
        //this.cargarConfiguracionInicialPantalla();
        this.cargarBeneficiariosEnTabla();
        this.estadoPagina();
        configurarBotones();
    }

    private void estadoPagina() {
        String numPagina = String.valueOf(pagina);
        lblPagina.setText("Pagina " + numPagina);
        estatusBotonAtras();
        estatusBotonSiguiente();
    }

    private void estatusBotonAtras() {
        btnAtras.setEnabled(this.pagina > 1);
    }

    private void estatusBotonSiguiente() {
        try {
            List<BeneficiarioDTO> siguientePagina = this.gestionarBeneficiarios.listaBeneficiariosPaginado(this.LIMITE, this.pagina + 1);
            btnSiguiente.setEnabled(siguientePagina != null && !siguientePagina.isEmpty());
        } catch (NegocioException ex) {
            System.out.println(ex);
            btnSiguiente.setEnabled(false);
        }
    }

    public void cargarBeneficiariosEnTabla() {
        try {
            beneficiarioIds.clear();
            List<BeneficiarioDTO> beneficiarioLista = this.gestionarBeneficiarios.listaBeneficiariosPaginado(this.pagina, this.LIMITE);

            for (BeneficiarioDTO BeneficiarioDTO : beneficiarioLista) {
                beneficiarioIds.add(BeneficiarioDTO.getBeneficiarioId());
            }

            this.llenarTablaBeneficiarios(beneficiarioLista);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Informacion", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarBotones() {

        // ActionListener para botón Editar
        ActionListener editarListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblBeneficiarios.getSelectedRow();
                if (selectedRow != -1) {
                    Long selectedId = beneficiarioIds.get(selectedRow);
                    editar(selectedId);
                }
            }
        };

        // ActionListener para botón Eliminar
        ActionListener eliminarListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblBeneficiarios.getSelectedRow();
                if (selectedRow != -1) {
                    Long selectedId = beneficiarioIds.get(selectedRow);
                    eliminar(selectedId);
                }
            }
        };

        // Configurar columna de Editar
        int indiceColumnaEditar = 4; // Suponiendo que esta es la tercera columna (índice 2)
        TableColumnModel modeloColumnas = tblBeneficiarios.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellRenderer(new JButtonRenderer("Editar"));
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellEditor(new JButtonCellEditor("Editar", editarListener));

        // Configurar columna de Eliminar
        int indiceColumnaEliminar = 5; // Suponiendo que esta es la cuarta columna (índice 3)
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellRenderer(new JButtonRenderer("Eliminar"));
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellEditor(new JButtonCellEditor("Eliminar", eliminarListener));
    }

    private void llenarTablaBeneficiarios(List<BeneficiarioDTO> beneficiarioLista) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblBeneficiarios.getModel();
        modeloTabla.setRowCount(0); // Limpia la tabla

        if (beneficiarioLista != null) {
            for (BeneficiarioDTO beneficiario : beneficiarioLista) {
                Object[] fila = new Object[4];
                fila[0] = beneficiario.getNombre();
                fila[1] = beneficiario.getApellidoPA();
                fila[2] = beneficiario.getApellidoMA();
                fila[3] = beneficiario.getClaveContrato();
                modeloTabla.addRow(fila);
            }
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
//                // Open your frame here
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

    private void editar(Long id) {
        //Metodo para regresar el alumno seleccionado
        System.out.println(id);
        try {
            if (id == 0) {
                throw new NegocioException("Por favor seleccione un beneficiario");
            }
            BeneficiarioDTO beneficiarioDTO = gestionarBeneficiarios.consultarBeneficiarioPorID(id);

            ModificarBeneficiario editarBeneficiario = new ModificarBeneficiario(gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos, gestionarBeneficiarios);
            editarBeneficiario.setVisible(true);
            cargarBeneficiariosEnTabla();

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar(Long id) {
        try {

            if (id == 0) {
                throw new NegocioException("Por favor seleccione un beneficiario");
            }
            BeneficiarioDTO beneficiarioDTO = gestionarBeneficiarios.consultarBeneficiarioPorID(id);

            int confirmacion = JOptionPane.showOptionDialog(this,
                    "¿Está seguro de que desea eliminar este beneficiario?",
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
            gestionarBeneficiarios.eliminarBeneficiario(id);

            if (this.gestionarBeneficiarios.listaBeneficiariosPaginado(this.LIMITE, this.pagina) == null || this.gestionarBeneficiarios.listaBeneficiariosPaginado(this.LIMITE, this.pagina).isEmpty()) {
                this.btnAtrasActionPerformed(null);
            } else {
                cargarBeneficiariosEnTabla();
            }
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Administracion Beneficarios");

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
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido Paterno", "Apellido Materno", "Clave contrato", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblBeneficiarios);

        Agrupador.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 880, 360));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Beneficiarios");
        Agrupador.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 180, -1));

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
            .addComponent(Agrupador, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:

        CrearBeneficiario crear = new CrearBeneficiario(gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos, gestionarBeneficiarios);

        crear.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        if (pagina > 1) {
            pagina--;
            cargarBeneficiariosEnTabla();
            estadoPagina();
        }

    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
        pagina++;
        cargarBeneficiariosEnTabla();
        estadoPagina();
    }//GEN-LAST:event_btnSiguienteActionPerformed


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
