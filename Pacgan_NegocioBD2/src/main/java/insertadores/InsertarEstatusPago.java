/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package insertadores;

import entidades.EstatusEntidad;
import entidades.TipoPagoEntidad;
import interfaces.IEstatusDAO;
import interfaces.ITipoPagoDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jesus
 */
public class InsertarEstatusPago {

    private IEstatusDAO estatusDAO;
    private ITipoPagoDAO tipoPagoDAO;

    public InsertarEstatusPago(IEstatusDAO estatusDAO, ITipoPagoDAO tipoPagoDAO) {
        this.estatusDAO = estatusDAO;
        this.tipoPagoDAO = tipoPagoDAO;
    }

    public void insertarEstatus() {
   
    }

    public void insertarTipoPago() {
   
    }
}
