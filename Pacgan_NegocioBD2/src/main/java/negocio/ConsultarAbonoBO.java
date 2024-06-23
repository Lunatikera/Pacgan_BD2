/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import convertidores.ConvertidorAbono;
import static convertidores.ConvertidorAbono.convertirEntidadADTO;
import dtos.AbonoDTO;
import entidades.AbonoEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IAbonoDAO;
import interfaces.IConsultarAbonoBO;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Usuario
 */
public class ConsultarAbonoBO implements IConsultarAbonoBO {

    IAbonoDAO abonoDAO;

    public ConsultarAbonoBO(IAbonoDAO abonoDAO) {
        this.abonoDAO = abonoDAO;
    }

    @Override
    public AbonoDTO consultarAbonoPorID(Long id) throws NegocioException {
        try {
            // Consultar el abono por su ID utilizando abonoDAO
            AbonoEntidad abonoEntidad = abonoDAO.consultarAbonoPorID(id);

            if (abonoEntidad != null) {
                // Si se encuentra el abono, convertir AbonoEntidad a AbonoDTO y retornarlo
                return convertirEntidadADTO(abonoEntidad);
            } else {
                throw new NegocioException("El abono con ID " + id + " no existe.");
            }
        } catch (PersistenciaException e) {
            // Capturar excepción de persistencia y lanzar como NegocioException
            throw new NegocioException("Error al consultar el abono.", e);
        }

    }

    @Override
    public List<AbonoDTO> listaAbonos() throws NegocioException {
       try {
            // Obtener la lista de abonos desde abonoDAO
            List<AbonoEntidad> listaAbonosEntidad = abonoDAO.listaAbonos();

            // Convertir la lista de AbonoEntidad a una lista de AbonoDTO
            return listaAbonosEntidad.stream()
                    .map(ConvertidorAbono::convertirEntidadADTO)
                    .collect(Collectors.toList());
            
        } catch (PersistenciaException e) {
            // Capturar excepción de persistencia y lanzar como NegocioException
            throw new NegocioException("Error al obtener la lista de abonos desde la base de datos.", e);
        }
    }
}
