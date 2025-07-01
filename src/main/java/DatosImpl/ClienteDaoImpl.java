package DatosImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import Datos.ClienteDao;
import Dominio.Cliente;

public class ClienteDaoImpl implements ClienteDao {
private Conexion conexion;
	@Override
	public boolean insertarCliente(Cliente cliente) {
	    PreparedStatement ps = null;
	    Conexion cn = new Conexion();
	    Connection conexion = cn.Open();
	    boolean estado = false;

	    try {
	        String sql = "INSERT INTO Cliente (dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, id_localidad, id_provincia, correo_electronico, telefono, id_usuario, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        ps = (PreparedStatement) conexion.prepareStatement(sql);
	        
	        ps.setInt(1, cliente.getDni());
	        ps.setString(2, cliente.getCuil());
	        ps.setString(3, cliente.getNombre());
	        ps.setString(4, cliente.getApellido());
	        ps.setString(5, cliente.getSexo());
	        ps.setString(6, cliente.getNacionalidad());
	        ps.setDate(7, java.sql.Date.valueOf(cliente.getFecha_nacimiento()));
	        ps.setString(8, cliente.getDireccion());
	        ps.setInt(9, cliente.getId_localidad());
	        ps.setInt(10, cliente.getId_provincia());
	        ps.setString(11, cliente.getCorreo_electronico());
	        ps.setString(12, cliente.getTelefono());
	        ps.setInt(13, cliente.getIdUsuario());
	        ps.setBoolean(14, cliente.getActivo());

	        estado = ps.executeUpdate() > 0;
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
	    return estado;
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
                cliente.setId_localidad(rs.getInt("id_localidad"));
                cliente.setId_provincia(rs.getInt("id_provincia"));
                cliente.setCorreo_electronico(rs.getString("correo_electronico"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setIdUsuario(rs.getInt("id_usuario"));
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
            ps.setInt(8, cliente.getId_localidad());
            ps.setInt(9, cliente.getId_provincia());
            ps.setString(10, cliente.getCorreo_electronico());
            ps.setString(11, cliente.getTelefono());
            ps.setInt(12, cliente.getIdUsuario());
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
                cliente.setId_localidad(rs.getInt("id_localidad"));
                cliente.setId_provincia(rs.getInt("id_provincia"));
                cliente.setCorreo_electronico(rs.getString("correo_electronico"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setIdUsuario(rs.getInt("id_usuario"));
                cliente.setActivo(rs.getBoolean("activo"));
            }
            ps.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }

}
