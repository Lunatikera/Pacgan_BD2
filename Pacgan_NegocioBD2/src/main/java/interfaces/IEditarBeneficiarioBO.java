/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.BeneficiarioDTO;
import excepciones.NegocioException;

/**
 *
 * @author Usuario
 */
public interface IEditarBeneficiarioBO {

    public void editarBeneficiario(BeneficiarioDTO beneficiario) throws NegocioException;

}
