/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import static convertidores.ConvertidorTipoPago.convertirEntidadADTO;
import dtos.TipoPagoDTO;
import entidades.TipoPagoEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IConsultarTipoPagoBO;
import interfaces.ITipoPagoDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class ConsultarTipoPagoBO implements IConsultarTipoPagoBO {
    ITipoPagoDAO tipoPagoDAO;

    public ConsultarTipoPagoBO(  ITipoPagoDAO tipoPagoDAO) {
        this.tipoPagoDAO = tipoPagoDAO;
    }  

    @Override
    public TipoPagoDTO consultarTipoPagoPorID(Long id) throws NegocioException {
      try {
        TipoPagoEntidad tipoPagoEntidad = tipoPagoDAO.consultarTipoPagoPorID(id); // Método de ITipoPagoDAO

        if (tipoPagoEntidad != null) {
            // Convertir TipoPagoEntidad a TipoPagoDTO
            TipoPagoDTO tipoPagoDTO = convertirEntidadADTO(tipoPagoEntidad);
            return tipoPagoDTO;
        } else {
            throw new NegocioException("El tipo de pago con ID " + id + " no existe.");
        }
    } catch (PersistenciaException e) {
        throw new NegocioException("Error al consultar el tipo de pago por ID", e);
    }
}


    @Override
public List<TipoPagoDTO> listaTiposPago() throws NegocioException {
    try {
        List<TipoPagoEntidad> tiposPagoEntidad = tipoPagoDAO.listaTiposPago(); 

        // Convertir lista de TipoPagoEntidad a lista de TipoPagoDTO
        List<TipoPagoDTO> tiposPagoDTO = new ArrayList<>();
        for (TipoPagoEntidad tipoPagoEntidad : tiposPagoEntidad) {
            tiposPagoDTO.add(convertirEntidadADTO(tipoPagoEntidad));
        }

        return tiposPagoDTO;
    } catch (PersistenciaException e) {
        throw new NegocioException("Error al obtener la lista de tipos de pago", e);
    }
}

}
