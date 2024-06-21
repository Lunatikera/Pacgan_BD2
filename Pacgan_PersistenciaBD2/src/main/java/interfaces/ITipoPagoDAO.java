/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.TipoPagoEntidad;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface ITipoPagoDAO {

    public void agregarTipoPago(TipoPagoEntidad tipoPago) throws PersistenciaException;

    public TipoPagoEntidad consultarTipoPagoPorID(Long id) throws PersistenciaException;

    public List<TipoPagoEntidad> listaTiposPago() throws PersistenciaException;

    public void editarTipoPago(TipoPagoEntidad tipoPago) throws PersistenciaException;

    public void eliminarTipoPago(Long id) throws PersistenciaException;
}
