/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servicios;

import dtos.AbonoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IGestionarAbonos {

    public void agregarAbono(AbonoDTO abono) throws NegocioException;

    public AbonoDTO consultarAbonoPorID(Long id) throws NegocioException;

    public List<AbonoDTO> listaAbonos() throws NegocioException;

    public void eliminarAbono(Long id) throws NegocioException;
}
