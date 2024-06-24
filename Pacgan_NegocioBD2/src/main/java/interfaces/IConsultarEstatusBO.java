/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.EstatusDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author triny
 */
public interface IConsultarEstatusBO {

    public EstatusDTO consultarEstatusPorID(Long id) throws NegocioException;

    public List<EstatusDTO> listaEstatus() throws NegocioException;

}
