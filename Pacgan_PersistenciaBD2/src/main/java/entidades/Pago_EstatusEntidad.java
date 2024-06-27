/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
@Table(name = "tblPago_Estatus")
public class Pago_EstatusEntidad implements Serializable {

    @Id
    @Column(name = "id_pagoEstatus")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pagoEstatus;

    @Column(name = "mensaje", length = 255)
    private String mensaje;

    @Column(name = "fechaHora", nullable = false)
    private LocalDateTime fechaHora;

    @ManyToOne(targetEntity = PagoEntidad.class)
    @JoinColumn(name = "id_pago", nullable = false)
    private PagoEntidad pagoEstatus;

    @ManyToOne(targetEntity = EstatusEntidad.class)
    @JoinColumn(name = "id_estatus", nullable = false)
    private EstatusEntidad estatus;

    public Pago_EstatusEntidad() {
    }

    public Pago_EstatusEntidad(String mensaje, LocalDateTime fechaHora, PagoEntidad pagoEstatus, EstatusEntidad estatus) {
        this.mensaje = mensaje;
        this.fechaHora = fechaHora;
        this.pagoEstatus = pagoEstatus;
        this.estatus = estatus;
    }

    public Long getId() {
        return id_pagoEstatus;
    }

    public void setId(Long id_pagoEstatus) {
        this.id_pagoEstatus = id_pagoEstatus;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public PagoEntidad getPagoEstatus() {
        return pagoEstatus;
    }

    public void setPagoEstatus(PagoEntidad pagoEstatus) {
        this.pagoEstatus = pagoEstatus;
    }

    public EstatusEntidad getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusEntidad estatus) {
        this.estatus = estatus;
    }

    @PrePersist
    public void prePersist() {
        ZoneId zonaObregon = ZoneId.of("America/Hermosillo");

        // Obtener la fecha y hora actual en la zona horaria de Ciudad Obregón
        fechaHora = LocalDateTime.now(zonaObregon);

        if (mensaje == null || mensaje.isBlank()) {
            mensaje = "No se ha escrito ningun mensaje";
        }

    }

    @Override
    public String toString() {
        return "Pago_EstatusEntidad{" + "id_pagoEstatus=" + id_pagoEstatus + ", mensaje=" + mensaje + ", fechaHora=" + fechaHora + ", pagoEstatus=" + pagoEstatus + ", estatus=" + estatus + '}';
    }

}
