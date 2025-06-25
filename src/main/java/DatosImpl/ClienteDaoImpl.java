package DatosImpl;

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


}
