package DatosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Datos.ReportesDao;
import Dominio.Reporte;

public class ReportesDaoImpl implements ReportesDao {
    
	private static final String queryPrestamos = "SELECT "
		    + "COUNT(*) AS TotalSolicitudesPrestamo, "
		    + "SUM(CASE WHEN estado = 'Aprobada' THEN 1 ELSE 0 END) AS total_prestamos_aprobados, "
		    + "SUM(CASE WHEN estado = 'Rechazada' THEN 1 ELSE 0 END) AS total_prestamos_rechazados, "
		    + "SUM(CASE WHEN estado = 'Pendiente' THEN 1 ELSE 0 END) AS total_prestamos_pendientes, "
		    + "(SUM(CASE WHEN estado = 'Aprobada' THEN 1 ELSE 0 END) * 100.0 / COUNT(*)) AS porcentaje_aprobados, "
		    + "(SUM(CASE WHEN estado = 'Rechazada' THEN 1 ELSE 0 END) * 100.0 / COUNT(*)) AS porcentaje_rechazados, "
		    + "(SUM(CASE WHEN estado = 'Pendiente' THEN 1 ELSE 0 END) * 100.0 / COUNT(*)) AS porcentaje_pendientes "
		    + "FROM SolicitudPrestamos "
		    + "WHERE fecha_solicitud BETWEEN ? AND ?"; 
    		 
   
    private static final String queryUsuarios = "SELECT "
    				    + "COUNT(*) AS TotalUsuarios, "
    				    + "SUM(CASE WHEN activo = TRUE THEN 1 ELSE 0 END) AS usuarios_activos, "
    				    + "SUM(CASE WHEN activo = FALSE THEN 1 ELSE 0 END) AS usuarios_inactivos, "
    				    + "(SUM(CASE WHEN activo = TRUE THEN 1 ELSE 0 END) * 100.0 / COUNT(*)) AS porcentaje_activos, "
    				    + "(SUM(CASE WHEN activo = FALSE THEN 1 ELSE 0 END) * 100.0 / COUNT(*)) AS porcentaje_inactivos "
    				    + "FROM Usuarios "
    				    + "WHERE fecha_alta BETWEEN ? AND ?";
    
    
    private static final String queryCuentasPorTipo = 
    	    "SELECT tc.id_tipo_cuenta, tc.descripcion, COUNT(c.id) AS cantidad " +
    	    "FROM TipoCuenta tc " +
    	    "LEFT JOIN Cuenta c ON tc.id_tipo_cuenta = c.tipo_cuenta " +
    	    "WHERE c.activo = TRUE " +  
    	    "AND c.fecha_creacion BETWEEN ? AND ? " +
    	    "GROUP BY tc.id_tipo_cuenta, tc.descripcion";
    Conexion conexion;
    
    public ReportesDaoImpl() {
    	super();
        this.conexion = new Conexion();
    }
    
    @Override
    public List<Reporte> obtenerReportePrestamos(Date fechaInicio, Date fechaFin) {
        List<Reporte> reportes = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {       
            conexion.Open();
          
            ps = conexion.prepareStatement(queryPrestamos);
            ps.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            ps.setDate(2, new java.sql.Date(fechaFin.getTime()));

            rs = ps.executeQuery();
            
            while (rs.next()) {
            	Reporte reporte = new Reporte();
                reporte.setTipoReporte("Prestamos");
                reporte.setFechaInicio(fechaInicio);
                reporte.setFechaFin(fechaFin);
               
                reporte.setTotalPrestamos(rs.getInt("TotalSolicitudesPrestamo"));
                reporte.setPorcAprobados(rs.getFloat("porcentaje_aprobados"));
                reporte.setPorcRechazados(rs.getFloat("porcentaje_rechazados"));
                reporte.setPorcPendientes(rs.getFloat("porcentaje_pendientes"));
                reportes.add(reporte);
        
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return reportes;
    }
    
    @Override
    public List<Reporte> obtenerReporteUsuarios(Date fechaInicio, Date fechaFin) {
        List<Reporte> reportes = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conexion.Open();
            ps = conexion.prepareStatement(queryUsuarios);
            ps.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            ps.setDate(2, new java.sql.Date(fechaFin.getTime()));
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Reporte reporte = new Reporte();
                reporte.setTipoReporte("Usuarios");
                reporte.setFechaInicio(fechaInicio);
                reporte.setFechaFin(fechaFin);
                
                reporte.setTotalUsuarios(rs.getInt("TotalUsuarios")); 
                reporte.setPorcActivos(rs.getFloat("porcentaje_activos")); 
                reporte.setPorcInactivos(rs.getFloat("porcentaje_inactivos")); 
                
                reportes.add(reporte);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return reportes;
    }
    
    
    @Override
    public List<Reporte> obtenerReporteCuentasPorTipo(Date fechaInicio, Date fechaFin) {
        List<Reporte> reportes = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conexion.Open();
            ps = conexion.prepareStatement(queryCuentasPorTipo);
            ps.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            ps.setDate(2, new java.sql.Date(fechaFin.getTime()));
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Reporte reporte = new Reporte();
                reporte.setTipoReporte("CuentasPorTipo");
                reporte.setDescripcionTipoCuenta(rs.getString("descripcion")); 
                reporte.setCantidadCuentas(rs.getInt("cantidad")); 
                reportes.add(reporte);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return reportes;
    }
    
}