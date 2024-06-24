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
    private List<Long> estatusId;

    public EstatusDTO() {
    }

    public EstatusDTO(Long id, String nombre, List<Long> estatusId) {
        this.id = id;
        this.nombre = nombre;
        this.estatusId = estatusId;
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
        return estatusId;
    }

    public void setEstatusId(List<Long> estatusId) {
        this.estatusId = estatusId;
    }

    @Override
    public String toString() {
        return "EstatusDTO{" + "id=" + id
                + ", nombre=" + nombre
                + ", estatusId=" + estatusId + '}';
    }

}
