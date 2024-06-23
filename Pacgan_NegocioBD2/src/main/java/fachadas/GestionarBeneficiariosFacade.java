/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import dtos.BeneficiarioDTO;
import excepciones.NegocioException;
import interfaces.IAgregarBeneficiarioBO;
import interfaces.IConsultarBeneficiarioBO;
import interfaces.IEditarBeneficiarioBO;
import interfaces.IEliminarBeneficiarioBO;
import java.util.List;
import servicios.IGestionarBeneficiarios;

/**
 *
 * @author Usuario
 */
public class GestionarBeneficiariosFacade implements IGestionarBeneficiarios {

    private IAgregarBeneficiarioBO agregarBeneficiarioBO;
    private IConsultarBeneficiarioBO consultarBeneficiarioBO;
    private IEditarBeneficiarioBO editarBeneficiarioBO;
    private IEliminarBeneficiarioBO eliminarBeneficiarioBO;
    
    public GestionarBeneficiariosFacade(IAgregarBeneficiarioBO agregarBeneficiarioBO, IConsultarBeneficiarioBO consultarBeneficiarioBO, IEditarBeneficiarioBO editarBeneficiarioBO, IEliminarBeneficiarioBO eliminarBeneficiarioBO) {
        this.agregarBeneficiarioBO = agregarBeneficiarioBO;
        this.consultarBeneficiarioBO = consultarBeneficiarioBO;
        this.editarBeneficiarioBO = editarBeneficiarioBO;
        this.eliminarBeneficiarioBO = eliminarBeneficiarioBO;
    }
    
    @Override
    public void agregarBeneficiario(BeneficiarioDTO beneficiario) throws NegocioException {
        agregarBeneficiarioBO.agregarBeneficiario(beneficiario);
    }
    
    @Override
    public BeneficiarioDTO consultarBeneficiarioPorID(Long id) throws NegocioException {
        return consultarBeneficiarioBO.consultarBeneficiarioPorID(id);
    }
    
    @Override
    public List<BeneficiarioDTO> listaBeneficiarios() throws NegocioException {
        return consultarBeneficiarioBO.listaBeneficiarios();
    }
    
    @Override
    public void editarBeneficiario(BeneficiarioDTO beneficiario) throws NegocioException {
         editarBeneficiarioBO.editarBeneficiario(beneficiario);
    }
    
    @Override
    public void eliminarBeneficiario(Long id) throws NegocioException {
         eliminarBeneficiarioBO.eliminarBeneficiario(id);
    }

    @Override
    public List<BeneficiarioDTO> listaBeneficiariosPaginado(int numeroPagina, int tamanoPagina) throws NegocioException {
        return consultarBeneficiarioBO.listaBeneficiariosPaginado(numeroPagina, tamanoPagina);
    }
    
}
