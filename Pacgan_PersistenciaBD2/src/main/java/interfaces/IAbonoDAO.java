/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.AbonoEntidad;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IAbonoDAO {

    public void agregarAbono(AbonoEntidad abono) throws PersistenciaException;

    public AbonoEntidad consultarAbonoPorID(Long id) throws PersistenciaException;

    public List<AbonoEntidad> listaAbonos() throws PersistenciaException;

    public void editarAbono(AbonoEntidad abono) throws PersistenciaException;

    public void eliminarAbono(Long id) throws PersistenciaException;
}
