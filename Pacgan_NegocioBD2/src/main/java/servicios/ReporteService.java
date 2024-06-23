/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import daos.PagoDAO;
import entidades.PagoEntidad;
import entidades.Pago_EstatusEntidad;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.time.LocalDate;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jesus
 */
@Stateless
public class ReporteService {

    @Inject
    private PagoDAO pagoDAO;

    public byte[] generarReportePDF(LocalDate fechaInicio, LocalDate fechaFin,
            Long tipoId, Boolean abonosTerminados,
            Long estatusId) throws IOException, DocumentException {
        List<PagoEntidad> pagos = pagoDAO.buscarPagosFiltrados(fechaInicio, fechaFin, tipoId, abonosTerminados, estatusId);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);

        document.open();
        PdfPTable table = new PdfPTable(6);

        table.addCell("Tipo");
        table.addCell("Monto");
        table.addCell("Nombre Beneficiario");
        table.addCell("Abonos Terminados");
        table.addCell("Cuenta Depósito");
        table.addCell("Estatus");

        for (PagoEntidad pago : pagos) {
            table.addCell(pago.getTipoPago().getNombreTipo());
            table.addCell(String.valueOf(pago.getMonto()));
            table.addCell(pago.getBeneficiarioPago().getNombres());
            table.addCell(determinarAbonosTerminados(pago));
            table.addCell(pago.getCuentaBancaria().getNumeroCuenta());
            table.addCell(obtenerUltimoEstatus(pago));
        }

        document.add(table);
        document.close();

        return baos.toByteArray();
    }

    private String determinarAbonosTerminados(PagoEntidad pago) {
        // Implementa la lógica para determinar si los abonos están terminados
        return "Sí/No";
    }

    private String obtenerUltimoEstatus(PagoEntidad pago) {
        List<Pago_EstatusEntidad> estatusList = pago.getPagoEstatus();
        if (estatusList != null && !estatusList.isEmpty()) {
            return estatusList.get(estatusList.size() - 1).getEstatus().getNombre();
        }
        return "N/A";
    }
}
