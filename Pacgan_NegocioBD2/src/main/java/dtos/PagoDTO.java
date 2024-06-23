/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.List;

/**
 *
 * @author Usuario
 */
public class PagoDTO {

    private Long pagoId;
    private double monto;
    private byte[] comprobante;
    private Long tipoPagoId;
    private Long beneficiarioId;
    private Long cuentaBancariaId;
    private List<Long> abonoIds;
    private List<Long> estatusIds;

    public PagoDTO() {
    }

    public PagoDTO(Long pagoId, double monto, byte[] comprobante, Long tipoPagoId, Long beneficiarioId, Long cuentaBancariaId, List<Long> abonoIds, List<Long> estatusIds) {
        this.pagoId = pagoId;
        this.monto = monto;
        this.comprobante = comprobante;
        this.tipoPagoId = tipoPagoId;
        this.beneficiarioId = beneficiarioId;
        this.cuentaBancariaId = cuentaBancariaId;
        this.abonoIds = abonoIds;
        this.estatusIds = estatusIds;
    }

    public Long getPagoId() {
        return pagoId;
    }

    public void setPagoId(Long pagoId) {
        this.pagoId = pagoId;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public byte[] getComprobante() {
        return comprobante;
    }

    public void setComprobante(byte[] comprobante) {
        this.comprobante = comprobante;
    }

    public Long getTipoPagoId() {
        return tipoPagoId;
    }

    public void setTipoPagoId(Long tipoPagoId) {
        this.tipoPagoId = tipoPagoId;
    }

    public Long getBeneficiarioId() {
        return beneficiarioId;
    }

    public void setBeneficiarioId(Long beneficiarioId) {
        this.beneficiarioId = beneficiarioId;
    }

    public Long getCuentaBancariaId() {
        return cuentaBancariaId;
    }

    public void setCuentaBancariaId(Long cuentaBancariaId) {
        this.cuentaBancariaId = cuentaBancariaId;
    }

    public List<Long> getAbonoIds() {
        return abonoIds;
    }

    public void setAbonoIds(List<Long> abonoIds) {
        this.abonoIds = abonoIds;
    }

    public List<Long> getEstatusIds() {
        return estatusIds;
    }

    public void setEstatusIds(List<Long> estatusIds) {
        this.estatusIds = estatusIds;
    }

    @Override
    public String toString() {
        return "PagoDTO{" + "pagoId=" + pagoId + ", monto=" + monto + ", comprobante=" + comprobante + ", tipoPagoId=" + tipoPagoId + ", beneficiarioId=" + beneficiarioId + ", cuentaBancariaId=" + cuentaBancariaId + ", abonoIds=" + abonoIds + ", estatusIds=" + estatusIds + '}';
    }

}
