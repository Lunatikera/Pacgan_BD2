/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import static convertidores.ConvertidorAbono.convertirDTOAEntidad;
import dtos.AbonoDTO;
import entidades.AbonoEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IAbonoDAO;
import interfaces.IAgregarAbonoBO;
import java.math.BigDecimal;
import javax.persistence.PersistenceException;

/**
 *
 * @author Usuario
 */
public class AgregarAbonoBO implements IAgregarAbonoBO {

    IAbonoDAO abonoDAO;

    public AgregarAbonoBO(IAbonoDAO abonoDAO) {
        this.abonoDAO = abonoDAO;
    }

    @Override
    public void agregarAbono(AbonoDTO abono) throws NegocioException {
        validarAbonoDTO(abono);
        
        try {
            AbonoEntidad abonoEntidad = convertirDTOAEntidad(abono);
            
            abonoDAO.agregarAbono(abonoEntidad);
            
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al guardar el abono.", e);
        }
    }

    private void validarAbonoDTO(AbonoDTO abonoDTO) throws NegocioException {
        if (abonoDTO == null) {
            throw new NegocioException("El abono no puede ser nulo.");
        }
        if (abonoDTO.getPagoId() == null || abonoDTO.getPagoId() <= 0) {
            throw new NegocioException("El ID del pago asociado no es válido.");
        }
         if (abonoDTO.getMonto().compareTo(BigDecimal.ZERO) <=0) {
            throw new NegocioException("El monto del abono debe ser mayor que cero.");
        }
    }

}
