package Negocio;

import java.util.Date;
import java.util.List;
import Dominio.Reporte;

public interface ReportesNegocio {
	List<Reporte> generarReportePrestamos(Date fechaInicio, Date fechaFin);
}
