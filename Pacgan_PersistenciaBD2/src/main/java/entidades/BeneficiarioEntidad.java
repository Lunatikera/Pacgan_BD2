/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "tblBeneficiarios")
public class BeneficiarioEntidad implements Serializable {

    @Id
    @Column(name = "id_beneficiario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_beneficiario;

    @Column(name = "claveContrato")
    private String claveContrato;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidoPA")
    private String apellidoPA;

    @Column(name = "apellidoMA")
    private String apellidoMA;

    @Column(name = "saldo")
    private double saldo;

    @Column(name = "nombreUsuario")
    private String nombreUsuario;

    @Column(name = "contraseña")
    private String contraseña;

    @OneToMany(mappedBy = "beneficiarioPago", cascade = {CascadeType.PERSIST})
    private List<PagoEntidad> beneficiarioPago;
    
    @OneToMany(mappedBy = "beneficiarioCuenta", cascade = {CascadeType.PERSIST})
    private List<CuentaBancariaEntidad> beneficiarioCuenta;

    public BeneficiarioEntidad() {
    }

    public BeneficiarioEntidad(String claveContrato, String nombres, String apellidoPA, String apellidoMA, double saldo, String nombreUsuario, String contraseña, List<PagoEntidad> beneficiarioPago, List<CuentaBancariaEntidad> beneficiarioCuenta) {
        this.claveContrato = claveContrato;
        this.nombres = nombres;
        this.apellidoPA = apellidoPA;
        this.apellidoMA = apellidoMA;
        this.saldo = saldo;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.beneficiarioPago = beneficiarioPago;
        this.beneficiarioCuenta = beneficiarioCuenta;
    }

    public Long getId() {
        return id_beneficiario;
    }

    public void setId(Long id_beneficiario) {
        this.id_beneficiario = id_beneficiario;
    }

    public String getClaveContrato() {
        return claveContrato;
    }

    public void setClaveContrato(String claveContrato) {
        this.claveContrato = claveContrato;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public List<PagoEntidad> getBeneficiarioPago() {
        return beneficiarioPago;
    }

    public void setBeneficiarioPago(List<PagoEntidad> beneficiarioPago) {
        this.beneficiarioPago = beneficiarioPago;
    }

    public List<CuentaBancariaEntidad> getBeneficiarioCuenta() {
        return beneficiarioCuenta;
    }

    public void setBeneficiarioCuenta(List<CuentaBancariaEntidad> beneficiarioCuenta) {
        this.beneficiarioCuenta = beneficiarioCuenta;
    }

    @Override
    public String toString() {
        return "BeneficiarioEntidad{" + "id_beneficiario=" + id_beneficiario + ", claveContrato=" + claveContrato + ", nombres=" + nombres + ", apellidoPA=" + apellidoPA + ", apellidoMA=" + apellidoMA + ", saldo=" + saldo + ", nombreUsuario=" + nombreUsuario + ", contrase\u00f1a=" + contraseña + ", beneficiarioPago=" + beneficiarioPago + ", beneficiarioCuenta=" + beneficiarioCuenta + '}';
    }
    
    
}
