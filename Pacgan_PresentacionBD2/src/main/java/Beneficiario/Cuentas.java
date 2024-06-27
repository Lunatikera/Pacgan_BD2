/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Beneficiario;

import dtos.BeneficiarioDTO;
import dtos.CuentaBancariaDTO;
import excepciones.NegocioException;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import servicios.IConsultarEstadoPagos;
import servicios.IGestionarAbonos;
import servicios.IGestionarCuentasBancarias;
import servicios.IGestionarPagos;
import utileria.JButtonRenderer;
import utileria.JButtonCellEditor;

/**
 *
 * @author jesus
 */
public class Cuentas extends javax.swing.JFrame {

    IGestionarCuentasBancarias gestionarCuentasBancarias;
    IGestionarPagos gestionarPagos;
    IConsultarEstadoPagos consultarEstadoPagos;
    IGestionarAbonos gestionarAbonos;
    BeneficiarioDTO beneficiario;

    private int pagina = 1;
    private final int LIMITE = 5;
    List<Long> cuentasIds;

    /**
     * Creates new form Cuentas
     *
     * @param gestionarCuentasBancarias
     */
    public Cuentas(IGestionarCuentasBancarias gestionarCuentasBancarias, IGestionarPagos gestionarPagos, IConsultarEstadoPagos consultarEstadoPagos, IGestionarAbonos gestionarAbonos, BeneficiarioDTO beneficiario) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.gestionarCuentasBancarias = gestionarCuentasBancarias;
        this.gestionarPagos = gestionarPagos;
        this.consultarEstadoPagos = consultarEstadoPagos;
        this.gestionarAbonos = gestionarAbonos;
        this.beneficiario = beneficiario;
        this.cuentasIds = new ArrayList<>();
        personalizador();
        agregarOpcionesMenu();
        cargarMetodosIniciales();

    }

    public void personalizador() {
        panelMenu.setBackground(Color.decode("#142132"));
        btnCrearCuenta.setBackground(Color.decode("#142132"));
        btnAtras.setBackground(Color.decode("#142132"));
        btnSiguiente.setBackground(Color.decode("#142132"));

    }

    public void cargarMetodosIniciales() {
        //this.cargarConfiguracionInicialPantalla();
        this.cargarCuentasEnTabla();
        this.estadoPagina();
        this.configurarBotones();
    }

    public void cargarCuentasEnTabla() {
        try {
            cuentasIds.clear();
            List<CuentaBancariaDTO> cuentaLista = this.gestionarCuentasBancarias.listaPaginadoCuentasPorBeneficiario(this.LIMITE, this.pagina, beneficiario.getBeneficiarioId());
            if (cuentaLista.isEmpty()) {
                return;
            }
            for (CuentaBancariaDTO cuentaDTO : cuentaLista) {
                cuentasIds.add(cuentaDTO.getCuentaBancariaId());
            }
            if (cuentaLista.isEmpty() && pagina == 1) {
                JOptionPane.showMessageDialog(this, "No tiene ninguna cuenta Registrada, Por favor Registre una", "Información", JOptionPane.ERROR_MESSAGE);

            }
            this.llenarTablaCuentas(cuentaLista);
        } catch (NegocioException ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
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

    private void llenarTablaCuentas(List<CuentaBancariaDTO> cuentasLista) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblCuentas.getModel();

        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }

        if (cuentasLista != null) {
            cuentasLista.forEach(row
                    -> {
                Object[] fila = new Object[5];
                fila[0] = row.getNumeroCuenta();
                fila[1] = row.getClabe();
                fila[2] = row.getNombreBanco();
                modeloTabla.addRow(fila);
            });
        }
    }

    private void configurarBotones() {
        // ActionListener para botón Editar
        ActionListener editarListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblCuentas.getSelectedRow();
                if (selectedRow != -1) {
                    Long selectedId = cuentasIds.get(selectedRow);
                    editar(selectedId);
                }
            }
        };

        // ActionListener para botón Eliminar
        ActionListener eliminarListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblCuentas.getSelectedRow();
                if (selectedRow != -1) {
                    Long selectedId = cuentasIds.get(selectedRow);
                    eliminar(selectedId);
                }
            }
        };

        // Configurar columna de Editar
        int indiceColumnaEditar = 3; // Suponiendo que esta es la tercera columna (índice 2)
        TableColumnModel modeloColumnas = tblCuentas.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellRenderer(new JButtonRenderer("Editar"));
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellEditor(new utileria.JButtonCellEditor("Editar", editarListener));

        // Configurar columna de Eliminar
        int indiceColumnaEliminar = 4; // Suponiendo que esta es la cuarta columna (índice 3)
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellRenderer(new JButtonRenderer("Eliminar"));
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellEditor(new utileria.JButtonCellEditor("Eliminar", eliminarListener));
    }

    public void editar(Long id) {
        try {
            if (id == 0) {
                throw new NegocioException("Por favor seleccione una Cuenta");
            }
            CuentaBancariaDTO cuentaDTO = gestionarCuentasBancarias.consultarCuentaBancariaPorID(id);

            ModificarCuenta editarCuenta = new ModificarCuenta(gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos, gestionarAbonos, beneficiario, cuentaDTO);
            editarCuenta.setVisible(true);
            this.dispose();

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminar(Long id) {
        try {

            if (id == 0) {
                throw new NegocioException("Por favor seleccione un Pago");
            }

            int confirmacion = JOptionPane.showOptionDialog(this,
                    "¿Está seguro de que desea eliminar esta Cuenta?",
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
            gestionarCuentasBancarias.eliminarCuentaBancaria(id);

            if (this.gestionarCuentasBancarias.listaPaginadoCuentasPorBeneficiario(this.LIMITE, this.pagina, beneficiario.getBeneficiarioId()) == null || this.gestionarCuentasBancarias.listaPaginadoCuentasPorBeneficiario(this.LIMITE, this.pagina, beneficiario.getBeneficiarioId()).isEmpty()) {
                this.btnAtrasActionPerformed(null);
            } else {
                cargarCuentasEnTabla();

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
        panelMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCuentas = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        lblPagina = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        btnCrearCuenta = new javax.swing.JButton();
        MenuBarAdmin = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Agrupador.setBackground(new java.awt.Color(255, 255, 255));
        Agrupador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelMenu.setBackground(new java.awt.Color(0, 51, 102));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/LogoEsquina.jpg"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mis Cuentas");

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
                        .addGap(263, 263, 263)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        tblCuentas.setBackground(new java.awt.Color(234, 234, 234));
        tblCuentas.setForeground(new java.awt.Color(0, 0, 0));
        tblCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero de Cuenta", "Clabe", "Banco", "", ""
            }
        ));
        jScrollPane1.setViewportView(tblCuentas);

        Agrupador.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 880, 350));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 102));
        jLabel5.setText(" Cuentas Bancarias");
        Agrupador.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 490, 30));

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

        btnCrearCuenta.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearCuenta.setText("Crear Cuenta");
        btnCrearCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCuentaActionPerformed(evt);
            }
        });
        Agrupador.add(btnCrearCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 130, 120, 30));

        setJMenuBar(MenuBarAdmin);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Agrupador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Agrupador, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
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
            if (this.gestionarCuentasBancarias.listaPaginadoCuentasPorBeneficiario(this.LIMITE, this.pagina + 1, beneficiario.getBeneficiarioId()) == null
                    || this.gestionarCuentasBancarias.listaPaginadoCuentasPorBeneficiario(this.LIMITE, this.pagina + 1, beneficiario.getBeneficiarioId()).isEmpty()) {
                btnSiguiente.setEnabled(false);
            }
        } catch (NegocioException ex) {
            System.out.println(ex);
        }

    }
    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.pagina = this.pagina - 1;
        this.cargarCuentasEnTabla();
        this.estadoPagina();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        this.pagina = this.pagina + 1;
        this.cargarCuentasEnTabla();
        this.estadoPagina();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnCrearCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCuentaActionPerformed
        CrearCuenta cuenta = new CrearCuenta(gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos, gestionarAbonos, beneficiario);
        cuenta.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCrearCuentaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Agrupador;
    private javax.swing.JMenuBar MenuBarAdmin;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnCrearCuenta;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JTable tblCuentas;
    // End of variables declaration//GEN-END:variables
}
