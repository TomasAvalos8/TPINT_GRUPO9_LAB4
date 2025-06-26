package Datos;

import Dominio.Usuario;

public interface UsuarioDao {

	public boolean insertarUsuario(Usuario usuario);
	public int insertarUsuarioYDevuelveId(Usuario usuario);
	public Usuario login(String usuarioInput, String contraseñaInput);
	public Usuario obtenerUsuarioPorId(int idUsuario);
	public boolean actualizarUsuario(Usuario usuario);
}
