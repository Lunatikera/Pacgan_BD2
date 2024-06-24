/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.BeneficiarioDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IConsultarBeneficiarioBO {

    public BeneficiarioDTO consultarBeneficiarioPorID(Long id) throws NegocioException;

    public List<BeneficiarioDTO> listaBeneficiarios() throws NegocioException;

    public List<BeneficiarioDTO> listaBeneficiariosPaginado(int numeroPagina, int tamanoPagina) throws NegocioException;
    
    public BeneficiarioDTO ConsultarBeneficiarioNombreUsuario(String nombreUsuario) throws NegocioException;
}
