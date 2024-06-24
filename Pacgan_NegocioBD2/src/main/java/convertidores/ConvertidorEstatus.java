/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package convertidores;

import dtos.EstatusDTO;
import entidades.EstatusEntidad;

/**
 *
 * @author jesus
 */
public class ConvertidorEstatus {

    public static EstatusEntidad convertirDTOaEntidad(EstatusDTO estatusDTO) {
        EstatusEntidad estatusEntidad = new EstatusEntidad();
        estatusEntidad.setId(estatusDTO.getId());
        estatusEntidad.setNombre(estatusDTO.getNombre());
        // No se asigna estatusId ya que no está presente en EstatusEntidad
        return estatusEntidad;
    }

}
