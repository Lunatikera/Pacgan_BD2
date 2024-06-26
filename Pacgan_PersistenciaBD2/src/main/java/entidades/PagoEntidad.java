/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "tblPagos")
public class PagoEntidad implements Serializable {

    @Id
    @Column(name = "id_pago")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pago;

    @Column(name = "monto", precision = 10, scale = 2, nullable = false)
    private BigDecimal monto;

    @Lob
    private byte[] comprobante;

    @ManyToOne(targetEntity = TipoPagoEntidad.class)
    @JoinColumn(name = "id_tipoPago", nullable = false)
    private TipoPagoEntidad tipoPago;

    @ManyToOne(targetEntity = BeneficiarioEntidad.class)
    @JoinColumn(name = "id_beneficiario", nullable = false)
    private BeneficiarioEntidad beneficiarioPago;

    @ManyToOne(targetEntity = CuentaBancariaEntidad.class)
    @JoinColumn(name = "id_cuentaBancaria", nullable = false)
    private CuentaBancariaEntidad cuentaBancaria;

    @OneToMany(mappedBy = "pagoAbono", cascade = {CascadeType.PERSIST})
    private List<AbonoEntidad> pagoAbonos;

    @OneToMany(mappedBy = "pagoEstatus", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Pago_EstatusEntidad> pagoEstatus;

    public PagoEntidad() {
    }

    public PagoEntidad(BigDecimal monto, byte[] comprobante, TipoPagoEntidad tipoPago, BeneficiarioEntidad beneficiarioPago, CuentaBancariaEntidad cuentaBancaria, List<AbonoEntidad> pagoAbono, List<Pago_EstatusEntidad> pagoEstatus) {
        this.monto = monto;
        this.comprobante = comprobante;
        this.tipoPago = tipoPago;
        this.beneficiarioPago = beneficiarioPago;
        this.cuentaBancaria = cuentaBancaria;
        this.pagoAbonos = pagoAbono;
        this.pagoEstatus = pagoEstatus;
    }

    public Long getId() {
        return id_pago;
    }

    public void setId(Long id_pago) {
        this.id_pago = id_pago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public byte[] getComprobante() {
        return comprobante;
    }

    public void setComprobante(byte[] comprobante) {
        this.comprobante = comprobante;
    }

    public TipoPagoEntidad getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPagoEntidad tipoPago) {
        this.tipoPago = tipoPago;
    }

    public BeneficiarioEntidad getBeneficiarioPago() {
        return beneficiarioPago;
    }

    public void setBeneficiarioPago(BeneficiarioEntidad beneficiarioPago) {
        this.beneficiarioPago = beneficiarioPago;
    }

    public CuentaBancariaEntidad getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancariaEntidad cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public List<AbonoEntidad> getPagoAbonos() {
        return pagoAbonos;
    }

    public void setPagoAbonos(List<AbonoEntidad> pagoAbono) {
        this.pagoAbonos = pagoAbono;
    }

    public List<Pago_EstatusEntidad> getPagoEstatus() {
        return pagoEstatus;
    }

    public void setPagoEstatus(List<Pago_EstatusEntidad> pagoEstatus) {
        this.pagoEstatus = pagoEstatus;
    }

}
