/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "tblAbonos")
public class AbonoEntidad implements Serializable {

    @Id
    @Column(name = "id_abono")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_abono;

    @Column(name = "monto", precision = 10, scale = 2, nullable = false)
    private BigDecimal monto;

    @Column(name = "fechaHora", nullable = false)
    private LocalDateTime fechaHora;

    @ManyToOne(targetEntity = PagoEntidad.class)
    @JoinColumn(name = "id_pago", nullable = false)
    private PagoEntidad pagoAbono;

    public AbonoEntidad() {
    }

    public AbonoEntidad(BigDecimal monto, LocalDateTime fechaHora, PagoEntidad pagoAbono) {
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.pagoAbono = pagoAbono;
    }

    public Long getId() {
        return id_abono;
    }

    public void setId(Long id_abono) {
        this.id_abono = id_abono;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public PagoEntidad getPagoAbono() {
        return pagoAbono;
    }

    public void setPagoAbono(PagoEntidad pagoAbono) {
        this.pagoAbono = pagoAbono;

    }

    @PrePersist
    public void prePersist() {
        fechaHora = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "AbonoEntidad{" + "id_abono=" + id_abono + ", monto=" + monto + ", fechaHora=" + fechaHora + ", pagoAbono=" + pagoAbono + '}';
    }

}
