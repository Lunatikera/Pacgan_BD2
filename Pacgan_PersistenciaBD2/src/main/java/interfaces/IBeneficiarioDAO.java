/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.BeneficiarioEntidad;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IBeneficiarioDAO {

    public void agregarBeneficiario(BeneficiarioEntidad beneficiario) throws PersistenciaException;

    public BeneficiarioEntidad consultarBeneficiarioPorID(Long id) throws PersistenciaException;

    public List<BeneficiarioEntidad> listaBeneficiarios() throws PersistenciaException;

    public void editarBeneficiario(BeneficiarioEntidad beneficiario) throws PersistenciaException;
    
     public void eliminarBeneficiario(Long id) throws PersistenciaException;
}
