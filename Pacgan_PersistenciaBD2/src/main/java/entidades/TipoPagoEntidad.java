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
@Table(name = "tblTipoPagos")
public class TipoPagoEntidad implements Serializable {

    @Id
    @Column(name = "id_tipoPago")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tipoPagos;

    @Column(name = "nombreTipo")
    private String nombreTipo;

    @Column(name = "numeroParcialidades")
    private Integer numeroParcialidades;

    @OneToMany(mappedBy = "tipoPago", cascade = {CascadeType.PERSIST})
    private List<PagoEntidad> tipoPago;

    public TipoPagoEntidad() {
    }

    public TipoPagoEntidad(String nombreTipo, Integer numeroParcialidades, List<PagoEntidad> tipoPago) {
        this.nombreTipo = nombreTipo;
        this.numeroParcialidades = numeroParcialidades;
        this.tipoPago = tipoPago;
    }

    public Long getId() {
        return id_tipoPagos;
    }

    public void setId(Long id_tipoPagos) {
        this.id_tipoPagos = id_tipoPagos;
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

    public List<PagoEntidad> getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(List<PagoEntidad> tipoPago) {
        this.tipoPago = tipoPago;
    }

    @Override
    public String toString() {
        return "TipoPagoEntidad{" + "id_tipoPagos=" + id_tipoPagos + ", nombreTipo=" + nombreTipo + ", numeroParcialidades=" + numeroParcialidades + ", tipoPago=" + tipoPago + '}';
    }
    
    
}
