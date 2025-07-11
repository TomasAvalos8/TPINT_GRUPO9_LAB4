package Negocio;

import java.util.ArrayList;
import Dominio.Usuario;

public interface UsuarioNeg {

	public boolean insertar(Usuario u);
	public int insertarYDevuelveId(Usuario u);
	public Usuario obtenerUsuarioPorId(int idUsuario);
	public boolean actualizarUsuario(Usuario u);
	public ArrayList<Usuario> listarUsuarios();
	public boolean eliminarUsuario(int usuario);
	boolean recuperarContraseña(String usuario, String dni, String nuevaContraseña);
	boolean recuperarContraseñaCliente(String usuario, String NuevaContraseña);
	Usuario login(String user, String pass);
}
