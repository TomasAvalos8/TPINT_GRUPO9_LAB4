package Negocio;

import java.util.ArrayList;
import Dominio.Usuario;

public interface UsuarioNeg {

	public boolean insertar(Usuario u);
	public int insertarYDevuelveId(Usuario u);
	public Usuario obtenerUsuarioPorId(int idUsuario);
	public boolean actualizarUsuario(Usuario u);
	public ArrayList<Usuario> listarUsuarios();
	public boolean eliminar(String usuario);
}
