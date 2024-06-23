/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.List;

/**
 *
 * @author Usuario
 */
public class CuentaBancariaDTO {

    private Long cuentaBancariaId;
    private String numeroCuenta;
    private String clabe;
    private String nombreBanco;
    private boolean estaEliminada;
    private Long beneficiarioId;
    private List<Long> pagoIds;

    public CuentaBancariaDTO(Long cuentaBancariaId, String numeroCuenta, String clabe, String nombreBanco, boolean estaEliminada, Long beneficiarioId, List<Long> pagoIds) {
        this.cuentaBancariaId = cuentaBancariaId;
        this.numeroCuenta = numeroCuenta;
        this.clabe = clabe;
        this.nombreBanco = nombreBanco;
        this.estaEliminada = estaEliminada;
        this.beneficiarioId = beneficiarioId;
        this.pagoIds = pagoIds;
    }

    public Long getCuentaBancariaId() {
        return cuentaBancariaId;
    }

    public void setCuentaBancariaId(Long cuentaBancariaId) {
        this.cuentaBancariaId = cuentaBancariaId;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getClabe() {
        return clabe;
    }

    public void setClabe(String clabe) {
        this.clabe = clabe;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public boolean isEstaEliminada() {
        return estaEliminada;
    }

    public void setEstaEliminada(boolean estaEliminada) {
        this.estaEliminada = estaEliminada;
    }

    public Long getBeneficiarioId() {
        return beneficiarioId;
    }

    public void setBeneficiarioId(Long beneficiarioId) {
        this.beneficiarioId = beneficiarioId;
    }

    public List<Long> getPagoIds() {
        return pagoIds;
    }

    public void setPagoIds(List<Long> pagoIds) {
        this.pagoIds = pagoIds;
    }

    @Override
    public String toString() {
        return "CuentaBancariaDTO{" + "cuentaBancariaId=" + cuentaBancariaId + ", numeroCuenta=" + numeroCuenta + ", clabe=" + clabe + ", nombreBanco=" + nombreBanco + ", estaEliminada=" + estaEliminada + ", beneficiarioId=" + beneficiarioId + ", pagoIds=" + pagoIds + '}';
    }
    
    
}

  