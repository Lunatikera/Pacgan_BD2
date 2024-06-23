/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servicios;

import dtos.CuentaBancariaDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IGestionarCuentasBancarias {

    public void agregarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) throws NegocioException;

    public CuentaBancariaDTO consultarCuentaBancariaPorID(Long id) throws NegocioException;

    public List<CuentaBancariaDTO> listaCuentasBancarias() throws NegocioException;

    public void editarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) throws NegocioException;

    public void eliminarCuentaBancaria(Long id) throws NegocioException;
}
