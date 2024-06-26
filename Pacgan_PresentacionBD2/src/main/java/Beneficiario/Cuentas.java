/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Beneficiario;

import dtos.BeneficiarioDTO;
import dtos.CuentaBancariaDTO;
import excepciones.NegocioException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import servicios.IGestionarCuentasBancarias;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRender;

/**
 *
 * @author jesus
 */
public class Cuentas extends javax.swing.JFrame {

    IGestionarCuentasBancarias gestionarCuentasBancarias;
    BeneficiarioDTO beneficiario;

    /**
     * Creates new form Cuentas
     *
     * @param gestionarCuentasBancarias
     */
    public Cuentas(IGestionarCuentasBancarias gestionarCuentasBancarias, BeneficiarioDTO beneficiario) {
        this.gestionarCuentasBancarias = gestionarCuentasBancarias;
        this.beneficiario = beneficiario;

        initComponents();
        personalizador();
        agregarOpcionesMenu();

        this.cargarCuentasEnTabla();
        this.cargarConfiguracionInicialTabla();
    }

    public void personalizador() {
        panelMenu.setBackground(Color.decode("#142132"));
        btnCrearCuenta.setBackground(Color.decode("#142132"));
        btnAtras.setBackground(Color.decode("#142132"));
        btnSiguiente.setBackground(Color.decode("#142132"));

    }

    public void agregarOpcionesMenu() {

        JMenu menuPagos = new JMenu("Pagos");
        JMenuItem misPagos = new JMenuItem("Mis pagos");
        misPagos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //   Pagos Pagos = new Pagos();
                // Pagos.setVisible(true);
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
                Cuentas Cuentas = new Cuentas(gestionarCuentasBancarias, beneficiario);
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
                //Aquiva el ID del beneficiario
                if (row.getBeneficiarioId().equals(Long.valueOf(beneficiario.getBeneficiarioId()))) {

                    try {
                        CuentaBancariaDTO cuentaBancaria = gestionarCuentasBancarias.consultarCuentaBancariaPorID(row.getCuentaBancariaId());

                        Object[] fila = new Object[3];
                        fila[0] = cuentaBancaria.getNumeroCuenta();
                        fila[1] = row.getClabe();
                        fila[2] = row.getNombreBanco();

                        modeloTabla.addRow(fila);
                    } catch (NegocioException ex) {
                        Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }

    private void cargarCuentasEnTabla() {
        try {
            List<CuentaBancariaDTO> cuentaLista = this.gestionarCuentasBancarias.listaCuentasBancarias();
            this.llenarTablaCuentas(cuentaLista);
        } catch (NegocioException ex) {
            Logger.getLogger(Cuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarConfiguracionInicialTabla() {
        ActionListener onEditarClickListener = new ActionListener() {
            final int columnaNumeroCuenta = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para editar un alumno
                editar();
            }
        };
        int indiceColumnaEditar = 3;
        TableColumnModel modeloColumnas = this.tblCuentas.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellRenderer(new JButtonRender("Editar"));
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellEditor(new JButtonCellEditor("Editar",
                        onEditarClickListener));

        ActionListener onEliminarClickListener = new ActionListener() {
            final int columnaId = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para eliminar un alumno
                eliminar();
            }
        };
        int indiceColumnaEliminar = 4;
        modeloColumnas = this.tblCuentas.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellRenderer(new JButtonRender("Eliminar"));
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellEditor(new JButtonCellEditor("Eliminar",
                        onEliminarClickListener));
    }

    private String getNumeroCuentaSeleccionado() {
        int indiceFilaSeleccionada = this.tblCuentas.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblCuentas.getModel();
            int indiceColumna = 0;
            String numeroSeleccionado = (String) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumna);
            return numeroSeleccionado;
        } else {
            return null;
        }
    }

    public CuentaBancariaDTO conseguirCuenta(String numeroCuenta) {
        try {
            for (int i = 0; i < gestionarCuentasBancarias.listaCuentasBancarias().size(); i++) {
                if (gestionarCuentasBancarias.listaCuentasBancarias().get(i).getNumeroCuenta().equals(numeroCuenta)) {
                    return gestionarCuentasBancarias.listaCuentasBancarias().get(i);
                }
            }
        } catch (NegocioException ex) {
            Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void editar() {
        ModificarCuenta modificar = new ModificarCuenta(this.getNumeroCuentaSeleccionado(), gestionarCuentasBancarias, beneficiario, this.conseguirCuenta(this.getNumeroCuentaSeleccionado()));
        modificar.setVisible(true);
        dispose();
    }

    public void eliminar() {
        
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
        tblCuentas = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        btnCrearCuenta = new javax.swing.JButton();
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

        tblCuentas.setBackground(new java.awt.Color(234, 234, 234));
        tblCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Numero de Cuenta", "Clabe", "Banco", "", ""
            }
        ));
        jScrollPane1.setViewportView(tblCuentas);

        Agrupador.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 880, 350));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Adminsitrar Cuentas Bancarias");
        Agrupador.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 380, 30));

        btnCrearCuenta.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearCuenta.setText("Crear Cuenta");
        btnCrearCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCuentaActionPerformed(evt);
            }
        });
        Agrupador.add(btnCrearCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 130, 100, 30));

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
            .addComponent(Agrupador, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCuentaActionPerformed
        // TODO add your handling code here:
        CrearCuenta crearCuenta = new CrearCuenta(gestionarCuentasBancarias, beneficiario);

        crearCuenta.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCrearCuentaActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnSiguienteActionPerformed

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
