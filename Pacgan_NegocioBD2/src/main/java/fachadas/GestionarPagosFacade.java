/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import dtos.PagoDTO;
import dtos.TipoPagoDTO;
import excepciones.NegocioException;
import interfaces.IConsultarPagoBO;
import interfaces.IConsultarTipoPagoBO;
import interfaces.ICrearPagoBO;
import interfaces.IEditarPagoBO;
import interfaces.IEliminarPagoBO;
import java.util.List;
import servicios.IGestionarPagos;

/**
 *
 * @author Usuario
 */
public class GestionarPagosFacade implements IGestionarPagos {

    private ICrearPagoBO crearPagoBO;
    private IConsultarPagoBO consultarPagoBO;
    private IEditarPagoBO editarPagoBO;
    private IEliminarPagoBO eliminarPagoBO;
    private IConsultarTipoPagoBO consultarTipoPagoBO;

    public GestionarPagosFacade(ICrearPagoBO crearPagoBO, IConsultarPagoBO consultarPagoBO, IEditarPagoBO editarPagoBO, IEliminarPagoBO eliminarPagoBO, IConsultarTipoPagoBO consultarTipoPagoBO) {
        this.crearPagoBO = crearPagoBO;
        this.consultarPagoBO = consultarPagoBO;
        this.editarPagoBO = editarPagoBO;
        this.eliminarPagoBO = eliminarPagoBO;
        this.consultarTipoPagoBO=consultarTipoPagoBO;
    }

    @Override
    public void crearPagoBO(PagoDTO pago) throws NegocioException {
        crearPagoBO.crearPago(pago);
    }

    @Override
    public PagoDTO consultarPagoPorID(Long id) throws NegocioException {
        return consultarPagoBO.consultarPagoPorID(id);
    }

    @Override
    public List<PagoDTO> listaPagos() throws NegocioException {
        return consultarPagoBO.listaPagos();
    }

    @Override
    public void editarPago(PagoDTO pago) throws NegocioException {
        editarPagoBO.editarPago(pago);
    }

    @Override
    public void eliminarPago(Long id) throws NegocioException {
        eliminarPagoBO.eliminarPago(id);
    }

    @Override
    public List<PagoDTO> listaPagosPaginado(int numeroPagina, int tamanoPagina) throws NegocioException {
        return consultarPagoBO.listaPagosPaginado(numeroPagina, tamanoPagina);
    }

    @Override
    public TipoPagoDTO consultarTipoPagoPorID(Long id) throws NegocioException {
        return consultarTipoPagoBO.consultarTipoPagoPorID(id);
    }

    @Override
    public List<TipoPagoDTO> listaTiposPago() throws NegocioException {
        return consultarTipoPagoBO.listaTiposPago();
    }

}
