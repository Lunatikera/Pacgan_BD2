/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.io.IOException;
import java.time.LocalDate;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import servicios.ReporteService;
import javax.ws.rs.QueryParam;

/**
 *
 * @author jesus
 */
@Path("/reportes")
public class ReporteController {

    ReporteService reporteService;

    @POST
    @Path("/generar")
    @Produces("application/pdf")
    public Response generarReporte(@QueryParam("fechaInicio") String fechaInicioStr,
            @QueryParam("fechaFin") String fechaFinStr,
            @QueryParam("tipoId") Long tipoId,
            @QueryParam("abonosTerminados") Boolean abonosTerminados,
            @QueryParam("estatusId") Long estatusId) {
        try {
            LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);
            LocalDate fechaFin = LocalDate.parse(fechaFinStr);

            byte[] reportePDF = reporteService.generarReportePDF(fechaInicio, fechaFin, tipoId, abonosTerminados, estatusId);

            return Response.ok(reportePDF)
                    .header("Content-Disposition", "attachment; filename=reporte.pdf")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al generar el reporte").build();
        }
    }
}
