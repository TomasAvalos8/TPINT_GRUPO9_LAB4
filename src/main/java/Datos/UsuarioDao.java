package Datos;

import Dominio.Usuario;

public interface UsuarioDao {

	public boolean insertarUsuario(Usuario usuario);
	public Usuario login(String usuarioInput, String contraseñaInput);
}
