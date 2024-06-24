/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.Pago_EstadoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author triny
 */
public interface IHistorialEstadoPagoBO {

    public List<Pago_EstadoDTO> obtenerHistorialDeEstados(long pagoId) throws NegocioException;

}
