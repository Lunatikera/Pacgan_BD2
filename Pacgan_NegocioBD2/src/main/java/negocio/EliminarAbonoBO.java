/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.AbonoDTO;
import entidades.AbonoEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IAbonoDAO;
import interfaces.IEliminarAbonoBO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class EliminarAbonoBO implements IEliminarAbonoBO {

    IAbonoDAO abonoDAO;

    public EliminarAbonoBO(IAbonoDAO abonoDAO) {
        this.abonoDAO = abonoDAO;
    }

    @Override
    public void eliminarAbono(Long id) throws NegocioException {

        try {
            AbonoEntidad abono = abonoDAO.consultarAbonoPorID(id);
            validarAbono(abono);
            abonoDAO.eliminarAbono(abono.getId());

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar el abono", ex);
        }
    }

    private void validarAbono(AbonoEntidad abono) throws NegocioException {
        // Aquí puedes implementar la lógica de validación para abonoDTO
        // Por ejemplo, verificar que los campos obligatorios no estén vacíos, 
        // que los valores sean válidos, etc.
        if (abono == null) {
            throw new NegocioException("El abono no existe.");
        }
    }
}
