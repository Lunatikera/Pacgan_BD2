/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package convertidores;

import dtos.Pago_EstadoDTO;
import entidades.Pago_EstatusEntidad;

/**
 *
 * @author triny
 */
public class ConvertidorPago_Estatus {

    public static Pago_EstadoDTO convertirEntidadADTO(Pago_EstatusEntidad pagos_estatus) {
        Pago_EstadoDTO dto = new Pago_EstadoDTO();
        dto.setIdPago(pagos_estatus.getPagoEstatus().getId()); // Asigna el ID del pago
        dto.setMensaje(pagos_estatus.getMensaje());
        dto.setFechaHora(pagos_estatus.getFechaHora());
        dto.setIdEstatus(pagos_estatus.getEstatus().getId()); // Asigna el ID del estatus
        return dto;
    }
}
