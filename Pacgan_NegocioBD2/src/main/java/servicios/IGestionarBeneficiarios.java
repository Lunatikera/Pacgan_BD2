/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servicios;

import dtos.BeneficiarioDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IGestionarBeneficiarios {
     public void agregarBeneficiario(BeneficiarioDTO beneficiario) throws NegocioException ;

    public BeneficiarioDTO consultarBeneficiarioPorID(Long id) throws NegocioException;

    public List<BeneficiarioDTO> listaBeneficiarios() throws NegocioException;

    public void editarBeneficiario(BeneficiarioDTO beneficiario) throws NegocioException;
    
     public void eliminarBeneficiario(Long id) throws NegocioException;
}
