/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package insertadores;

import convertidores.ConvertidorEstatus;
import convertidores.ConvertidorTipoPago;
import daos.ConexionBD;
import daos.EstatusDAO;
import daos.TipoPagoDAO;
import dtos.EstatusDTO;
import dtos.TipoPagoDTO;
import entidades.EstatusEntidad;
import entidades.TipoPagoEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IEstatusDAO;
import interfaces.ITipoPagoDAO;
import interfaces.IinsertarEstatusPago;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jesus
 */
public class InsertarEstatusPago implements IinsertarEstatusPago {

    private IEstatusDAO estatusDAO;
    private ITipoPagoDAO tipoPagoDAO;


    public InsertarEstatusPago(ITipoPagoDAO tipoPagoDAO, IEstatusDAO estatusDAO) {
        this.tipoPagoDAO = tipoPagoDAO;
        this.estatusDAO = estatusDAO;
    }

    @Override
    public void insertarTiposDePagoPredeterminados() throws NegocioException {
        try {
            TipoPagoDTO tipoPagoDTO1 = new TipoPagoDTO(null, "Reembolso", 1, null);
            TipoPagoDTO tipoPagoDTO2 = new TipoPagoDTO(null, "Proveedor", 5, null);
            TipoPagoDTO tipoPagoDTO3 = new TipoPagoDTO(null, "Reembolso", 7, null);

            TipoPagoEntidad tipoPago1 = ConvertidorTipoPago.convertirDTOaEntidad(tipoPagoDTO1);
            TipoPagoEntidad tipoPago2 = ConvertidorTipoPago.convertirDTOaEntidad(tipoPagoDTO2);
            TipoPagoEntidad tipoPago3 = ConvertidorTipoPago.convertirDTOaEntidad(tipoPagoDTO3);

            tipoPagoDAO.agregarTipoPago(tipoPago1);
            tipoPagoDAO.agregarTipoPago(tipoPago2);
            tipoPagoDAO.agregarTipoPago(tipoPago3);
        } catch (PersistenciaException ex) {
            Logger.getLogger(InsertarEstatusPago.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insertarTiposDeEstatusPredeterminados() throws NegocioException {
        try {
            EstatusDTO estatusDTO1 = new EstatusDTO(null, "Creado", null);
            EstatusDTO estatusDTO2 = new EstatusDTO(null, "Autorizado", null);
            EstatusDTO estatusDTO3 = new EstatusDTO(null, "Rechazado", null);
            EstatusDTO estatusDTO4 = new EstatusDTO(null, "Pagado", null);
            EstatusDTO estatusDTO5 = new EstatusDTO(null, "Modificado", null);

            EstatusEntidad estatusEntidad1 = ConvertidorEstatus.convertirDTOaEntidad(estatusDTO1);
            EstatusEntidad estatusEntidad2 = ConvertidorEstatus.convertirDTOaEntidad(estatusDTO2);
            EstatusEntidad estatusEntidad3 = ConvertidorEstatus.convertirDTOaEntidad(estatusDTO3);
            EstatusEntidad estatusEntidad4 = ConvertidorEstatus.convertirDTOaEntidad(estatusDTO4);
            EstatusEntidad estatusEntidad5 = ConvertidorEstatus.convertirDTOaEntidad(estatusDTO5);

            estatusDAO.agregarEstatus(estatusEntidad1);
            estatusDAO.agregarEstatus(estatusEntidad2);
            estatusDAO.agregarEstatus(estatusEntidad3);
            estatusDAO.agregarEstatus(estatusEntidad4);
            estatusDAO.agregarEstatus(estatusEntidad5);
        } catch (PersistenciaException ex) {
            Logger.getLogger(InsertarEstatusPago.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
