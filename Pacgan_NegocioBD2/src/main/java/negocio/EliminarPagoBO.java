/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import entidades.PagoEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IEliminarPagoBO;
import interfaces.IPagoDAO;

/**
 *
 * @author Usuario
 */
public class EliminarPagoBO implements IEliminarPagoBO {

    IPagoDAO pagoDAO;

    public EliminarPagoBO(IPagoDAO pagoDAO) {
        this.pagoDAO = pagoDAO;
    }

    @Override
    public void eliminarPago(Long id) throws NegocioException {
     try {
            PagoEntidad pago = pagoDAO.consultarPagoPorID(id);
            validarAbono(pago);
            pagoDAO.eliminarPago(pago.getId());

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar la cuenta", ex);
        }
    }

    private void validarAbono(PagoEntidad pago) throws NegocioException {

        if (pago == null) {
            throw new NegocioException("la Cuenta no existe.");
        }
    }
}
