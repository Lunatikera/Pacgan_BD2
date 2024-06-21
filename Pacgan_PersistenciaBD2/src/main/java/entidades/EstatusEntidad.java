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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "tblEstatus")
public class EstatusEntidad implements Serializable {

    @Id
    @Column(name = "id_estatus")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_estatus;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "estatus", cascade = {CascadeType.PERSIST})
    private List<Pago_EstatusEntidad> estatus;

    public EstatusEntidad() {
    }

    public EstatusEntidad(String nombre, List<Pago_EstatusEntidad> estatus) {
        this.nombre = nombre;
        this.estatus = estatus;
    }

    public Long getId() {
        return id_estatus;
    }

    public void setId(Long id_estatus) {
        this.id_estatus = id_estatus;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Pago_EstatusEntidad> getEstatus() {
        return estatus;
    }

    public void setEstatus(List<Pago_EstatusEntidad> estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "EstatusEntidad{" + "id_estatus=" + id_estatus + ", nombre=" + nombre + ", estatus=" + estatus + '}';
    }
    
    
}
