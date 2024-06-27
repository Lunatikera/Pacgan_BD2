/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import convertidores.ConvertidorPago;
import static convertidores.ConvertidorPago.convertirEntidadADTO;
import dtos.PagoDTO;
import entidades.PagoEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IConsultarPagoBO;
import interfaces.IPagoDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ConsultarPagoBO implements IConsultarPagoBO {
    
    IPagoDAO pagoDAO;
    
    public ConsultarPagoBO(IPagoDAO pagoDAO) {
        this.pagoDAO = pagoDAO;
    }
    
    @Override
    public PagoDTO consultarPagoPorID(Long id) throws NegocioException {
        try {
            PagoEntidad pagoEntidad = pagoDAO.consultarPagoPorID(id);
            if (pagoEntidad == null) {
                throw new NegocioException("Pago no encontrado");
            }
            return convertirEntidadADTO(pagoEntidad);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar pago", e);
        }
    }
    
    @Override
    public List<PagoDTO> listaPagos() throws NegocioException {
        try {
            List<PagoEntidad> listaPagosEntidad = pagoDAO.listaPagos();
            List<PagoDTO> listaPagosDTO = new ArrayList<>();
            for (PagoEntidad pagoEntidad : listaPagosEntidad) {
                listaPagosDTO.add(ConvertidorPago.convertirEntidadADTO(pagoEntidad));
            }
            return listaPagosDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar lista de pagos", e);
        }
    }
    
    @Override
    public List<PagoDTO> listaPagosPaginado(int limite, int numeroPagina) throws NegocioException {
        try {
            List<PagoEntidad> listaPagosEntidad = pagoDAO.listaPagosPaginado(limite, numeroPagina);
            List<PagoDTO> listaPagosDTO = new ArrayList<>();
            
            for (PagoEntidad pago : listaPagosEntidad) {
                PagoDTO pagoDTO = convertirEntidadADTO(pago);
                listaPagosDTO.add(pagoDTO);
            }
            return listaPagosDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener la lista de beneficiarios desde la base de datos.", e);
        }
    }
    
    @Override
    public List<PagoDTO> listaPagoPaginadoPorBeneficiario(int limite, int numeroPagina, Long beneficiarioId) throws NegocioException {
        try {
            List<PagoEntidad> listaPagosEntidad = pagoDAO.listaPagoPaginadoPorBeneficiario(limite, numeroPagina, beneficiarioId);
            List<PagoDTO> listaPagosDTO = new ArrayList<>();
            
            for (PagoEntidad pago : listaPagosEntidad) {
                PagoDTO pagoDTO = convertirEntidadADTO(pago);
                listaPagosDTO.add(pagoDTO);
            }
          
            return listaPagosDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener la lista de pago.", e);
        }
    }
    
    @Override
    public List<PagoDTO> listaPagoPaginadoAdmin(int limite, int numeroPagina, String estatusFiltro) throws NegocioException {
        try {
            List<PagoEntidad> listaPagosEntidad = pagoDAO.listaPagoPaginadoAdmin(limite, numeroPagina, estatusFiltro);
            List<PagoDTO> listaPagosDTO = new ArrayList<>();
            
            for (PagoEntidad pago : listaPagosEntidad) {
                PagoDTO pagoDTO = convertirEntidadADTO(pago);
                listaPagosDTO.add(pagoDTO);
            }

            return listaPagosDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener la lista de pago.", e);
        }
    }
    
}
