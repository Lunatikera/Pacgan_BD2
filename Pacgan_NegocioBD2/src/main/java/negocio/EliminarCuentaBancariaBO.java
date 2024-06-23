/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import entidades.CuentaBancariaEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.ICuentaBancariaDAO;
import interfaces.IEliminarCuentaBancariaBO;

/**
 *
 * @author Usuario
 */
public class EliminarCuentaBancariaBO implements IEliminarCuentaBancariaBO {

    ICuentaBancariaDAO cuentaBancariaDAO;

    public EliminarCuentaBancariaBO(ICuentaBancariaDAO cuentaBancariaDAO) {
        this.cuentaBancariaDAO = cuentaBancariaDAO;
    }

    @Override
    public void eliminarCuentaBancaria(Long id) throws NegocioException {
        try {
            CuentaBancariaEntidad cuentaBancaria = cuentaBancariaDAO.consultarCuentaBancariaPorID(id);
            validarAbono(cuentaBancaria);
            cuentaBancariaDAO.eliminarCuentaBancaria(cuentaBancaria.getId());

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar la cuenta", ex);
        }
    }

    private void validarAbono(CuentaBancariaEntidad cuentaBancaria) throws NegocioException {

        if (cuentaBancaria == null) {
            throw new NegocioException("la Cuenta no existe.");
        }
    }
}
