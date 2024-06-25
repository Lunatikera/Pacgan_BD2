/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package convertidores;

import dtos.TipoPagoDTO;
import entidades.TipoPagoEntidad;

/**
 *
 * @author jesus
 */
public class ConvertidorTipoPago {

    public static TipoPagoEntidad convertirDTOaEntidad(TipoPagoDTO tipoPagoDTO) {
        TipoPagoEntidad tipoPagoEntidad = new TipoPagoEntidad();
        tipoPagoEntidad.setNombreTipo(tipoPagoDTO.getNombreTipo());
        tipoPagoEntidad.setNumeroParcialidades(tipoPagoDTO.getNumeroParcialidades());

        return tipoPagoEntidad;
    }
    
    public static TipoPagoDTO convertirEntidadADTO(TipoPagoEntidad tipoPagoEntidad) {
    TipoPagoDTO tipoPagoDTO = new TipoPagoDTO();
    tipoPagoDTO.setId(tipoPagoEntidad.getId());
    tipoPagoDTO.setNombreTipo(tipoPagoEntidad.getNombreTipo());
    tipoPagoDTO.setNumeroParcialidades(tipoPagoEntidad.getNumeroParcialidades());

    return tipoPagoDTO;
}

}
