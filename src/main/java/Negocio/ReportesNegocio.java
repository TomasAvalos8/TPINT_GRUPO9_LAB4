package Negocio;

import java.util.Date;
import java.util.List;
import Dominio.Reporte;

public interface ReportesNegocio {
	List<Reporte> generarReportePrestamos(Date fechaInicio, Date fechaFin);
	List<Reporte> generarReporteUsuarios(Date fechaInicio, Date fechaFin); 
	List<Reporte> generarReporteCuentasPorTipo(Date fechaInicio, Date fechaFin);
}
