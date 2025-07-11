package Datos;


import java.util.Date;
import java.util.List;

import Dominio.Reporte;

public interface ReportesDao {
    List<Reporte> obtenerReportePrestamos(Date fechaInicio, Date fechaFin);

	List<Reporte> obtenerReporteUsuarios(Date fechaInicio, Date fechaFin);

	List<Reporte> obtenerReporteCuentasPorTipo(Date fechaInicio, Date fechaFin);
}
