/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servicios;

import dtos.PagoDTO;
import dtos.Pago_EstadoDTO;
import dtos.TipoPagoDTO;
import excepciones.NegocioException;
import java.time.LocalDateTime;
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

    public List<PagoDTO> listaPagoPaginadoPorBeneficiario(int limite, int numeroPagina, Long beneficiarioId) throws NegocioException;

    public List<PagoDTO> listaPagoPaginadoAdmin(int limite, int numeroPagina, String estatusFiltro) throws NegocioException;

    public void agregarPagoEstatus(Pago_EstadoDTO pagoEstatus) throws NegocioException;
    public List<PagoDTO> filtrarPagos(List<Long> tipoPagoIds, List<Long> estatusIds, Boolean abonosTerminados, LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
