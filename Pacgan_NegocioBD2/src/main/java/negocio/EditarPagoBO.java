/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import static convertidores.ConvertidorPago.convertirDTOAEntidad;
import dtos.PagoDTO;
import dtos.Pago_EstadoDTO;
import entidades.PagoEntidad;
import entidades.Pago_EstatusEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IEditarPagoBO;
import interfaces.IPagoDAO;
import interfaces.IPago_EstatusDAO;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class EditarPagoBO implements IEditarPagoBO {

    IPagoDAO pagoDAO;
    IPago_EstatusDAO pago_EstatusDAO;

    public EditarPagoBO(IPagoDAO pagoDAO, IPago_EstatusDAO pago_EstatusDAO) {
        this.pagoDAO = pagoDAO;
        this.pago_EstatusDAO = pago_EstatusDAO;
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

        if (pago.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegocioException("Monto de pago debe ser mayor a cero");
        }

        if (pago.getBeneficiarioId() == null) {
            throw new NegocioException("Id de beneficiario no puede ser nulo");
        }

    }

    @Override
    public void agregarPagoEstatus(Pago_EstadoDTO pagoEstatus) throws NegocioException {
        try {
            Pago_EstatusEntidad pagoestatusEntidad = convertidores.ConvertidorPago_Estatus.convertirDTOaEntidad(pagoEstatus);
            pago_EstatusDAO.agregarPagoEstatus(pagoestatusEntidad);
        } catch (PersistenciaException e) {
            Logger.getLogger(EditarPagoBO.class.getName()).log(Level.SEVERE, null, e);

            throw new NegocioException("Error al agregear el estado del pago", e);
        }
    }
}
