package DatosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Datos.ClienteDao;
import Dominio.Cliente;
import Dominio.Provincia;
import Dominio.Localidad;
import Dominio.Usuario;
import Negocio.UsuarioNeg;
import Excepciones.ClienteYaExisteException;

public class ClienteDaoImpl implements ClienteDao {
private Conexion conexion;
	@Override
	public boolean insertarCliente(Cliente cliente) throws ClienteYaExisteException {
	    PreparedStatement ps = null;
	    Connection conexion = null;
	    boolean estado = false;

	    try {
	        Conexion cn = new Conexion();
	        conexion = cn.Open();

	        String checkSql = "SELECT COUNT(*) FROM Cliente WHERE dni = ? AND activo = 1";
	        ps = conexion.prepareStatement(checkSql);
	        ps.setInt(1, cliente.getDni());
	        ResultSet rs = ps.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            rs.close();
	            ps.close();
	            throw new ClienteYaExisteException("Error: el cliente ya existe");
	        }
	        rs.close();
	        ps.close();

	        String sql = "INSERT INTO Cliente (dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, id_localidad, id_provincia, correo_electronico, telefono, id_usuario, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        ps = conexion.prepareStatement(sql);
	        
	        ps.setInt(1, cliente.getDni());
	        ps.setString(2, cliente.getCuil());
	        ps.setString(3, cliente.getNombre());
	        ps.setString(4, cliente.getApellido());
	        ps.setString(5, cliente.getSexo());
	        ps.setString(6, cliente.getNacionalidad());
	        ps.setDate(7, java.sql.Date.valueOf(cliente.getFecha_nacimiento()));
	        ps.setString(8, cliente.getDireccion());
	        ps.setInt(9, cliente.getLocalidad().getId_localidad());
	        ps.setInt(10, cliente.getProvincia().getId_provincia());
	        ps.setString(11, cliente.getCorreo_electronico());
	        ps.setString(12, cliente.getTelefono());
	        ps.setInt(13, cliente.getUsuario() != null ? cliente.getUsuario().getId_usuario() : 0);
	        ps.setBoolean(14, cliente.getActivo());

	        estado = ps.executeUpdate() > 0;
	        return estado;
	    } catch (ClienteYaExisteException e) {
	        throw e;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (conexion != null) conexion.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	@Override
	public List<Cliente> listarClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        conexion = new Conexion();
        conexion.Open();
        
        String query = "SELECT dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, id_localidad, id_provincia, correo_electronico, telefono, id_usuario, activo FROM Cliente WHERE activo = 1";
        
        try {
            ResultSet rs = conexion.executeQuery(query);
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setDni(rs.getInt("dni"));
                cliente.setCuil(rs.getString("cuil"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setNacionalidad(rs.getString("nacionalidad"));
                java.sql.Date fechaNacimientoSQL = rs.getDate("fecha_nacimiento");
                if (fechaNacimientoSQL != null) {
                    cliente.setFecha_nacimiento(fechaNacimientoSQL.toLocalDate());
                }
                cliente.setDireccion(rs.getString("direccion"));
                
                Localidad localidad = new Localidad();
                localidad.setId_localidad(rs.getInt("id_localidad"));
                cliente.setLocalidad(localidad);
                
                Provincia provincia = new Provincia();
                provincia.setId_provincia(rs.getInt("id_provincia"));
                cliente.setProvincia(provincia);
                cliente.setCorreo_electronico(rs.getString("correo_electronico"));
                cliente.setTelefono(rs.getString("telefono"));
                
                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                cliente.setUsuario(usuario);
                
                cliente.setActivo(rs.getBoolean("activo"));
                listaClientes.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.close();
        }
        
        return listaClientes;
    }
	
	public boolean eliminarCliente(int dni) {
		PreparedStatement ps = null;
	    Conexion cn = new Conexion();
	    Connection conexion = cn.Open();
	    boolean eliminado = false;
	    try {
	        String sql = "UPDATE Cliente SET activo = false WHERE dni = ?";
	        ps = (PreparedStatement) conexion.prepareStatement(sql);
	        ps.setInt(1, dni);
	        int filasAfectadas = ps.executeUpdate();


			String sqlUser = "UPDATE Usuarios SET activo = false WHERE id_usuario = (SELECT id_usuario FROM Cliente WHERE dni = ?)";
			ps = (PreparedStatement) conexion.prepareStatement(sqlUser);
			ps.setInt(1, dni);
	        int filasAfectadasUser = ps.executeUpdate();

	        eliminado = filasAfectadasUser > 0 && filasAfectadas > 0;
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (conexion != null) conexion.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return eliminado;
	}
	
	public boolean actualizarCliente(Cliente cliente){
        PreparedStatement ps = null;
        Conexion cn = new Conexion();
        Connection conexion = cn.Open();
        boolean actualizado = false;
        try {
            String sql = "UPDATE Cliente SET cuil=?, nombre=?, apellido=?, sexo=?, nacionalidad=?, fecha_nacimiento=?, direccion=?, id_localidad=?, id_provincia=?, correo_electronico=?, telefono=?, id_usuario=?, activo=? WHERE dni=?";
            ps = (PreparedStatement) conexion.prepareStatement(sql);
            ps.setString(1, cliente.getCuil());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getSexo());
            ps.setString(5, cliente.getNacionalidad());
            ps.setDate(6, java.sql.Date.valueOf(cliente.getFecha_nacimiento()));
            ps.setString(7, cliente.getDireccion());
            ps.setInt(8, cliente.getLocalidad().getId_localidad());
            ps.setInt(9, cliente.getProvincia().getId_provincia());
            ps.setString(10, cliente.getCorreo_electronico());
            ps.setString(11, cliente.getTelefono());
            ps.setInt(12, cliente.getUsuario() != null ? cliente.getUsuario().getId_usuario() : 0);
            ps.setBoolean(13, cliente.getActivo());
            ps.setInt(14, cliente.getDni());

            int filas = ps.executeUpdate();
            actualizado = filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return actualizado;
    }
	
	public Cliente obtenerClientePorIdUsuario(int idUsuario) {
        Cliente cliente = null;
        conexion = new Conexion();
        conexion.Open();
        String query = "SELECT * FROM Cliente WHERE id_usuario = ? AND activo = 1";
        try {
            PreparedStatement ps = (PreparedStatement) conexion.connection.prepareStatement(query);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setDni(rs.getInt("dni"));
                cliente.setCuil(rs.getString("cuil"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setNacionalidad(rs.getString("nacionalidad"));
                java.sql.Date fechaNacimientoSQL = rs.getDate("fecha_nacimiento");
                if (fechaNacimientoSQL != null) {
                    cliente.setFecha_nacimiento(fechaNacimientoSQL.toLocalDate());
                }
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setLocalidad(new Localidad(rs.getInt("id_localidad"), null, null));
                Provincia provincia2 = new Provincia();
                provincia2.setId_provincia(rs.getInt("id_provincia"));
                cliente.setProvincia(provincia2);
                cliente.setCorreo_electronico(rs.getString("correo_electronico"));
                cliente.setTelefono(rs.getString("telefono"));
                
                UsuarioNeg usuarioNeg = new NegocioImpl.UsuarioNegImpl();
                Usuario usuario = usuarioNeg.obtenerUsuarioPorId(rs.getInt("id_usuario"));
                cliente.setUsuario(usuario);
                
                cliente.setActivo(rs.getBoolean("activo"));
            }
            ps.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }

	@Override
	public Cliente obtenerClientePorDni(long dni) {
		Cliente cliente = null;
		Conexion conexion = new Conexion();
		Connection conn = conexion.Open();
		String sql = "SELECT * FROM Cliente WHERE dni = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, dni);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cliente = new Cliente();
				cliente.setDni(rs.getInt("dni"));
				cliente.setCuil(rs.getString("cuil"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setNacionalidad(rs.getString("nacionalidad"));
				java.sql.Date fechaNacimientoSQL = rs.getDate("fecha_nacimiento");
				if (fechaNacimientoSQL != null) {
					cliente.setFecha_nacimiento(fechaNacimientoSQL.toLocalDate());
				}
				cliente.setDireccion(rs.getString("direccion"));
				
				Localidad localidad = new Localidad();
				localidad.setId_localidad(rs.getInt("id_localidad"));
				cliente.setLocalidad(localidad);
				
				Provincia provincia = new Provincia();
				provincia.setId_provincia(rs.getInt("id_provincia"));
				cliente.setProvincia(provincia);
				cliente.setCorreo_electronico(rs.getString("correo_electronico"));
				cliente.setTelefono(rs.getString("telefono"));
				
				Usuario usuario = new Usuario();
				usuario.setId_usuario(rs.getInt("id_usuario"));
				cliente.setUsuario(usuario);
				
				cliente.setActivo(rs.getBoolean("activo"));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}
	
	
	public Cliente obtenerClienteConLocalidadProvincia(int idUsuario) {
	    Cliente cliente = null;
	    conexion = new Conexion();
	    conexion.Open();

	    String query = "SELECT c.*, l.id_localidad, l.descripcion AS nombre_localidad, " +
	                   "p.id_provincia, p.descripcion AS nombre_provincia " +
	                   "FROM Cliente c " +
	                   "INNER JOIN Localidad l ON c.id_localidad = l.id_localidad " +
	                   "INNER JOIN Provincia p ON l.id_provincia = p.id_provincia " +
	                   "WHERE c.id_usuario = ? AND c.activo = 1";

	    try {
	        PreparedStatement ps = conexion.connection.prepareStatement(query);
	        ps.setInt(1, idUsuario);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            cliente = new Cliente();
	            cliente.setDni(rs.getInt("dni"));
	            cliente.setCuil(rs.getString("cuil"));
	            cliente.setNombre(rs.getString("nombre"));
	            cliente.setApellido(rs.getString("apellido"));
	            cliente.setSexo(rs.getString("sexo"));
	            cliente.setNacionalidad(rs.getString("nacionalidad"));

	            java.sql.Date fechaNacimientoSQL = rs.getDate("fecha_nacimiento");
	            if (fechaNacimientoSQL != null)
	                cliente.setFecha_nacimiento(fechaNacimientoSQL.toLocalDate());

	            cliente.setDireccion(rs.getString("direccion"));
	            cliente.setCorreo_electronico(rs.getString("correo_electronico"));
	            cliente.setTelefono(rs.getString("telefono"));
	            cliente.setActivo(rs.getBoolean("activo"));

	           
	            UsuarioNeg usuarioNeg = new NegocioImpl.UsuarioNegImpl();
	            Usuario usuario = usuarioNeg.obtenerUsuarioPorId(rs.getInt("id_usuario"));
	            cliente.setUsuario(usuario);

	           
	            Provincia provincia = new Provincia();
	            provincia.setId_provincia(rs.getInt("id_provincia"));
	            provincia.setDescripcion(rs.getString("nombre_provincia"));

	            
	            Localidad localidad = new Localidad();
	            localidad.setId_localidad(rs.getInt("id_localidad"));
	            localidad.setDescripcion(rs.getString("nombre_localidad"));
	            localidad.setProvincia(provincia);

	            cliente.setLocalidad(localidad);
	            cliente.setProvincia(provincia); 
	        }

	        ps.close();
	        conexion.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return cliente;
	}


}
