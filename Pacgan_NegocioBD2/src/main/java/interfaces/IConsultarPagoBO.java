/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.PagoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IConsultarPagoBO {

    public PagoDTO consultarPagoPorID(Long id) throws NegocioException;

    public List<PagoDTO> listaPagos() throws NegocioException;

    public List<PagoDTO> listaPagosPaginado(int numeroPagina, int tamanoPagina) throws NegocioException;

    public List<PagoDTO> listaPagoPaginadoPorBeneficiario(int limite, int numeroPagina, Long beneficiarioId) throws NegocioException;

    public List<PagoDTO> listaPagoPaginadoAdmin(int limite, int numeroPagina, String estatusFiltro) throws NegocioException;
}
