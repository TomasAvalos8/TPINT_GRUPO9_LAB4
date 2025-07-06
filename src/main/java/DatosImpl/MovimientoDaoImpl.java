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



}
