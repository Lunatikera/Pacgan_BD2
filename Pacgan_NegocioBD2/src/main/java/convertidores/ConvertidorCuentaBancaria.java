/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package convertidores;

import dtos.CuentaBancariaDTO;
import entidades.BeneficiarioEntidad;
import entidades.CuentaBancariaEntidad;
import entidades.PagoEntidad;
import excepciones.PersistenciaException;
import interfaces.IBeneficiarioDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ConvertidorCuentaBancaria {

    private static IBeneficiarioDAO beneficiarioDAO;

    public ConvertidorCuentaBancaria(IBeneficiarioDAO beneficiarioDAO) {
        this.beneficiarioDAO = beneficiarioDAO;
    }

    public static CuentaBancariaEntidad convertirDTOAEntidad(CuentaBancariaDTO cuentaDTO) throws PersistenciaException {
        if (cuentaDTO == null) {
            throw new IllegalArgumentException("El DTO de cuenta bancaria no puede ser nulo");
        }

        CuentaBancariaEntidad cuentaEntidad = new CuentaBancariaEntidad();
        cuentaEntidad.setId(cuentaDTO.getCuentaBancariaId());
        cuentaEntidad.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuentaEntidad.setClabe(cuentaDTO.getClabe());
        cuentaEntidad.setNombreBanco(cuentaDTO.getNombreBanco());
        cuentaEntidad.setEstaEliminada(cuentaDTO.isEstaEliminada());

        BeneficiarioEntidad beneficiario = beneficiarioDAO.consultarBeneficiarioPorID(cuentaDTO.getBeneficiarioId());
        cuentaEntidad.setBeneficiarioCuenta(beneficiario);

        List<PagoEntidad> pagos = convertirListaPagosIds(cuentaDTO.getPagoIds());
        cuentaEntidad.setCuentaBancariaPagos(pagos);

        return cuentaEntidad;
    }

    private static List<PagoEntidad> convertirListaPagosIds(List<Long> cuentaPagoIds) throws PersistenciaException {
        List<PagoEntidad> pagos = new ArrayList<>();
        for (Long pagoId : cuentaPagoIds) {
            PagoEntidad pagoEntidad = new PagoEntidad();
            pagoEntidad.setId(pagoId);
            // Aquí podrías cargar más detalles del pago desde la capa de persistencia si es necesario
            pagos.add(pagoEntidad);
        }
        return pagos;
    }
    
    
    

    public static CuentaBancariaDTO convertirEntidadADTO(CuentaBancariaEntidad cuentaEntidad) throws PersistenciaException {
        if (cuentaEntidad == null) {
            throw new IllegalArgumentException("La entidad de cuenta bancaria no puede ser nula");
        }

        CuentaBancariaDTO cuentaDTO = new CuentaBancariaDTO(
                cuentaEntidad.getId(),
                cuentaEntidad.getNumeroCuenta(),
                cuentaEntidad.getClabe(),
                cuentaEntidad.getNombreBanco(),
                cuentaEntidad.isEstaEliminada(),
                cuentaEntidad.getBeneficiarioCuenta().getId(),
                convertirListaPagosEntidadIds(cuentaEntidad.getCuentaBancariaPagos())
        );

        return cuentaDTO;
    }

    private static List<Long> convertirListaPagosEntidadIds(List<PagoEntidad> pagos) {
        List<Long> pagoIds = new ArrayList<>();
        for (PagoEntidad pago : pagos) {
            pagoIds.add(pago.getId());
        }
        return pagoIds;
    }
}
