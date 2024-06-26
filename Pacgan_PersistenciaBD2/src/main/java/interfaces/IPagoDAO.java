/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.PagoEntidad;
import excepciones.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IPagoDAO {

    public void agregarPago(PagoEntidad pago) throws PersistenciaException;

    public PagoEntidad consultarPagoPorID(Long id) throws PersistenciaException;

    public List<PagoEntidad> listaPagos() throws PersistenciaException;

    public List<PagoEntidad> listaPagosPaginado(int numeroPagina, int tamanoPagina) throws PersistenciaException;

    public void editarPago(PagoEntidad pago) throws PersistenciaException;

    public void eliminarPago(Long id) throws PersistenciaException;

    public List<PagoEntidad> listaPagoPaginadoPorBeneficiario(int limite, int numeroPagina, Long BeneficiarioId) throws PersistenciaException;

    public List<PagoEntidad> listaPagoPaginadoAdmin(int limite, int numeroPagina, String estatusFiltro) throws PersistenciaException;

    public List<PagoEntidad> filtrarPagos(List<Long> tipoPagoIds, List<Long> estatusIds, Boolean abonosTerminados, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    }
