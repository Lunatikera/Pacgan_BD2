/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import dtos.EstatusDTO;
import dtos.Pago_EstadoDTO;
import excepciones.NegocioException;
import interfaces.IConsultarEstatusBO;
import interfaces.IHistorialEstadoPagoBO;
import interfaces.IVerEstadoPagoBO;
import java.util.List;
import servicios.IConsultarEstadoPagos;

/**
 *
 * @author triny
 */
public class ConsultarEstadoPagosFacade implements IConsultarEstadoPagos {

    private IVerEstadoPagoBO verEstadoPagoBO;
    private IHistorialEstadoPagoBO historialEstadoPagoBO;
    private IConsultarEstatusBO consultarEstatusBO;

    public ConsultarEstadoPagosFacade(IVerEstadoPagoBO verEstadoPagoBO, IHistorialEstadoPagoBO historialEstadoPagoBO, IConsultarEstatusBO consultarEstatusBO) {
        this.verEstadoPagoBO = verEstadoPagoBO;
        this.historialEstadoPagoBO = historialEstadoPagoBO;
        this.consultarEstatusBO = consultarEstatusBO;
    }

    @Override
    public List<Pago_EstadoDTO> obtenerHistorialDeEstados(long pagoId) throws NegocioException {
        return historialEstadoPagoBO.obtenerHistorialDeEstados(pagoId);
    }

    @Override
    public Pago_EstadoDTO obtenerEstadoDelPago(long pagoId) throws NegocioException {
        return verEstadoPagoBO.obtenerEstadoDelPago(pagoId);
    }

    @Override
    public EstatusDTO consultarEstatusPorID(Long id) throws NegocioException {
        return consultarEstatusBO.consultarEstatusPorID(id);
    }

    @Override
    public List<EstatusDTO> listaEstatus() throws NegocioException {
        return consultarEstatusBO.listaEstatus();
    }

}
