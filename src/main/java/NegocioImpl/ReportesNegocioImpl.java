package NegocioImpl;

import java.util.Date;
import java.util.List;

import Datos.ReportesDao;
import DatosImpl.ReportesDaoImpl;
import Dominio.Reporte;
import Negocio.ReportesNegocio;

public class ReportesNegocioImpl implements ReportesNegocio {

	@Override
	public List<Reporte> generarReportePrestamos(Date fechaInicio, Date fechaFin) {
        ReportesDao rp = new ReportesDaoImpl(); 
        return rp.obtenerReportePrestamos(fechaInicio, fechaFin) ;
	}
	
	@Override
	public List<Reporte> generarReporteUsuarios(Date fechaInicio, Date fechaFin) {
	    ReportesDao rp = new ReportesDaoImpl(); 
	    return rp.obtenerReporteUsuarios(fechaInicio, fechaFin); 
	}
}
