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

    @Column(name = "numeroCuenta")
    private String numeroCuenta;

    @Column(name = "clabe")
    private String clabe;

    @Column(name = "nombreBanco")
    private String nombreBanco;

    @Column(name = "estaEliminada")
    private boolean estaEliminada;

    @ManyToOne(targetEntity = BeneficiarioEntidad.class)
    @JoinColumn(name = "id_beneficiario", nullable = false)
    private BeneficiarioEntidad beneficiarioCuenta;

     @OneToMany(mappedBy = "cuentaBancaria", cascade = {CascadeType.PERSIST})
    private List<PagoEntidad> cuentaBancaria;

    public CuentaBancariaEntidad() {
    }

    public CuentaBancariaEntidad(String numeroCuenta, String clabe, String nombreBanco, boolean estaEliminada, BeneficiarioEntidad beneficiarioCuenta, List<PagoEntidad> cuentaBancaria) {
        this.numeroCuenta = numeroCuenta;
        this.clabe = clabe;
        this.nombreBanco = nombreBanco;
        this.estaEliminada = estaEliminada;
        this.beneficiarioCuenta = beneficiarioCuenta;
        this.cuentaBancaria = cuentaBancaria;
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

    public List<PagoEntidad> getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(List<PagoEntidad> cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    @Override
    public String toString() {
        return "CuentaBancariaEntidad{" + "id_cuentaBancaria=" + id_cuentaBancaria + ", numeroCuenta=" + numeroCuenta + ", clabe=" + clabe + ", nombreBanco=" + nombreBanco + ", estaEliminada=" + estaEliminada + ", beneficiarioCuenta=" + beneficiarioCuenta + ", cuentaBancaria=" + cuentaBancaria + '}';
    }
     
     
}
