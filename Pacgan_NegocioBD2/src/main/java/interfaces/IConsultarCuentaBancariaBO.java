/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.CuentaBancariaDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IConsultarCuentaBancariaBO {

    public CuentaBancariaDTO consultarCuentaBancariaPorID(Long id) throws NegocioException;

    public List<CuentaBancariaDTO> listaCuentasBancarias() throws NegocioException;
}
