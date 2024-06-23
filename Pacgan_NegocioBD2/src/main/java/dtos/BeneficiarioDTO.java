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
public class BeneficiarioDTO {

    private Long beneficiarioId;
    private String claveContrato;
    private String nombre;
    private String apellidoPA;
    private String apellidoMA;
    private double saldo;
    private String nombreUsuario;
    private String contraseña;
    private List<Long> beneficiarioPagoIds;
    private List<Long> beneficiarioCuentaIds;

    public BeneficiarioDTO(Long beneficiarioId, String claveContrato, String nombre, String apellidoPA, String apellidoMA, double saldo, String nombreUsuario, String contraseña, List<Long> beneficiarioPagoIds, List<Long> beneficiarioCuentaIds) {
        this.beneficiarioId = beneficiarioId;
        this.claveContrato = claveContrato;
        this.nombre = nombre;
        this.apellidoPA = apellidoPA;
        this.apellidoMA = apellidoMA;
        this.saldo = saldo;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.beneficiarioPagoIds = beneficiarioPagoIds;
        this.beneficiarioCuentaIds = beneficiarioCuentaIds;
    }

    public BeneficiarioDTO() {
    }

    public Long getBeneficiarioId() {
        return beneficiarioId;
    }

    public void setBeneficiarioId(Long beneficiarioId) {
        this.beneficiarioId = beneficiarioId;
    }

    public String getClaveContrato() {
        return claveContrato;
    }

    public void setClaveContrato(String claveContrato) {
        this.claveContrato = claveContrato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPA() {
        return apellidoPA;
    }

    public void setApellidoPA(String apellidoPA) {
        this.apellidoPA = apellidoPA;
    }

    public String getApellidoMA() {
        return apellidoMA;
    }

    public void setApellidoMA(String apellidoMA) {
        this.apellidoMA = apellidoMA;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public List<Long> getBeneficiarioPagoIds() {
        return beneficiarioPagoIds;
    }

    public void setBeneficiarioPagoIds(List<Long> beneficiarioPagoIds) {
        this.beneficiarioPagoIds = beneficiarioPagoIds;
    }

    public List<Long> getBeneficiarioCuentaIds() {
        return beneficiarioCuentaIds;
    }

    public void setBeneficiarioCuentaIds(List<Long> beneficiarioCuentaIds) {
        this.beneficiarioCuentaIds = beneficiarioCuentaIds;
    }

    @Override
    public String toString() {
        return "BeneficiarioDTO{" + "beneficiarioId=" + beneficiarioId + ", claveContrato=" + claveContrato + ", nombres=" + nombre + ", apellidoPA=" + apellidoPA + ", apellidoMA=" + apellidoMA + ", saldo=" + saldo + ", nombreUsuario=" + nombreUsuario + ", contrase\u00f1a=" + contraseña + ", beneficiarioPagoIds=" + beneficiarioPagoIds + ", beneficiarioCuentaIds=" + beneficiarioCuentaIds + '}';
    }
    
    

}
