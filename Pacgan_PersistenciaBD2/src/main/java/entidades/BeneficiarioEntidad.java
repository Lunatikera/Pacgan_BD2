/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
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

    @Column(name = "claveContrato", unique = true, length = 10, nullable = false)
    private String claveContrato;

    @Column(name = "nombres", length = 50, nullable = false)
    private String nombres;

    @Column(name = "apellidoPA", length = 50,  nullable = false)
    private String apellidoPA;

    @Column(name = "apellidoMA", length = 50)
    private String apellidoMA;

    @Column(name = "saldo", precision = 10, scale = 2,  nullable = false)
    private BigDecimal saldo;

    @Column(name = "nombreUsuario", length = 50, nullable = false, unique = true)
    private String nombreUsuario;

    @Column(name = "contraseña", length = 100, nullable = false)
    private String contraseña;

    @OneToMany(mappedBy = "beneficiarioPago", cascade = {CascadeType.PERSIST})
    private List<PagoEntidad> beneficiarioPago;

    @OneToMany(mappedBy = "beneficiarioCuenta", cascade = {CascadeType.PERSIST})
    private List<CuentaBancariaEntidad> beneficiarioCuenta;

    public BeneficiarioEntidad() {
    }

    public BeneficiarioEntidad(String claveContrato, String nombres, String apellidoPA, String apellidoMA, BigDecimal saldo, String nombreUsuario, String contraseña, List<PagoEntidad> beneficiarioPago, List<CuentaBancariaEntidad> beneficiarioCuenta) {
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

    public BeneficiarioEntidad(Long id_beneficiario, String claveContrato, String nombres, String apellidoPA, String apellidoMA, BigDecimal saldo, String nombreUsuario, String contraseña) {
        this.id_beneficiario = id_beneficiario;
        this.claveContrato = claveContrato;
        this.nombres = nombres;
        this.apellidoPA = apellidoPA;
        this.apellidoMA = apellidoMA;
        this.saldo = saldo;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
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

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
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

    private String generarClaveContrato() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, Math.min(uuid.length(), 10)); // Cortar a 10 caracteres máximo
    }

    @PrePersist
    public void prePersist() {
        claveContrato = generarClaveContrato();
    }

    @Override
    public String toString() {
        return "BeneficiarioEntidad{" + "id_beneficiario=" + id_beneficiario + ", claveContrato=" + claveContrato + ", nombres=" + nombres + ", apellidoPA=" + apellidoPA + ", apellidoMA=" + apellidoMA + ", saldo=" + saldo + ", nombreUsuario=" + nombreUsuario + ", contrase\u00f1a=" + contraseña + ", beneficiarioPago=" + beneficiarioPago + ", beneficiarioCuenta=" + beneficiarioCuenta + '}';
    }

}
