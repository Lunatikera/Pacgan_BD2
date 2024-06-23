/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import excepciones.NegocioException;

/**
 *
 * @author Usuario
 */
public interface IEliminarAbonoBO {

    public void eliminarAbono(Long id) throws NegocioException;
}
