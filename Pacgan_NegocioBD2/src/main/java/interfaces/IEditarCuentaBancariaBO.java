/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.CuentaBancariaDTO;
import excepciones.NegocioException;

/**
 *
 * @author Usuario
 */
public interface IEditarCuentaBancariaBO {

    public void editarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) throws NegocioException;

}
