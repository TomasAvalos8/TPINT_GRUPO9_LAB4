package DatosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Datos.UsuarioDao;
import Dominio.TipoUsuario;
import Dominio.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {
	
	private Conexion conexion;

	@Override
	public boolean insertarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
		boolean estado = true;
		conexion = new Conexion();
		conexion.Open();
		String query = "INSERT INTO Usuarios (id_tipo_usuario, usuario, contraseña, fecha_alta, activo) " +
		              "VALUES ('"+ usuario.getTipoUsuario().getIdTipoUsuario()+"'," +
		              "'"+usuario.getUsuario()+"'," +
		              "'"+usuario.getContraseña()+"'," +
		              "'"+usuario.getFechaAlta()+"', 1)";
		
		try {
			
			estado = conexion.execute(query);
				
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		finally {
			conexion.close();
		}
		
		return estado;
		
	}
	
	public int insertarUsuarioYDevuelveId(Usuario usuario) {
		// TODO Auto-generated method stub
		
		int idGuardado = 0;
		conexion = new Conexion();
		conexion.Open();
		String query = "INSERT INTO Usuarios (id_tipo_usuario, usuario, contraseña, fecha_alta) VALUES ('"+ usuario.getTipoUsuario().getIdTipoUsuario()+"','"+usuario.getUsuario()+"','"+usuario.getContraseña()+"','"+usuario.getFechaAlta()+"') ";
		
		try {
			
			idGuardado = conexion.executeDevuelveIdUsuario(query);
				
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		finally {
			conexion.close();
		}
		
		return idGuardado;
		
	}
	
	@Override
	public Usuario login(String usuarioInput, String contraseñaInput) {
	    Usuario usuario = null;
	    conexion = new Conexion();
	    conexion.Open();

	    String query = "SELECT u.id_usuario, u.usuario, u.contraseña, u.id_tipo_usuario, t.descripcion " +
	                   "FROM Usuarios u " +
	                   "JOIN TiposUsuarios t ON u.id_tipo_usuario = t.id_tipo_usuario " +
	                   "WHERE u.usuario = '" + usuarioInput + "' AND u.contraseña = '" + contraseñaInput + "'";

	    try {
	        ResultSet rs = conexion.executeQuery(query);
	        if (rs.next()) {
	            usuario = new Usuario();
	            usuario.setId_usuario(rs.getInt("id_usuario"));
	            usuario.setUsuario(rs.getString("usuario"));
	            usuario.setContraseña(rs.getString("contraseña"));

	            TipoUsuario tipo = new TipoUsuario();
	            tipo.setIdTipoUsuario(rs.getInt("id_tipo_usuario"));
	            tipo.setDescripcion(rs.getString("descripcion"));

	            usuario.setTipoUsuario(tipo);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        conexion.close();
	    }

	    return usuario;
	}

	public Usuario obtenerUsuarioPorId(int idUsuario) {
		Usuario usuario = null;
		Conexion conexion = new Conexion();
		conexion.Open();
		String query = "SELECT id_usuario, usuario, contraseña, id_tipo_usuario FROM Usuarios WHERE id_usuario = " + idUsuario;
		try {
			ResultSet rs = conexion.executeQuery(query);
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId_usuario(rs.getInt("id_usuario"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setContraseña(rs.getString("contraseña"));
				TipoUsuario tipo = new TipoUsuario();
				tipo.setIdTipoUsuario(rs.getInt("id_tipo_usuario"));
				usuario.setTipoUsuario(tipo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}
		return usuario;
	}
	
	@Override
	public boolean actualizarUsuario(Usuario usuario) {
		PreparedStatement ps = null;
		Conexion cn = new Conexion();
		Connection conexion = cn.Open();
		boolean actualizado = false;
		try {
			String sql = "UPDATE Usuarios SET usuario=? WHERE id_usuario=?";
			ps = conexion.prepareStatement(sql);
			ps.setString(1, usuario.getUsuario());
			ps.setInt(2, usuario.getId_usuario());
			actualizado = ps.executeUpdate() > 0;
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
	
	@Override
	public ArrayList<Usuario> listarUsuarios() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		conexion = new Conexion();
		conexion.Open();
		String query = "SELECT u.id_usuario, u.usuario, u.contraseña, u.fecha_alta, u.id_tipo_usuario, t.descripcion as tipo_desc " +
					  "FROM Usuarios u " +
					  "LEFT JOIN TiposUsuarios t ON u.id_tipo_usuario = t.id_tipo_usuario " +
					  "WHERE u.activo = 1 " +
					  "ORDER BY u.id_usuario";
		
		try {
			ResultSet rs = conexion.executeQuery(query);
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId_usuario(rs.getInt("id_usuario"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setContraseña(rs.getString("contraseña"));
				usuario.setFechaAlta(rs.getDate("fecha_alta").toLocalDate());
				
				TipoUsuario tipo = new TipoUsuario();
				tipo.setIdTipoUsuario(rs.getInt("id_tipo_usuario"));
				tipo.setDescripcion(rs.getString("tipo_desc"));
				usuario.setTipoUsuario(tipo);
				lista.add(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}
		return lista;
	}

	@Override
	public boolean eliminarUsuario(int usuario) {
		boolean eliminado = false;
		conexion = new Conexion();
		Connection conn = conexion.Open();
		PreparedStatement ps = null;
		
		try {
			
			String sql = "UPDATE Usuarios SET activo = false WHERE id_usuario = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, usuario);
		
			
			eliminado = ps.executeUpdate() > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conexion.close();
		}
		
		return eliminado;
	}

}



