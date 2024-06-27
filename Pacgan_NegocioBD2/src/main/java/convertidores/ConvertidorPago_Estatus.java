/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package convertidores;

import daos.PagoDAO;
import dtos.Pago_EstadoDTO;
import entidades.Pago_EstatusEntidad;
import excepciones.PersistenciaException;
import interfaces.IEstatusDAO;
import interfaces.IPagoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author triny
 */
public class ConvertidorPago_Estatus {

    static IPagoDAO pagoDAO;
    static IEstatusDAO estatusDAO;
    
    public ConvertidorPago_Estatus(IPagoDAO pagoDAO,  IEstatusDAO estatusDAO) {
     this.pagoDAO=pagoDAO;
     this.estatusDAO=estatusDAO;
    }
    

    
    public static Pago_EstadoDTO convertirEntidadADTO(Pago_EstatusEntidad pagos_estatus) {
        Pago_EstadoDTO dto = new Pago_EstadoDTO();
        dto.setIdPago(pagos_estatus.getPagoEstatus().getId()); // Asigna el ID del pago
        dto.setMensaje(pagos_estatus.getMensaje());
        dto.setFechaHora(pagos_estatus.getFechaHora());
        dto.setIdEstatus(pagos_estatus.getEstatus().getId()); // Asigna el ID del estatus
        return dto;
    }
    public static Pago_EstatusEntidad convertirDTOaEntidad(Pago_EstadoDTO pagos_estatus) {
        try {
            Pago_EstatusEntidad pago= new Pago_EstatusEntidad();
            pago.setEstatus(estatusDAO.consultarEstatusPorID(pagos_estatus.getIdEstatus()));
            pago.setPagoEstatus(pagoDAO.consultarPagoPorID(pagos_estatus.getIdPago()));
            pago.setMensaje(pagos_estatus.getMensaje());
            
            return pago;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ConvertidorPago_Estatus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
