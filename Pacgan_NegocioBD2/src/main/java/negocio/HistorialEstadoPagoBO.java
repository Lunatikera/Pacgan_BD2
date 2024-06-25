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
import interfaces.IHistorialEstadoPagoBO;
import interfaces.IPagoDAO;
import interfaces.IPago_EstatusDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author triny
 */
public class HistorialEstadoPagoBO implements IHistorialEstadoPagoBO {
    
    IPago_EstatusDAO pago_EstatusDAO;

    public HistorialEstadoPagoBO(IPago_EstatusDAO pago_EstatusDAO) {
        this.pago_EstatusDAO = pago_EstatusDAO;
    }

       @Override
    public List<Pago_EstadoDTO> obtenerHistorialDeEstados(Long pagoId) throws NegocioException {
        try {
            List<Pago_EstatusEntidad> historialEntidades = pago_EstatusDAO.obtenerHistorialDeEstados(pagoId);
            List<Pago_EstadoDTO> historialDTO = new ArrayList<>();

            for (Pago_EstatusEntidad entidad : historialEntidades) {
                Pago_EstadoDTO dto = convertirEntidadADTO(entidad);
                historialDTO.add(dto);
            }
            
            return historialDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener el historial de estados del pago", ex);
        }
    }
}