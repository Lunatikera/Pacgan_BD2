/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import convertidores.ConvertidorPago;
import static convertidores.ConvertidorPago.convertirEntidadADTO;
import dtos.PagoDTO;
import entidades.PagoEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IConsultarPagoBO;
import interfaces.IPagoDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ConsultarPagoBO implements IConsultarPagoBO {

    IPagoDAO pagoDAO;

    public ConsultarPagoBO(IPagoDAO pagoDAO) {
        this.pagoDAO = pagoDAO;
    }

    @Override
    public PagoDTO consultarPagoPorID(Long id) throws NegocioException {
     try {
            PagoEntidad pagoEntidad = pagoDAO.consultarPagoPorID(id);
            if (pagoEntidad == null) {
                throw new NegocioException("Pago no encontrado");
            }
            return convertirEntidadADTO(pagoEntidad);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar pago", e);
        }
    }

    @Override
    public List<PagoDTO> listaPagos() throws NegocioException {
        try {
            List<PagoEntidad> listaPagosEntidad = pagoDAO.listaPagos();
            List<PagoDTO> listaPagosDTO = new ArrayList<>();
            for (PagoEntidad pagoEntidad : listaPagosEntidad) {
                listaPagosDTO.add(ConvertidorPago.convertirEntidadADTO(pagoEntidad));
            }
            return listaPagosDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar lista de pagos", e);
        }
    }
}