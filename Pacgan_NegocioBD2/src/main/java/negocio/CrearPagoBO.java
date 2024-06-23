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
import interfaces.ICrearPagoBO;
import interfaces.IPagoDAO;

/**
 *
 * @author Usuario
 */
public class CrearPagoBO implements ICrearPagoBO {

    IPagoDAO pagoDAO;

    public CrearPagoBO(IPagoDAO pagoDAO) {
        this.pagoDAO = pagoDAO;
    }

    @Override
    public void crearPago(PagoDTO pago) throws NegocioException {
        validarPago(pago);
        try {
            PagoEntidad pagoEntidad = convertirDTOAEntidad(pago);
            pagoDAO.agregarPago(pagoEntidad);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al crear pago", e);
        }
    }

    private void validarPago(PagoDTO pago) throws NegocioException {
        if (pago == null) {
        throw new NegocioException("Pago no puede ser nulo");
    }
    
    if (pago.getMonto() <= 0) {
        throw new NegocioException("Monto de pago debe ser mayor a cero");
    }
    
    if (pago.getComprobante() == null || pago.getComprobante().length == 0) {
        throw new NegocioException("Comprobante de pago no puede ser nulo o vacío");
    }
    
    if (pago.getTipoPagoId() == null) {
        throw new NegocioException("Tipo de pago no puede ser nulo");
    }
    
    if (pago.getBeneficiarioId() == null) {
        throw new NegocioException("Beneficiario no puede ser nulo");
    }
    
    if (pago.getCuentaBancariaId() == null) {
        throw new NegocioException("Cuenta bancaria no puede ser nula");
    }
    
    if (pago.getAbonoIds() == null || pago.getAbonoIds().isEmpty()) {
        throw new NegocioException("Abonos no pueden ser nulos o vacíos");
    }
    
    if (pago.getEstatusIds() == null || pago.getEstatusIds().isEmpty()) {
        throw new NegocioException("Estatus no pueden ser nulos o vacíos");
    }
    }
}
