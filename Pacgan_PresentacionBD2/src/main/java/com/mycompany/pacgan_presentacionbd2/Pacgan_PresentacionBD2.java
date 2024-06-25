/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.pacgan_presentacionbd2;

import Beneficiario.Pagos;
import Inicio.LogIn;
import daos.AbonoDAO;
import daos.BeneficiarioDAO;
import daos.ConexionBD;
import daos.CuentaBancariaDAO;
import daos.EstatusDAO;
import daos.PagoDAO;
import daos.Pago_EstatusDAO;
import daos.TipoPagoDAO;
import fachadas.ConsultarEstadoPagosFacade;
import fachadas.GestionarAbonosFacade;
import fachadas.GestionarBeneficiariosFacade;
import fachadas.GestionarCuentasBancariasFacade;
import fachadas.GestionarPagosFacade;
import insertadores.InsertarBeneficiario;
import insertadores.InsertarEstatusPago;
import interfaces.IAbonoDAO;
import interfaces.IAgregarAbonoBO;
import interfaces.IAgregarBeneficiarioBO;
import interfaces.IAgregarCuentaBancariaBO;
import interfaces.IBeneficiarioDAO;
import interfaces.IConexionBD;
import interfaces.IConsultarAbonoBO;
import interfaces.IConsultarBeneficiarioBO;
import interfaces.IConsultarCuentaBancariaBO;
import interfaces.IConsultarEstatusBO;
import interfaces.IConsultarPagoBO;
import interfaces.ICrearPagoBO;
import interfaces.ICuentaBancariaDAO;
import interfaces.IEditarBeneficiarioBO;
import interfaces.IEditarCuentaBancariaBO;
import interfaces.IEditarPagoBO;
import interfaces.IEliminarAbonoBO;
import interfaces.IEliminarBeneficiarioBO;
import interfaces.IEliminarCuentaBancariaBO;
import interfaces.IEliminarPagoBO;
import interfaces.IEstatusDAO;
import interfaces.IHistorialEstadoPagoBO;
import interfaces.IIniciarSesionBO;
import interfaces.IPagoDAO;
import interfaces.IPago_EstatusDAO;
import interfaces.ITipoPagoDAO;
import negocio.AgregarAbonoBO;
import negocio.AgregarBeneficiarioBO;
import negocio.AgregarCuentaBancariaBO;
import negocio.ConsultarAbonoBO;
import negocio.ConsultarBeneficiarioBO;
import negocio.ConsultarCuentaBancariaBO;
import negocio.ConsultarPagoBO;
import negocio.CrearPagoBO;
import negocio.EditarBeneficiarioBO;
import negocio.EditarCuentaBancariaBO;
import negocio.EditarPagoBO;
import negocio.EliminarAbonoBO;
import negocio.EliminarBeneficiarioBO;
import negocio.EliminarCuentaBancariaBO;
import negocio.EliminarPagoBO;
import negocio.IniciarSesionBO;
import negocio.VerEstadoPagoBO;
import servicios.IGestionarAbonos;
import servicios.IGestionarBeneficiarios;
import servicios.IGestionarCuentasBancarias;
import servicios.IGestionarPagos;
import interfaces.IVerEstadoPagoBO;
import interfaces.IinsertarBeneficiario;
import interfaces.IinsertarEstatusPago;
import negocio.ConsultarEstatusBO;
import negocio.HistorialEstadoPagoBO;
import servicios.IConsultarEstadoPagos;

/**
 *
 * @author Usuario
 */
public class Pacgan_PresentacionBD2 {

    public static void main(String[] args) {

        //Conexion
        IConexionBD conexion = new ConexionBD();

        //DAOs
        IPagoDAO pagoDAO = new PagoDAO(conexion);
        IBeneficiarioDAO beneficiarioDAO = new BeneficiarioDAO(conexion);
        ICuentaBancariaDAO cuentaBancariaDAO = new CuentaBancariaDAO(conexion);
        IAbonoDAO abonoDAO = new AbonoDAO(conexion);
        IEstatusDAO estatusDAO = new EstatusDAO(conexion);
        ITipoPagoDAO tipoPagoDAO = new TipoPagoDAO(conexion);
        IPago_EstatusDAO pago_EstatusDAO = new Pago_EstatusDAO(conexion);

        //BOs
        IAgregarAbonoBO agregarAbonoBO = new AgregarAbonoBO(abonoDAO);
        IAgregarBeneficiarioBO agregarBeneficiarioBO = new AgregarBeneficiarioBO(beneficiarioDAO);
        IAgregarCuentaBancariaBO agregarCuentaBancariaBO = new AgregarCuentaBancariaBO(cuentaBancariaDAO);
        IConsultarAbonoBO consultarAbonoBO = new ConsultarAbonoBO(abonoDAO);
        IConsultarBeneficiarioBO consultarBeneficiarioBO = new ConsultarBeneficiarioBO(beneficiarioDAO);
        IConsultarEstatusBO consultarEstatusBO = new ConsultarEstatusBO(estatusDAO);
        IConsultarCuentaBancariaBO consultarCuentaBancariaBO = new ConsultarCuentaBancariaBO(cuentaBancariaDAO);
        IConsultarPagoBO consultarPagoBO = new ConsultarPagoBO(pagoDAO);
        ICrearPagoBO crearPagoBO = new CrearPagoBO(pagoDAO);
        IEditarBeneficiarioBO editarBeneficiarioBO = new EditarBeneficiarioBO(beneficiarioDAO);
        IEditarCuentaBancariaBO editarCuentaBancariaBO = new EditarCuentaBancariaBO(cuentaBancariaDAO);
        IEditarPagoBO editarPagoBO = new EditarPagoBO(pagoDAO);
        IEliminarAbonoBO eliminarAbonoBO = new EliminarAbonoBO(abonoDAO);
        IEliminarBeneficiarioBO eliminarBeneficiarioBO = new EliminarBeneficiarioBO(beneficiarioDAO);
        IEliminarCuentaBancariaBO eliminarCuentaBancariaBO = new EliminarCuentaBancariaBO(cuentaBancariaDAO);
        IEliminarPagoBO eliminarPagoBO = new EliminarPagoBO(pagoDAO);
        IIniciarSesionBO iniciarSesionBO = new IniciarSesionBO();
        IinsertarBeneficiario insertarBeneficiario= new InsertarBeneficiario(beneficiarioDAO);
        IinsertarEstatusPago insertarEstatusPago= new InsertarEstatusPago(tipoPagoDAO, estatusDAO); 
        IVerEstadoPagoBO verEstadoPagoBO = new VerEstadoPagoBO(pago_EstatusDAO);
        IHistorialEstadoPagoBO historialEstadoPagoBO = new HistorialEstadoPagoBO(pago_EstatusDAO);

        //Fachadas
        IGestionarAbonos gestionarAbonos = new GestionarAbonosFacade(agregarAbonoBO, consultarAbonoBO, eliminarAbonoBO);
        IGestionarBeneficiarios gestionarBeneficiarios = new GestionarBeneficiariosFacade(agregarBeneficiarioBO, consultarBeneficiarioBO, editarBeneficiarioBO, eliminarBeneficiarioBO);
        IGestionarCuentasBancarias gestionarCuentasBancarias = new GestionarCuentasBancariasFacade(agregarCuentaBancariaBO, consultarCuentaBancariaBO, editarCuentaBancariaBO, eliminarCuentaBancariaBO);
        IGestionarPagos gestionarPagos = new GestionarPagosFacade(crearPagoBO, consultarPagoBO, editarPagoBO, eliminarPagoBO);
        IConsultarEstadoPagos consultarEstadoPagos = new ConsultarEstadoPagosFacade(verEstadoPagoBO, historialEstadoPagoBO, consultarEstatusBO);

        
        Pagos pago= new Pagos(gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos);
        pago.setVisible(true);
//        LogIn inicioSesion = new LogIn(iniciarSesionBO, insertarBeneficiario, insertarEstatusPago, gestionarAbonos, gestionarBeneficiarios, gestionarCuentasBancarias, gestionarPagos, consultarEstadoPagos);
//        inicioSesion.setVisible(true);
    }
}
