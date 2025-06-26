package DatosImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


import Datos.ProvinciaDao;
import Dominio.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao{

	public List<Provincia> obtenerTodasLasProvincias() {
	    List<Provincia> lista = new ArrayList<>();
	    Conexion cn = new Conexion();
	    Connection conexion = cn.Open();
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        String sql = "SELECT id_provincia, descripcion FROM Provincia";
	        ps = conexion.prepareStatement(sql);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Provincia provincia = new Provincia();
	            provincia.setId_provincia(rs.getInt("id_provincia"));
	            provincia.setDescripcion(rs.getString("descripcion"));
	            lista.add(provincia);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conexion != null) conexion.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return lista;
	}

}
