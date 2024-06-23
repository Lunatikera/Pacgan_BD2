/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.AbonoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IConsultarAbonoBO {

    public AbonoDTO consultarAbonoPorID(Long id) throws NegocioException;

    public List<AbonoDTO> listaAbonos() throws NegocioException;
}
