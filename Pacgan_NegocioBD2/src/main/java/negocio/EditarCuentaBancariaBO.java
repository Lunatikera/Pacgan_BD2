/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import static convertidores.ConvertidorCuentaBancaria.convertirDTOAEntidad;
import dtos.CuentaBancariaDTO;
import entidades.CuentaBancariaEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.ICuentaBancariaDAO;
import interfaces.IEditarCuentaBancariaBO;

/**
 *
 * @author Usuario
 */
public class EditarCuentaBancariaBO implements IEditarCuentaBancariaBO {

    ICuentaBancariaDAO cuentaBancariaDAO;

    public EditarCuentaBancariaBO(ICuentaBancariaDAO cuentaBancariaDAO) {
        this.cuentaBancariaDAO = cuentaBancariaDAO;
    }

    @Override
    public void editarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) throws NegocioException {
        validarCuentaBancaria(cuentaBancaria);

        try {
            CuentaBancariaEntidad cuentaBancariaEntidad = convertirDTOAEntidad(cuentaBancaria);

            cuentaBancariaDAO.editarCuentaBancaria(cuentaBancariaEntidad);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al editar cuenta bancaria", e);
        }
    }

    private void validarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) throws NegocioException {
        if (cuentaBancaria == null) {
            throw new NegocioException("Cuenta bancaria no puede ser nula");
        }

        if (cuentaBancaria.getCuentaBancariaId() == null) {
            throw new NegocioException("Id de cuenta bancaria no puede ser nulo");
        }

        if (cuentaBancaria.getNumeroCuenta() == null || cuentaBancaria.getNumeroCuenta().trim().isEmpty()) {
            throw new NegocioException("Número de cuenta no puede ser nulo o vacío");
        }

       
        // Add more validation rules as needed
    }
}
