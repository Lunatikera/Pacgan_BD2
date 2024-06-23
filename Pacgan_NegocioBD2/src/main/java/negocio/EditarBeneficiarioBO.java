/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import static convertidores.ConvertidorBeneficiario.convertirDTOAEntidad;
import dtos.BeneficiarioDTO;
import entidades.BeneficiarioEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IBeneficiarioDAO;
import interfaces.IEditarBeneficiarioBO;

/**
 *
 * @author Usuario
 */
public class EditarBeneficiarioBO implements IEditarBeneficiarioBO {

    IBeneficiarioDAO beneficiarioDAO;

    public EditarBeneficiarioBO(IBeneficiarioDAO beneficiarioDAO) {
        this.beneficiarioDAO = beneficiarioDAO;
    }

    @Override
    public void editarBeneficiario(BeneficiarioDTO beneficiario) throws NegocioException {
        try {
            validarBeneficiario(beneficiario);
            BeneficiarioEntidad beneficiarioEntidad = convertirDTOAEntidad(beneficiario);
            beneficiarioDAO.editarBeneficiario(beneficiarioEntidad);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al editar beneficiario", e);
        }
    }

    private void validarBeneficiario(BeneficiarioDTO beneficiario) throws NegocioException {
        if (beneficiario == null) {
            throw new NegocioException("Beneficiario no puede ser nulo");
        }

        if (beneficiario.getBeneficiarioId() == null) {
            throw new NegocioException("Id de beneficiario no puede ser nulo");
        }

        if (beneficiario.getNombre() == null || beneficiario.getNombre().trim().isEmpty()) {
            throw new NegocioException("Nombre de beneficiario no puede ser nulo o vacío");
        }

        if (beneficiario.getApellidoPA() == null || beneficiario.getApellidoPA().trim().isEmpty()) {
            throw new NegocioException("Apellido de beneficiario no puede ser nulo o vacío");
        }
        
        if (beneficiario.getApellidoMA() == null || beneficiario.getApellidoMA().trim().isEmpty()) {
            throw new NegocioException("Apellido de beneficiario no puede ser nulo o vacío");
        }
    }

}
