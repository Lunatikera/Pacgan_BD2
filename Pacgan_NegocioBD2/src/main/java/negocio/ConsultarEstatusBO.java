/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import static convertidores.ConvertidorEstatus.convertirEstatusADTO;
import dtos.EstatusDTO;
import entidades.EstatusEntidad;
import excepciones.NegocioException;
import interfaces.IConsultarEstatusBO;
import interfaces.IEstatusDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author triny
 */
public class ConsultarEstatusBO implements IConsultarEstatusBO {

    IEstatusDAO estatusDAO;

    public ConsultarEstatusBO(IEstatusDAO estatusDAO) {
        this.estatusDAO = estatusDAO;
    }

    @Override
    public EstatusDTO consultarEstatusPorID(Long id) throws NegocioException {
        try {
            EstatusEntidad estatus = estatusDAO.consultarEstatusPorID(id); // Método ficticio en IEstatusDAO
            if (estatus != null) {
                return convertirEstatusADTO(estatus);
            } else {
                throw new NegocioException("No se encontró el estatus con ID: " + id);
            }
        } catch (Exception e) {
            throw new NegocioException("Error al consultar el estatus por ID", e);
        }
    }

    @Override
    public List<EstatusDTO> listaEstatus() throws NegocioException {
        try {
            List<EstatusEntidad> estatus = estatusDAO.listaEstatus(); // Método ficticio en IEstatusDAO
            List<EstatusDTO> estatusDTO = new ArrayList<>();
            for (EstatusEntidad status : estatus) {
                estatusDTO.add(convertirEstatusADTO(status));
            }
            return estatusDTO;
        } catch (Exception e) {
            throw new NegocioException("Error al listar los estatus", e);
        }
    }

}
