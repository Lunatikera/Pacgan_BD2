/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package convertidores;

import dtos.AbonoDTO;
import entidades.AbonoEntidad;
import entidades.PagoEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IConsultarPagoBO;
import interfaces.IPagoDAO;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 *
 * @author Usuario
 */
public class ConvertidorAbono {

    private static IPagoDAO pagoDAO;

    public ConvertidorAbono(IPagoDAO pagoDAO) {
        this.pagoDAO = pagoDAO;
    }

    public static AbonoEntidad convertirDTOAEntidad(AbonoDTO abonoDTO) throws PersistenciaException {
        AbonoEntidad abonoEntidad = new AbonoEntidad();

        PagoEntidad pagoEntidad = pagoDAO.consultarPagoPorID(abonoDTO.getPagoId());

        abonoEntidad.setMonto(abonoDTO.getMonto());
        
        LocalDateTime fechaHora = abonoDTO.getFecha().atTime(abonoDTO.getHora());

        abonoEntidad.setFechaHora(fechaHora);

        if (pagoEntidad != null) {
            abonoEntidad.setPagoAbono(pagoEntidad);
        } else {
            throw new IllegalArgumentException("El pago asociado no existe.");
        }

        return abonoEntidad;
    }

    public static AbonoDTO convertirEntidadADTO(AbonoEntidad abonoEntidad) {
     
        AbonoDTO abonoDTO = new AbonoDTO();
        
        abonoDTO.setAbonoId(abonoEntidad.getId());
        abonoDTO.setMonto(abonoEntidad.getMonto());
        abonoDTO.setFecha(abonoEntidad.getFechaHora().toLocalDate());
        abonoDTO.setHora(abonoEntidad.getFechaHora().toLocalTime());
        
        return abonoDTO;
    }
}
