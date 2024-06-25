/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import static convertidores.ConvertidorPago_Estatus.convertirEntidadADTO;
import dtos.Pago_EstadoDTO;
import entidades.Pago_EstatusEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IPagoDAO;
import interfaces.IPago_EstatusDAO;
import interfaces.IVerEstadoPagoBO;

/**
 *
 * @author Usuario
 */
public class VerEstadoPagoBO implements IVerEstadoPagoBO {

    IPago_EstatusDAO pago_EstatusDAO;

    public VerEstadoPagoBO(IPago_EstatusDAO pago_EstatusDAO) {
        this.pago_EstatusDAO = pago_EstatusDAO;
    }

    public Pago_EstadoDTO obtenerEstadoDelPago(Long pagoId) throws NegocioException {
        try {
            Pago_EstatusEntidad estadoEntidad = pago_EstatusDAO.obtenerEstadoDelPago(pagoId);
            return convertirEntidadADTO(estadoEntidad);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener el estado del pago", ex);
        }
    }
}
