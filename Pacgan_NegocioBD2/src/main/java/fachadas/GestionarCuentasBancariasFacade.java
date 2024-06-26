/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import dtos.CuentaBancariaDTO;
import excepciones.NegocioException;
import interfaces.IAgregarCuentaBancariaBO;
import interfaces.IConsultarCuentaBancariaBO;
import interfaces.IEditarCuentaBancariaBO;
import interfaces.IEliminarCuentaBancariaBO;
import java.util.List;
import servicios.IGestionarCuentasBancarias;

/**
 *
 * @author Usuario
 */
public class GestionarCuentasBancariasFacade implements IGestionarCuentasBancarias {

    private IAgregarCuentaBancariaBO agregarCuentaBancariaBO;
    private IConsultarCuentaBancariaBO consultarCuentaBancariaBO;
    private IEditarCuentaBancariaBO editarCuentaBancariaBO;
    private IEliminarCuentaBancariaBO eliminarCuentaBancariaBO;

    public GestionarCuentasBancariasFacade(IAgregarCuentaBancariaBO agregarCuentaBancariaBO, IConsultarCuentaBancariaBO consultarCuentaBancariaBO, IEditarCuentaBancariaBO editarCuentaBancariaBO, IEliminarCuentaBancariaBO eliminarCuentaBancariaBO) {
        this.agregarCuentaBancariaBO = agregarCuentaBancariaBO;
        this.consultarCuentaBancariaBO = consultarCuentaBancariaBO;
        this.editarCuentaBancariaBO = editarCuentaBancariaBO;
        this.eliminarCuentaBancariaBO = eliminarCuentaBancariaBO;
    }

    @Override
    public void agregarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) throws NegocioException {
        agregarCuentaBancariaBO.agregarCuentaBancaria(cuentaBancaria);
    }

    @Override
    public CuentaBancariaDTO consultarCuentaBancariaPorID(Long id) throws NegocioException {
        return consultarCuentaBancariaBO.consultarCuentaBancariaPorID(id);
    }

    @Override
    public List<CuentaBancariaDTO> listaCuentasBancarias() throws NegocioException {
        return consultarCuentaBancariaBO.listaCuentasBancarias();
    }

    @Override
    public void editarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) throws NegocioException {
        editarCuentaBancariaBO.editarCuentaBancaria(cuentaBancaria);
    }

    @Override
    public void eliminarCuentaBancaria(Long id) throws NegocioException {
        eliminarCuentaBancariaBO.eliminarCuentaBancaria(id);
    }

    @Override
    public List<CuentaBancariaDTO> listaCuentasPorBeneficiario(Long id) throws NegocioException {
        return consultarCuentaBancariaBO.listaCuentasPorBeneficiario(id);
    }

    @Override
    public List<CuentaBancariaDTO> listaPaginadoCuentasPorBeneficiario(int limite, int numeroPagina, Long beneficiarioId) throws NegocioException {
        return consultarCuentaBancariaBO.listaPaginadoCuentasPorBeneficiario(limite, numeroPagina, beneficiarioId);
    }

}
