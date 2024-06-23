/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import convertidores.ConvertidorBeneficiario;
import static convertidores.ConvertidorBeneficiario.convertirEntidadADTO;
import dtos.BeneficiarioDTO;
import entidades.BeneficiarioEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IBeneficiarioDAO;
import interfaces.IConsultarBeneficiarioBO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Usuario
 */
public class ConsultarBeneficiarioBO implements IConsultarBeneficiarioBO {

    IBeneficiarioDAO beneficiarioDAO;

    public ConsultarBeneficiarioBO(IBeneficiarioDAO beneficiarioDAO) {
        this.beneficiarioDAO = beneficiarioDAO;
    }

    @Override
    public BeneficiarioDTO consultarBeneficiarioPorID(Long id) throws NegocioException {
        try {
            BeneficiarioEntidad beneficiarioEntidad = beneficiarioDAO.consultarBeneficiarioPorID(id);

            if (beneficiarioEntidad != null) {
                return convertirEntidadADTO(beneficiarioEntidad);
            } else {
                throw new NegocioException("El abono con ID " + id + " no existe.");
            }
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar el abono.", e);
        }
    }

    @Override
    public List<BeneficiarioDTO> listaBeneficiarios() throws NegocioException {
        try {
            
            List<BeneficiarioEntidad> listaBeneficiariosEntidad = beneficiarioDAO.listaBeneficiarios();
            List<BeneficiarioDTO> listaBeneficiariosDTO = new ArrayList<>();
            
            for (BeneficiarioEntidad beneficiarioEntidad : listaBeneficiariosEntidad) {
                try {
                    BeneficiarioDTO beneficiarioDTO = convertirEntidadADTO(beneficiarioEntidad);
                    listaBeneficiariosDTO.add(beneficiarioDTO);
                } catch (PersistenciaException e) {
                    throw new NegocioException("Error al obtener la lista de abonos desde la base de datos.", e);
                }
            }
            return listaBeneficiariosDTO;
        } catch (PersistenciaException e) {
            // Capturar excepción de persistencia y lanzar como NegocioException
            throw new NegocioException("Error al obtener la lista de abonos desde la base de datos.", e);
        }
    }
}
