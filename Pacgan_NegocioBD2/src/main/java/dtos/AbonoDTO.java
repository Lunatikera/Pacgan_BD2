/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Usuario
 */
public class AbonoDTO {

    private Long abonoId;
    private double monto;
    private LocalDate fecha;
    private LocalTime hora;
    private Long pagoId;

    public AbonoDTO(Long abonoId, double monto, LocalDate fecha, LocalTime hora, Long pagoId) {
        this.abonoId = abonoId;
        this.monto = monto;
        this.fecha = fecha;
        this.hora = hora;
        this.pagoId = pagoId;
    }

    public AbonoDTO() {
    }

    public Long getAbonoId() {
        return abonoId;
    }

    public void setAbonoId(Long abonoId) {
        this.abonoId = abonoId;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Long getPagoId() {
        return pagoId;
    }

    public void setPagoId(Long pagoId) {
        this.pagoId = pagoId;
    }

    @Override
    public String toString() {
        return "AbonoDTO{" + "abonoId=" + abonoId + ", monto=" + monto + ", fecha=" + fecha + ", hora=" + hora + ", pagoId=" + pagoId + '}';
    }
    
    

}
