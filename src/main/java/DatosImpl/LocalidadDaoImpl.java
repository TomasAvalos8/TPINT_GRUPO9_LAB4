package DatosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Datos.LocalidadDao;
import Dominio.Localidad;
import Dominio.Provincia;

public class LocalidadDaoImpl implements LocalidadDao {

	public List<Localidad>obtenerLocalidadesPorProvincia(Provincia provincia) {
	    List<Localidad> lista = new ArrayList<>();
	    Conexion cn = new Conexion();
	    Connection conexion = cn.Open();
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        String sql = "SELECT id_localidad, descripcion, id_provincia FROM Localidad WHERE id_provincia = ?";
	        ps = conexion.prepareStatement(sql);
	        ps.setInt(1, provincia.getId_provincia());
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Localidad localidad = new Localidad();
	            localidad.setId_localidad(rs.getInt("id_localidad"));
	            localidad.setDescripcion(rs.getString("descripcion"));
	            Provincia prov = new Provincia();
	            prov.setId_provincia(rs.getInt("id_provincia"));
	            localidad.setProvincia(prov);
	            lista.add(localidad);
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
