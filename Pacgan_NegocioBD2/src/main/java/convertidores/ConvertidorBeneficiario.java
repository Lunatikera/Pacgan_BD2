/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package convertidores;

import dtos.BeneficiarioDTO;
import entidades.BeneficiarioEntidad;
import entidades.CuentaBancariaEntidad;
import entidades.PagoEntidad;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ConvertidorBeneficiario {

    //DTO a Entidad
    public static BeneficiarioEntidad convertirDTOAEntidad(BeneficiarioDTO beneficarioDTO) throws PersistenciaException {
        BeneficiarioEntidad beneficiarioEntidad = new BeneficiarioEntidad();

        asignarAtributosSimples(beneficiarioEntidad, beneficarioDTO);

        List<PagoEntidad> pagos = convertirListaPagoIds(beneficarioDTO.getBeneficiarioPagoIds());
        beneficiarioEntidad.setBeneficiarioPago(pagos);

        // Convertir lista de IDs de cuentas a entidades de cuentas
        List<CuentaBancariaEntidad> cuentas = convertirListaCuentaIds(beneficarioDTO.getBeneficiarioCuentaIds());
        beneficiarioEntidad.setBeneficiarioCuenta(cuentas);

        return beneficiarioEntidad;
    }

    private static void asignarAtributosSimples(BeneficiarioEntidad beneficiarioEntidad, BeneficiarioDTO beneficarioDTO) {
        beneficiarioEntidad.setId(beneficarioDTO.getBeneficiarioId());
        beneficiarioEntidad.setClaveContrato(beneficarioDTO.getClaveContrato());
        beneficiarioEntidad.setNombres(beneficarioDTO.getNombre());
        beneficiarioEntidad.setApellidoPA(beneficarioDTO.getApellidoPA());
        beneficiarioEntidad.setApellidoMA(beneficarioDTO.getApellidoMA());
        beneficiarioEntidad.setSaldo(beneficarioDTO.getSaldo());
        beneficiarioEntidad.setNombreUsuario(beneficarioDTO.getNombreUsuario());
        beneficiarioEntidad.setContraseña(beneficarioDTO.getContraseña());
    }

    private static List<PagoEntidad> convertirListaPagoIds(List<Long> beneficiarioPagoIds) throws PersistenciaException {
        List<PagoEntidad> pagos = new ArrayList<>();
        for (Long pagoId : beneficiarioPagoIds) {
            PagoEntidad pagoEntidad = new PagoEntidad();
            pagoEntidad.setId(pagoId);
            // Aquí podrías cargar más detalles del pago desde la capa de persistencia si es necesario
            pagos.add(pagoEntidad);
        }
        return pagos;
    }

    private static List<CuentaBancariaEntidad> convertirListaCuentaIds(List<Long> beneficiarioCuentaIds) throws PersistenciaException {
        List<CuentaBancariaEntidad> cuentas = new ArrayList<>();
        for (Long cuentaId : beneficiarioCuentaIds) {
            CuentaBancariaEntidad cuentaEntidad = new CuentaBancariaEntidad();
            cuentaEntidad.setId(cuentaId);
            // Aquí podrías cargar más detalles de la cuenta desde la capa de persistencia si es necesario
            cuentas.add(cuentaEntidad);
        }
        return cuentas;
    }
    
    
    // Entidad a DTO 
    public static BeneficiarioDTO convertirEntidadADTO(BeneficiarioEntidad beneficiarioEntidad) throws PersistenciaException {
        BeneficiarioDTO beneficiarioDTO = new BeneficiarioDTO();

        // Asignar atributos simples
        asignarAtributosSimples(beneficiarioDTO, beneficiarioEntidad);

        // Convertir lista de entidades de pagos a IDs de pagos
        List<Long> pagoIds = convertirListaPagoEntidades(beneficiarioEntidad.getBeneficiarioPago());
        beneficiarioDTO.setBeneficiarioPagoIds(pagoIds);

        // Convertir lista de entidades de cuentas a IDs de cuentas
        List<Long> cuentaIds = convertirListaCuentaEntidades(beneficiarioEntidad.getBeneficiarioCuenta());
        beneficiarioDTO.setBeneficiarioCuentaIds(cuentaIds);

        return beneficiarioDTO;
    }

    private static void asignarAtributosSimples(BeneficiarioDTO beneficiarioDTO, BeneficiarioEntidad beneficiarioEntidad) {
        beneficiarioDTO.setBeneficiarioId(beneficiarioEntidad.getId());
        beneficiarioDTO.setClaveContrato(beneficiarioEntidad.getClaveContrato());
        beneficiarioDTO.setNombre(beneficiarioEntidad.getNombres());
        beneficiarioDTO.setApellidoPA(beneficiarioEntidad.getApellidoPA());
        beneficiarioDTO.setApellidoMA(beneficiarioEntidad.getApellidoMA());
        beneficiarioDTO.setSaldo(beneficiarioEntidad.getSaldo());
        beneficiarioDTO.setNombreUsuario(beneficiarioEntidad.getNombreUsuario());
        beneficiarioDTO.setContraseña(beneficiarioEntidad.getContraseña());
    }

    private static List<Long> convertirListaPagoEntidades(List<PagoEntidad> pagos) throws PersistenciaException {
        List<Long> pagoIds = new ArrayList<>();
        for (PagoEntidad pagoEntidad : pagos) {
            pagoIds.add(pagoEntidad.getId());
        }
        return pagoIds;
    }

    private static List<Long> convertirListaCuentaEntidades(List<CuentaBancariaEntidad> cuentas) throws PersistenciaException {
        List<Long> cuentaIds = new ArrayList<>();
        for (CuentaBancariaEntidad cuentaEntidad : cuentas) {
            cuentaIds.add(cuentaEntidad.getId());
        }
        return cuentaIds;
    }
}


