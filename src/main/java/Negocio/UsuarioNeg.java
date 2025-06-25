package Negocio;

import Dominio.Usuario;

public interface UsuarioNeg {

	public boolean insertar(Usuario u);
	public int insertarYDevuelveId(Usuario u);
}
