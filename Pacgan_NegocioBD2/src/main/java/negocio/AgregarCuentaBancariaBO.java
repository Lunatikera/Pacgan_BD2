/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import static convertidores.ConvertidorCuentaBancaria.convertirDTOAEntidad;
import daos.CuentaBancariaDAO;
import dtos.CuentaBancariaDTO;
import entidades.CuentaBancariaEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IAgregarCuentaBancariaBO;
import interfaces.ICuentaBancariaDAO;

/**
 *
 * @author Usuario
 */
public class AgregarCuentaBancariaBO implements IAgregarCuentaBancariaBO {

    ICuentaBancariaDAO cuentaBancariaDAO;

    public AgregarCuentaBancariaBO(ICuentaBancariaDAO cuentaBancariaDAO) {
        this.cuentaBancariaDAO = cuentaBancariaDAO;
    }

    public AgregarCuentaBancariaBO() {
        this.cuentaBancariaDAO = new CuentaBancariaDAO();
    }
    
    

    @Override
    public void agregarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) throws NegocioException {
           validarCuentaBancariaDTO(cuentaBancaria);

        try {
            CuentaBancariaEntidad cuentaEntidad = convertirDTOAEntidad(cuentaBancaria);

            cuentaBancariaDAO.agregarCuentaBancaria(cuentaEntidad);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al guardar la cuenta bancaria.", e);
        }
    }

    private void validarCuentaBancariaDTO(CuentaBancariaDTO cuentaBancaria) throws NegocioException {
        if (cuentaBancaria == null) {
            throw new NegocioException("La cuenta bancaria no puede ser nula.");
        }
    }
}