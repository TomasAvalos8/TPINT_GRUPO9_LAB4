package DatosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Datos.TipoMovimientoDao;
import Dominio.TipoMovimiento;

public class TipoMovimientoDaoImpl implements TipoMovimientoDao{

	 public List<TipoMovimiento> obtenerTodos() {
	        List<TipoMovimiento> tipos = new ArrayList<>();
	        Conexion cn = new Conexion();
	        Connection conexion = cn.Open();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {
	            String sql = "SELECT id_tipo_movimiento, descripcion FROM TipoMovimiento";
	            ps = conexion.prepareStatement(sql);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                TipoMovimiento tipo = new TipoMovimiento();
	                tipo.setIdTipoMovimiento(rs.getInt("id_tipo_movimiento"));
	                tipo.setDescripcion(rs.getString("descripcion"));
	                tipos.add(tipo);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (ps != null) ps.close();
	                if (conexion != null) conexion.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return tipos;
	    }
}
