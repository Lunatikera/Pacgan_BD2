/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import static convertidores.ConvertidorPago.convertirDTOAEntidad;
import dtos.PagoDTO;
import entidades.PagoEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IEditarPagoBO;
import interfaces.IPagoDAO;

/**
 *
 * @author Usuario
 */
public class EditarPagoBO implements IEditarPagoBO {

    IPagoDAO pagoDAO;

    public EditarPagoBO(IPagoDAO pagoDAO) {
        this.pagoDAO = pagoDAO;
    }

    @Override
    public void editarPago(PagoDTO pago) throws NegocioException {
        validarPago(pago);
        try {
            PagoEntidad pagoEntidad = convertirDTOAEntidad(pago);
            pagoDAO.editarPago(pagoEntidad);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al editar pago", e);
        }
    }

    private void validarPago(PagoDTO pago) throws NegocioException {
        if (pago == null) {
            throw new NegocioException("Pago no puede ser nulo");
        }

        if (pago.getPagoId() == null) {
            throw new NegocioException("Id de pago no puede ser nulo");
        }

        if (pago.getMonto() <= 0) {
            throw new NegocioException("Monto de pago debe ser mayor a cero");
        }

        if (pago.getBeneficiarioId() == null) {
            throw new NegocioException("Id de beneficiario no puede ser nulo");
        }

        // Add more validation rules as needed
    }
}
