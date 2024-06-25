/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author jesus
 */
public class TipoPagoDTO {

    private Long id;
    private String nombreTipo;
    private Integer numeroParcialidades;
    private List<Long> pagosId;

    public TipoPagoDTO() {
    }

    public TipoPagoDTO(Long id, String nombreTipo, Integer numeroParcialidades, List<Long> pagosId) {
        this.id = id;
        this.nombreTipo = nombreTipo;
        this.numeroParcialidades = numeroParcialidades;
        this.pagosId = pagosId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public Integer getNumeroParcialidades() {
        return numeroParcialidades;
    }

    public void setNumeroParcialidades(Integer numeroParcialidades) {
        this.numeroParcialidades = numeroParcialidades;
    }

    public List<Long> getPagosId() {
        return pagosId;
    }

    public void setPagosId(List<Long> pagosId) {
        this.pagosId = pagosId;
    }

    @Override
    public String toString() {
        return nombreTipo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoPagoDTO other = (TipoPagoDTO) obj;
        return Objects.equals(this.id, other.id);
    }

}
