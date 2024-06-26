/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import dtos.AbonoDTO;
import excepciones.NegocioException;
import interfaces.IAgregarAbonoBO;
import interfaces.IConsultarAbonoBO;
import interfaces.IEliminarAbonoBO;
import java.util.List;
import servicios.IGestionarAbonos;

/**
 *
 * @author Usuario
 */
public class GestionarAbonosFacade implements IGestionarAbonos {

    private IAgregarAbonoBO agregarAbonoBO;
    private IConsultarAbonoBO consultarAbonoBO;
    private IEliminarAbonoBO eliminarAbonoBO;

    public GestionarAbonosFacade(IAgregarAbonoBO agregarAbonoBO, IConsultarAbonoBO consultarAbonoBO, IEliminarAbonoBO eliminarAbonoBO) {
        this.agregarAbonoBO = agregarAbonoBO;
        this.consultarAbonoBO = consultarAbonoBO;
        this.eliminarAbonoBO = eliminarAbonoBO;
    }

    @Override
    public void agregarAbono(AbonoDTO abono) throws NegocioException {
        agregarAbonoBO.agregarAbono(abono);
    }

    @Override
    public AbonoDTO consultarAbonoPorID(Long id) throws NegocioException {
        return consultarAbonoBO.consultarAbonoPorID(id);
    }

    @Override
    public List<AbonoDTO> listaAbonos() throws NegocioException {
        return consultarAbonoBO.listaAbonos();
    }

    @Override
    public void eliminarAbono(Long id) throws NegocioException {
        eliminarAbonoBO.eliminarAbono(id);
    }

    @Override
    public List<AbonoDTO> listaAbonosPaginado(int limite, int numeroPagina) throws NegocioException {
        return consultarAbonoBO.listaAbonosPaginado(limite, numeroPagina);
    }

    @Override
    public List<AbonoDTO> listaAbonosPaginadoPorPago(int limite, int numeroPagina, Long pagoId) throws NegocioException {
        return consultarAbonoBO.listaAbonosPaginadoPorPago(limite, numeroPagina, pagoId);
    }

}
