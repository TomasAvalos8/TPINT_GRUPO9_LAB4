package DatosImpl;

import Datos.UsuarioDao;
import Dominio.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {
	
	private Conexion conexion;

	@Override
	public boolean insertarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
		boolean estado = true;
		conexion = new Conexion();
		conexion.Open();
		String query = "INSERT INTO Usuarios (id_tipo_usuario, usuario, contraseña) VALUES ('"+ usuario.getTipoUsuario().getIdTipoUsuario()+"','"+usuario.getUsuario()+"','"+usuario.getContraseña()+"') ";
		
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
	
}



