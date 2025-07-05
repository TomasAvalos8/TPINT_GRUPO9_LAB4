package DatosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Datos.MovimientoDao;
import Dominio.Cliente;
import Dominio.Movimiento;

public class MovimientoDaoImpl implements MovimientoDao{
private Conexion conexion;
	@Override
	public List<Movimiento> listarMovimientos() {
	        List<Movimiento> listaMovimientos = new ArrayList<>();
	        conexion = new Conexion();
	        conexion.Open();
	        
	        String query = "SELECT dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, id_localidad, id_provincia, correo_electronico, telefono, id_usuario, activo FROM Cliente WHERE activo = 1";
	        
	        try {
	            ResultSet rs = conexion.executeQuery(query);
	            
	            while (rs.next()) {
	             Movimiento movimiento = new Movimiento();
	                movimiento.setIdMovimiento(0);
	                movimiento.setDetalle(query);
	                movimiento.setMonto(0);
	                movimiento.setNumeroCuenta(0);
	                movimiento.setTipoMovimiento(null);
	                
	                java.sql.Date fechaSQL = rs.getDate("fecha");
	                if (fechaSQL != null) {
	                    movimiento.setFecha(fechaSQL.toLocalDate());
	                }
	                
	                listaMovimientos.add(movimiento);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            conexion.close();
	        }
	        
	        return listaMovimientos;
	    }

}
