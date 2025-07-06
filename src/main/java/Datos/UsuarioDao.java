package Datos;

import java.util.ArrayList;
import Dominio.Usuario;

public interface UsuarioDao {

	public boolean insertarUsuario(Usuario usuario);
	public int insertarUsuarioYDevuelveId(Usuario usuario);
	public Usuario login(String usuarioInput, String contraseñaInput);
	public Usuario obtenerUsuarioPorId(int idUsuario);
	public boolean actualizarUsuario(Usuario usuario);
	public ArrayList<Usuario> listarUsuarios();
	public boolean eliminarUsuario(int usuario);
	boolean recuperarContraseña(String usuario, String dni, String nuevaContraseña);
	public boolean recuperarContraseñaCliente(String usuario, String nuevaContraseña);
}
