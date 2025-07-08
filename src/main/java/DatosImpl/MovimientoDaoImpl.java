package DatosImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Datos.MovimientoDao;
import Dominio.Cliente;
import Dominio.Movimiento;
import Dominio.TipoMovimiento;

public class MovimientoDaoImpl implements MovimientoDao{
	
	@Override
	public List<Movimiento> listarMovimientosPorCuenta(int nroCuenta) {
	    List<Movimiento> lista = new ArrayList<>();
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    Conexion cn = new Conexion();
	    Connection con = cn.Open();

	    try {
	        String sql = "SELECT m.id_movimiento, m.numero_cuenta, m.detalle, m.monto, m.fecha, " +
	                     "tm.id_tipo_movimiento, tm.descripcion AS tipo_movimiento " +
	                     "FROM Movimiento m " +
	                     "INNER JOIN TipoMovimiento tm ON m.id_tipo_movimiento = tm.id_tipo_movimiento " +
	                     "WHERE m.numero_cuenta = ? " +
	                     "ORDER BY m.fecha DESC";

	        ps = con.prepareStatement(sql);
	        ps.setInt(1, nroCuenta);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Movimiento mov = new Movimiento();
	            mov.setIdMovimiento(rs.getInt("id_movimiento"));
	            mov.setNumeroCuenta(rs.getInt("numero_cuenta"));
	            mov.setDetalle(rs.getString("detalle"));
	            mov.setMonto(rs.getFloat("monto"));

	            Date fechaSql = rs.getDate("fecha");
	            if (fechaSql != null) {
	                mov.setFecha(fechaSql.toLocalDate());
	            }

	            TipoMovimiento tipo = new TipoMovimiento();
	            tipo.setIdTipoMovimiento(rs.getInt("id_tipo_movimiento"));
	            tipo.setDescripcion(rs.getString("tipo_movimiento"));
	            mov.setTipoMovimiento(tipo);

	            lista.add(mov);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return lista;
	}
	
	public List<Movimiento> listarMovimientosPorCuentaYFechas(int nroCuenta, String fechaDesde, String fechaHasta) {
	    List<Movimiento> lista = new ArrayList<>();
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    Conexion cn = new Conexion();
	    Connection con = cn.Open();

	    try {
	        String sql = "SELECT m.id_movimiento, m.numero_cuenta, m.detalle, m.monto, m.fecha, " +
	                     "tm.id_tipo_movimiento, tm.descripcion AS tipo_movimiento " +
	                     "FROM Movimiento m " +
	                     "INNER JOIN TipoMovimiento tm ON m.id_tipo_movimiento = tm.id_tipo_movimiento " +
	                     "WHERE m.numero_cuenta = ? AND m.fecha BETWEEN ? AND ? " +
	                     "ORDER BY m.fecha DESC";

	        ps = con.prepareStatement(sql);
	        ps.setInt(1, nroCuenta);
	        ps.setDate(2, java.sql.Date.valueOf(fechaDesde));
	        ps.setDate(3, java.sql.Date.valueOf(fechaHasta));
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Movimiento mov = new Movimiento();
	            mov.setIdMovimiento(rs.getInt("id_movimiento"));
	            mov.setNumeroCuenta(rs.getInt("numero_cuenta"));
	            mov.setDetalle(rs.getString("detalle"));
	            mov.setMonto(rs.getFloat("monto"));

	            Date fechaSql = rs.getDate("fecha");
	            if (fechaSql != null) {
	                mov.setFecha(fechaSql.toLocalDate());
	            }

	            TipoMovimiento tipo = new TipoMovimiento();
	            tipo.setIdTipoMovimiento(rs.getInt("id_tipo_movimiento"));
	            tipo.setDescripcion(rs.getString("tipo_movimiento"));
	            mov.setTipoMovimiento(tipo);

	            lista.add(mov);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return lista;
	}


	@Override
	public void insertarMovimiento(Movimiento movimiento) {
		  Conexion cn = new Conexion();
		    Connection con = cn.Open();
		    PreparedStatement ps = null;

		    try {
		        String sql = "INSERT INTO Movimiento (numero_cuenta, detalle, monto, fecha, id_tipo_movimiento) " +
		                     "VALUES (?, ?, ?, ?, ?)";
		        ps = con.prepareStatement(sql);
		        ps.setInt(1, movimiento.getNumeroCuenta());
		        ps.setString(2, movimiento.getDetalle());
		        ps.setFloat(3, movimiento.getMonto());
		        ps.setDate(4, java.sql.Date.valueOf(movimiento.getFecha()));
		        ps.setInt(5, movimiento.getTipoMovimiento().getIdTipoMovimiento());

		        ps.executeUpdate();

		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (ps != null) ps.close();
		            if (con != null) con.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        }
		
	}



}
