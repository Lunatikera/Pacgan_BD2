/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import convertidores.ConvertidorCuentaBancaria;
import dtos.CuentaBancariaDTO;
import entidades.CuentaBancariaEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IConsultarCuentaBancariaBO;
import interfaces.ICuentaBancariaDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ConsultarCuentaBancariaBO implements IConsultarCuentaBancariaBO {

    ICuentaBancariaDAO cuentaBancariaDAO;

    public ConsultarCuentaBancariaBO(ICuentaBancariaDAO cuentaBancariaDAO) {
        this.cuentaBancariaDAO = cuentaBancariaDAO;
    }

    @Override
    public CuentaBancariaDTO consultarCuentaBancariaPorID(Long id) throws NegocioException {
        try {
            CuentaBancariaEntidad cuentaBancariaEntidad = cuentaBancariaDAO.consultarCuentaBancariaPorID(id);
            if (cuentaBancariaEntidad == null) {
                throw new NegocioException("Cuenta bancaria no encontrada");
            }
            return ConvertidorCuentaBancaria.convertirEntidadADTO(cuentaBancariaEntidad);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar cuenta bancaria", e);
        }
    }

    @Override
    public List<CuentaBancariaDTO> listaCuentasBancarias() throws NegocioException {
        try {
            
            List<CuentaBancariaEntidad> listaCuentasBancariasEntidad = cuentaBancariaDAO.listaCuentasBancarias();
            List<CuentaBancariaDTO> listaCuentasBancariasDTO = new ArrayList<>();
            
            for (CuentaBancariaEntidad cuentaBancariaEntidad : listaCuentasBancariasEntidad) {
                listaCuentasBancariasDTO.add(ConvertidorCuentaBancaria.convertirEntidadADTO(cuentaBancariaEntidad));
            }
            return listaCuentasBancariasDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar lista de cuentas bancarias", e);
        }
    }
}
