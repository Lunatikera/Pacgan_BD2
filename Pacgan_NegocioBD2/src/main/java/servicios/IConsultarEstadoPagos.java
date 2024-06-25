/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servicios;

import dtos.EstatusDTO;
import dtos.Pago_EstadoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author triny
 */
public interface IConsultarEstadoPagos {

    public List<Pago_EstadoDTO> obtenerHistorialDeEstados(Long pagoId) throws NegocioException;

    public Pago_EstadoDTO obtenerEstadoDelPago(Long pagoId) throws NegocioException;

    public EstatusDTO consultarEstatusPorID(Long id) throws NegocioException;

    public List<EstatusDTO> listaEstatus() throws NegocioException;

}
