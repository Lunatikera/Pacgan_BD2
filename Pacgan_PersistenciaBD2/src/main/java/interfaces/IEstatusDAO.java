/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.EstatusEntidad;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IEstatusDAO {

    public EstatusEntidad consultarEstatusPorID(Long id) throws PersistenciaException;

    public List<EstatusEntidad> listaEstatus() throws PersistenciaException;

    public void agregarEstatus(EstatusEntidad estatus) throws PersistenciaException;

}
