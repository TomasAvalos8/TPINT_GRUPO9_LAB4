package DatosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Datos.ClienteDao;
import Dominio.Cliente;

public class ClienteDaoImpl implements ClienteDao {
private Conexion conexion;
	@Override
	public boolean insertarCliente(Cliente cliente) {
	    boolean estado = true;

	    conexion = new Conexion();
	    conexion.Open();

	    String query = "INSERT INTO Cliente (dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, id_localidad, id_provincia, correo_electronico, telefono, id_usuario, activo) VALUES ("
	            + cliente.getDni() + ", "
	            + cliente.getCuil() + ", '"
	            + cliente.getNombre() + "', '"
	            + cliente.getApellido() + "', '"
	            + cliente.getSexo() + "', '"
	            + cliente.getNacionalidad() + "', '"
	            + cliente.getFecha_nacimiento() + "', '"
	            + cliente.getDireccion() + "', "
	            + cliente.getId_localidad() + ", "
	            + cliente.getId_provincia() + ", '"
	            + cliente.getCorreo_electronico() + "', '"
	            + cliente.getTelefono() + "', "
	            + cliente.getIdUsuario() + ", "
	            + (cliente.getActivo() ? 1 : 0) + ")";

	    try {
	        estado = conexion.execute(query);
	    } catch (Exception e) {
	        e.printStackTrace();
	        estado = false;
	    } finally {
	        conexion.close();
	    }
	    return estado;
	}
	@Override
	public List<Cliente> listarClientes() {
	    List<Cliente> listaClientes = new ArrayList<>();
	    conexion = new Conexion();
	    conexion.Open();
	    
	    String query = "SELECT dni, nombre, apellido, sexo, nacionalidad, fecha_nacimiento FROM Cliente WHERE activo = 1";
	    
	    try {
	        ResultSet rs = conexion.executeQuery(query);
	        
	        while (rs.next()) {
	            Cliente cliente = new Cliente();
	            cliente.setDni(rs.getInt("dni"));
	            cliente.setNombre(rs.getString("nombre"));
	            cliente.setApellido(rs.getString("apellido"));
	            cliente.setSexo(rs.getString("sexo"));
	            cliente.setNacionalidad(rs.getString("nacionalidad"));
	            
	            // Manejo de la fecha de nacimiento
	            java.sql.Date fechaNacimientoSQL = rs.getDate("fecha_nacimiento");
	            if (fechaNacimientoSQL != null) {
	                cliente.setFecha_nacimiento(fechaNacimientoSQL.toLocalDate());
	            }
	            
	            listaClientes.add(cliente);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        conexion.close();
	    }
	    
	    return listaClientes;
	}

}
