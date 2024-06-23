/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.PagoDTO;
import excepciones.NegocioException;

/**
 *
 * @author Usuario
 */
public interface IEditarPagoBO {

    public void editarPago(PagoDTO pago) throws NegocioException;
}
