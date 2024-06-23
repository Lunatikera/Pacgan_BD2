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
import interfaces.IAgregarBeneficiarioBO;
import interfaces.IBeneficiarioDAO;

/**
 *
 * @author Usuario
 */
public class AgregarBeneficiarioBO implements IAgregarBeneficiarioBO {

    IBeneficiarioDAO beneficiarioDAO;

    public AgregarBeneficiarioBO() {
    }

    
    public AgregarBeneficiarioBO(IBeneficiarioDAO beneficiarioDAO) {
        this.beneficiarioDAO = beneficiarioDAO;
    }

    @Override
    public void agregarBeneficiario(BeneficiarioDTO beneficiario) throws NegocioException {

        validarBeneficiario(beneficiario);

        try {
            BeneficiarioEntidad beneficiarioEntidad = convertirDTOAEntidad(beneficiario);

            beneficiarioDAO.agregarBeneficiario(beneficiarioEntidad);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al agregar beneficiario en la capa de negocio", e);
        }
    }

    private void validarBeneficiario(BeneficiarioDTO beneficiario) throws NegocioException {
        // Verificar que el nombre no esté vacío
        if (beneficiario.getNombre()== null || beneficiario.getNombre().isEmpty()) {
            throw new NegocioException("El nombre del beneficiario no puede estar vacío");
        }

        // Verificar que la clave de contrato no esté vacía
        if (beneficiario.getClaveContrato() == null || beneficiario.getClaveContrato().isEmpty()) {
            throw new NegocioException("La clave de contrato del beneficiario no puede estar vacía");
        }

        // Verificar que el saldo sea mayor que cero (si es una restricción)
        if (beneficiario.getSaldo() <= 0) {
            throw new NegocioException("El saldo del beneficiario debe ser mayor que cero");
        }

        // Verificar que las listas de IDs no estén vacías (ejemplo: beneficiarioPagoIds y beneficiarioCuentaIds)
        if (beneficiario.getBeneficiarioPagoIds() == null || beneficiario.getBeneficiarioPagoIds().isEmpty()) {
            throw new NegocioException("La lista de IDs de pagos del beneficiario no puede estar vacía");
        }

        if (beneficiario.getBeneficiarioCuentaIds() == null || beneficiario.getBeneficiarioCuentaIds().isEmpty()) {
            throw new NegocioException("La lista de IDs de cuentas del beneficiario no puede estar vacía");
        }

    }
}
