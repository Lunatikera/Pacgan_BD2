/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.List;

/**
 *
 * @author triny
 */
public class EstatusDTO {
    
    private Long estatusId;
    private String nombre;
    private List<Long> estatus;

    public EstatusDTO() {
    }

    public EstatusDTO(Long estatusId, String nombre, List<Long> estatus) {
        this.estatusId = estatusId;
        this.nombre = nombre;
        this.estatus = estatus;
    }

    public Long getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(Long estatusId) {
        this.estatusId = estatusId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Long> getEstatus() {
        return estatus;
    }

    public void setEstatus(List<Long> estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "EstatusDto{" +
                "id_estatus=" + estatusId +
                ", nombre='" + nombre + '\'' +
                ", estatus=" + estatus +
                '}';
    }
}

