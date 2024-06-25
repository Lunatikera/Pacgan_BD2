/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.TipoPagoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author victoria
 */
public interface IConsultarTipoPagoBO {

    public TipoPagoDTO consultarTipoPagoPorID(Long id) throws NegocioException;

    public List<TipoPagoDTO> listaTiposPago() throws NegocioException;
}
