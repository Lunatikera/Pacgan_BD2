/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Pago_EstatusEntidad;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author triny
 */
public interface IPago_EstatusDAO {
    
    public Pago_EstatusEntidad obtenerEstadoDelPago(Long pagoId ) throws PersistenciaException;
    
    public List<Pago_EstatusEntidad> obtenerHistorialDeEstados(Long pagoId) throws PersistenciaException;
    
}
