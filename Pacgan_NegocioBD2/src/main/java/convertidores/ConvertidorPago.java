/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package convertidores;

import dtos.PagoDTO;
import entidades.AbonoEntidad;
import entidades.BeneficiarioEntidad;
import entidades.CuentaBancariaEntidad;
import entidades.PagoEntidad;
import entidades.Pago_EstatusEntidad;
import entidades.TipoPagoEntidad;
import excepciones.PersistenciaException;
import interfaces.IBeneficiarioDAO;
import interfaces.ICuentaBancariaDAO;
import interfaces.ITipoPagoDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Usuario
 */
public class ConvertidorPago {

    private static ITipoPagoDAO tipoPagoDAO;
    private static IBeneficiarioDAO beneficiarioDAO;
    private static ICuentaBancariaDAO cuentaBancariaDAO;

    public ConvertidorPago(ITipoPagoDAO tipoPagoDAO, IBeneficiarioDAO beneficiarioDAO, ICuentaBancariaDAO cuentaBancariaDAO) {
        this.tipoPagoDAO = tipoPagoDAO;
        this.beneficiarioDAO = beneficiarioDAO;
        this.cuentaBancariaDAO = cuentaBancariaDAO;
    }

    public static PagoEntidad convertirDTOAEntidad(PagoDTO pagoDTO) throws PersistenciaException {
        if (pagoDTO == null) {
            throw new IllegalArgumentException("El DTO de pago no puede ser nulo");
        }

        PagoEntidad pagoEntidad = new PagoEntidad();
        pagoEntidad.setId(pagoDTO.getPagoId());
        pagoEntidad.setMonto(pagoDTO.getMonto());
        pagoEntidad.setComprobante(pagoDTO.getComprobante());

        TipoPagoEntidad tipoPagoEntidad = tipoPagoDAO.consultarTipoPagoPorID(pagoDTO.getTipoPagoId());
        if (tipoPagoEntidad != null) {
            pagoEntidad.setTipoPago(tipoPagoEntidad);
        } else {
            throw new PersistenciaException("No se encontró el tipo de pago con ID: " + pagoDTO.getTipoPagoId());
        }

        BeneficiarioEntidad beneficiarioEntidad = beneficiarioDAO.consultarBeneficiarioPorID(pagoDTO.getBeneficiarioId());
        if (beneficiarioEntidad != null) {
            pagoEntidad.setBeneficiarioPago(beneficiarioEntidad);
        } else {
            throw new PersistenciaException("No se encontró el beneficiario con ID: " + pagoDTO.getBeneficiarioId());
        }

        CuentaBancariaEntidad cuentaBancariaEntidad = cuentaBancariaDAO.consultarCuentaBancariaPorID(pagoDTO.getCuentaBancariaId());
        if (cuentaBancariaEntidad != null) {
            pagoEntidad.setCuentaBancaria(cuentaBancariaEntidad);
        } else {
            throw new PersistenciaException("No se encontró la cuenta bancaria con ID: " + pagoDTO.getCuentaBancariaId());
        }

        List<AbonoEntidad> abonos = convertirListaAbonosIds(pagoDTO.getAbonoIds());
        pagoEntidad.setPagoAbonos(abonos);

        List<Pago_EstatusEntidad> estatus = convertirListaEstatusIds(pagoDTO.getEstatusIds());
        pagoEntidad.setPagoEstatus(estatus);
        // No se convierten los pagos abonos ni estatus porque no se proporciona una lista
        return pagoEntidad;
    }

    private static List<AbonoEntidad> convertirListaAbonosIds(List<Long> pagoAbonosIds) throws PersistenciaException {
        List<AbonoEntidad> abonos = new ArrayList<>();
        for (Long abonoId : pagoAbonosIds) {
            AbonoEntidad abonoEntidad = new AbonoEntidad();
            abonoEntidad.setId(abonoId);
            // Aquí podrías cargar más detalles del pago desde la capa de persistencia si es necesario
            abonos.add(abonoEntidad);
        }
        return abonos;
    }

    private static List<Pago_EstatusEntidad> convertirListaEstatusIds(List<Long> pagoEstatusIds) throws PersistenciaException {
        List<Pago_EstatusEntidad> pagoEstatus = new ArrayList<>();
        for (Long estatusId : pagoEstatusIds) {
            Pago_EstatusEntidad pagoEntidad = new Pago_EstatusEntidad();
            pagoEntidad.setId(estatusId);
            // Aquí podrías cargar más detalles del pago desde la capa de persistencia si es necesario
            pagoEstatus.add(pagoEntidad);
        }
        return pagoEstatus;
    }

    public static PagoDTO convertirEntidadADTO(PagoEntidad pagoEntidad) {
        if (pagoEntidad == null) {
            throw new IllegalArgumentException("La entidad de pago no puede ser nula");
        }

        PagoDTO pagoDTO = new PagoDTO(
                pagoEntidad.getId(),
                pagoEntidad.getMonto(),
                pagoEntidad.getComprobante(),
                pagoEntidad.getTipoPago().getId(),
                pagoEntidad.getBeneficiarioPago().getId(),
                pagoEntidad.getCuentaBancaria().getId(),
                convertirListaAbonosEntidadIds(pagoEntidad.getPagoAbonos()),
                convertirListaEstatusEntidadIds(pagoEntidad.getPagoEstatus()));

        return pagoDTO;
    }

    private static List<Long> convertirListaAbonosEntidadIds(List<AbonoEntidad> abonos) {
        List<Long> abonoIds = new ArrayList<>();
        for (AbonoEntidad abono : abonos) {
            abonoIds.add(abono.getId());
        }
        return abonoIds;
    }

    private static List<Long> convertirListaEstatusEntidadIds(List<Pago_EstatusEntidad> estatus) {
        List<Long> estatusIds = new ArrayList<>();
        for (Pago_EstatusEntidad status : estatus) {
            estatusIds.add(status.getId());
        }
        return estatusIds;
    }
}
