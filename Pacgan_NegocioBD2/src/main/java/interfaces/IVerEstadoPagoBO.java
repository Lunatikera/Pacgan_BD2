/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.Pago_EstadoDTO;
import excepciones.NegocioException;

/**
 *
 * @author Usuario
 */
public interface IVerEstadoPagoBO {

    public Pago_EstadoDTO obtenerEstadoDelPago(long pagoId) throws NegocioException;

}
