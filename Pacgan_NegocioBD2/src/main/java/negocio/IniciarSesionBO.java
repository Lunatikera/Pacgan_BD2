/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import Encriptadores.Encriptacion;
import daos.BeneficiarioDAO;
import entidades.BeneficiarioEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IBeneficiarioDAO;
import interfaces.IIniciarSesionBO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class IniciarSesionBO implements IIniciarSesionBO {

    private IBeneficiarioDAO beneficiarioDAO;

    public IniciarSesionBO(IBeneficiarioDAO beneficiarioDAO) {
        this.beneficiarioDAO = beneficiarioDAO;
    }

    public IniciarSesionBO() {
        this.beneficiarioDAO = new BeneficiarioDAO();
    }

    @Override
    public boolean iniciarSesion(String nombreUsuario, String contraseña) throws NegocioException {
        try {
            // Consultar el beneficiario por nombre de usuario
            BeneficiarioEntidad beneficiario = beneficiarioDAO.consultarBeneficiarioPorNombreUsuario(nombreUsuario);

            // Verificar si el beneficiario existe y la contraseña coincide
            if (beneficiario != null) {
                // Desencriptar la contraseña recuperada del beneficiario
                String contraseñaDesencriptada = Encriptacion.desencriptar(beneficiario.getContraseña());

                // Verificar si la contraseña desencriptada coincide con la proporcionada
                if (contraseñaDesencriptada.equals(contraseña)) {
                    return true; // Las credenciales son correctas
                }
            }
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo verificar las credenciales");
        }
        return false; // Las credenciales son incorrectas o hubo un error
    }
}
