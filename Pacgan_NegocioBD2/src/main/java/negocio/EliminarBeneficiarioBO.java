/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import entidades.BeneficiarioEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IBeneficiarioDAO;
import interfaces.IEliminarBeneficiarioBO;

/**
 *
 * @author Usuario
 */
public class EliminarBeneficiarioBO implements IEliminarBeneficiarioBO {

    IBeneficiarioDAO beneficiarioDAO;

    public EliminarBeneficiarioBO(IBeneficiarioDAO beneficiarioDAO) {
        this.beneficiarioDAO = beneficiarioDAO;
    }

    @Override
    public void eliminarBeneficiario(Long id) throws NegocioException {
        try {
            BeneficiarioEntidad beneficiario = beneficiarioDAO.consultarBeneficiarioPorID(id);
            validarAbono(beneficiario);
            beneficiarioDAO.eliminarBeneficiario(beneficiario.getId());

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar al beneficiario.", ex);
        }
    }

    private void validarAbono(BeneficiarioEntidad beneficiario) throws NegocioException {

        if (beneficiario == null) {
            throw new NegocioException("El beneficiario no existe.");
        }
    }
}
