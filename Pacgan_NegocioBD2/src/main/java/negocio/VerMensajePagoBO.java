/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import interfaces.IPagoDAO;

/**
 *
 * @author Usuario
 */
public class VerMensajePagoBO {

    IPagoDAO pagoDAO;

    public VerMensajePagoBO(IPagoDAO pagoDAO) {
        this.pagoDAO = pagoDAO;
    }
}
