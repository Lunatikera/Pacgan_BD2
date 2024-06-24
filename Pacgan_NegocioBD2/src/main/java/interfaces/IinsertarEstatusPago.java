/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import excepciones.NegocioException;

/**
 *
 * @author jesus
 */
public interface IinsertarEstatusPago {

    public void insertarTiposDePagoPredeterminados() throws NegocioException;

    public void insertarTiposDeEstatusPredeterminados() throws NegocioException;
}
