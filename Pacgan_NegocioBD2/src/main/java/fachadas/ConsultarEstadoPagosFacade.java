/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import dtos.Pago_EstadoDTO;
import excepciones.NegocioException;
import interfaces.IHistorialEstadoPagoBO;
import interfaces.IVerEstadoPagoBO;
import java.util.List;
import negocio.HistorialEstadoPagoBO;
import servicios.IConsultarEstadoPagos;

/**
 *
 * @author triny
 */
public class ConsultarEstadoPagosFacade implements IConsultarEstadoPagos {

    private IVerEstadoPagoBO verEstadoPagoBO;
    private IHistorialEstadoPagoBO historialEstadoPagoBO;

    public ConsultarEstadoPagosFacade(IVerEstadoPagoBO verEstadoPagoBO, IHistorialEstadoPagoBO historialEstadoPagoBO) {
        this.verEstadoPagoBO = verEstadoPagoBO;
        this.historialEstadoPagoBO = historialEstadoPagoBO;
    }

    @Override
    public List<Pago_EstadoDTO> obtenerHistorialDeEstados(long pagoId) throws NegocioException {
       return historialEstadoPagoBO.obtenerHistorialDeEstados(pagoId);
    }

    @Override
    public Pago_EstadoDTO obtenerEstadoDelPago(long pagoId) throws NegocioException {
        return verEstadoPagoBO.obtenerEstadoDelPago(pagoId);
    }

}
