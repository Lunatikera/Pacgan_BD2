/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.CuentaBancariaEntidad;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface ICuentaBancariaDAO {

    public void agregarCuentaBancaria(CuentaBancariaEntidad cuentaBancaria) throws PersistenciaException;

    public CuentaBancariaEntidad consultarCuentaBancariaPorID(Long id) throws PersistenciaException;

    public List<CuentaBancariaEntidad> listaCuentasBancarias() throws PersistenciaException;
    
    public List<CuentaBancariaEntidad> listaCuentasPorBeneficiario(Long id) throws PersistenciaException;

    public void editarCuentaBancaria(CuentaBancariaEntidad cuentaBancaria) throws PersistenciaException;

    public void eliminarCuentaBancaria(Long id) throws PersistenciaException;
    
    public List<CuentaBancariaEntidad>  listaPaginadoCuentasPorBeneficiario(int limite, int numeroPagina, Long beneficiarioId) throws PersistenciaException;
       
}
