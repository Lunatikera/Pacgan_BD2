/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.time.LocalDateTime;

/**
 *
 * @author triny
 */
public class Pago_EstadoDTO {
    
    private Long idPago;
    private String mensaje;
    private LocalDateTime fechaHora;
    private Long idEstatus;

    // Constructores, getters y setters

    public Pago_EstadoDTO() {
    }

    public Pago_EstadoDTO(Long idPago, String mensaje, LocalDateTime fechaHora, Long idEstatus) {
        this.idPago = idPago;
        this.mensaje = mensaje;
        this.fechaHora = fechaHora;
        this.idEstatus = idEstatus;
    }

    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
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

    public Long getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Long idEstatus) {
        this.idEstatus = idEstatus;
    }

    @Override
    public String toString() {
        return "Pago_EstadoDTO{" +
                "idPago=" + idPago +
                ", mensaje='" + mensaje + '\'' +
                ", fechaHora=" + fechaHora +
                ", idEstatus=" + idEstatus +
                '}';
    }
}