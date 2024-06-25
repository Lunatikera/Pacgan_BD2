/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servicios;

import dtos.PagoDTO;
import dtos.TipoPagoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IGestionarPagos {

    public void crearPagoBO(PagoDTO pago) throws NegocioException;

    public PagoDTO consultarPagoPorID(Long id) throws NegocioException;

    public List<PagoDTO> listaPagos() throws NegocioException;

    public List<PagoDTO> listaPagosPaginado(int limite, int numeroPagina) throws NegocioException;

    public void editarPago(PagoDTO pago) throws NegocioException;

    public void eliminarPago(Long id) throws NegocioException;
    
     public TipoPagoDTO consultarTipoPagoPorID(Long id) throws NegocioException;

    public List<TipoPagoDTO> listaTiposPago() throws NegocioException;
}
