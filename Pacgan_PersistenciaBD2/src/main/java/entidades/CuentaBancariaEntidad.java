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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "tblCuentaBancarias")
public class CuentaBancariaEntidad implements Serializable {

    @Id
    @Column(name = "id_cuentaBancaria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cuentaBancaria;

    @Column(name = "numeroCuenta", length = 20, nullable = false, unique = true)
    private String numeroCuenta;

    @Column(name = "clabe", length = 18, nullable = false, unique = true)
    private String clabe;

    @Column(name = "nombreBanco", length = 60, nullable = false)
    private String nombreBanco;

    @Column(name = "estaEliminada",  nullable = false)
    private boolean estaEliminada = false;

    @ManyToOne(targetEntity = BeneficiarioEntidad.class)
    @JoinColumn(name = "id_beneficiario", nullable = false)
    private BeneficiarioEntidad beneficiarioCuenta;

    @OneToMany(mappedBy = "cuentaBancaria", cascade = {CascadeType.PERSIST})
    private List<PagoEntidad> cuentaBancariaPagos;

    public CuentaBancariaEntidad() {
    }

    public CuentaBancariaEntidad(String numeroCuenta, String clabe, String nombreBanco, boolean estaEliminada, BeneficiarioEntidad beneficiarioCuenta, List<PagoEntidad> cuentaBancaria) {
        this.numeroCuenta = numeroCuenta;
        this.clabe = clabe;
        this.nombreBanco = nombreBanco;
        this.estaEliminada = estaEliminada;
        this.beneficiarioCuenta = beneficiarioCuenta;
        this.cuentaBancariaPagos = cuentaBancaria;
    }

    public Long getId() {
        return id_cuentaBancaria;
    }

    public void setId(Long id_cuentaBancaria) {
        this.id_cuentaBancaria = id_cuentaBancaria;
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

    public BeneficiarioEntidad getBeneficiarioCuenta() {
        return beneficiarioCuenta;
    }

    public void setBeneficiarioCuenta(BeneficiarioEntidad beneficiarioCuenta) {
        this.beneficiarioCuenta = beneficiarioCuenta;
    }

    public List<PagoEntidad> getCuentaBancariaPagos() {
        return cuentaBancariaPagos;
    }

    public void setCuentaBancariaPagos(List<PagoEntidad> cuentaBancaria) {
        this.cuentaBancariaPagos = cuentaBancaria;
    }

    @Override
    public String toString() {
        return "CuentaBancariaEntidad{" + "id_cuentaBancaria=" + id_cuentaBancaria + ", numeroCuenta=" + numeroCuenta + ", clabe=" + clabe + ", nombreBanco=" + nombreBanco + ", estaEliminada=" + estaEliminada + ", beneficiarioCuenta=" + beneficiarioCuenta + ", cuentaBancaria=" + cuentaBancariaPagos + '}';
    }

}
