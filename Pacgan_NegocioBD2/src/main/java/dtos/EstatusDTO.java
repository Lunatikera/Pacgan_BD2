/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.List;

/**
 *
 * @author jesus
 */
public class EstatusDTO {

    private Long id;
    private String nombre;
    private List<Long> pagoEstatusIds;

    public EstatusDTO() {
    }

    public EstatusDTO(Long id, String nombre, List<Long> estatusId) {
        this.id = id;
        this.nombre = nombre;
        this.pagoEstatusIds = estatusId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Long> getEstatusId() {
        return pagoEstatusIds;
    }

    public void setEstatusId(List<Long> estatusId) {
        this.pagoEstatusIds = estatusId;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
