package NegocioImpl;

import Negocio.UsuarioNeg;
import Datos.UsuarioDao;
import DatosImpl.UsuarioDaoImpl;
import Dominio.Usuario; 

public class UsuarioNegImpl implements UsuarioNeg {

	public UsuarioDao  usDao = new UsuarioDaoImpl();
	
	@Override
	public boolean insertar(Usuario u) {
		
		return usDao.insertarUsuario(u);
	}

	@Override
	public int insertarYDevuelveId(Usuario u) {
		
		return usDao.insertarUsuarioYDevuelveId(u);
	}

	@Override
	public Usuario obtenerUsuarioPorId(int idUsuario) {
		return usDao.obtenerUsuarioPorId(idUsuario);
	}

	@Override
	public boolean actualizarUsuario(Usuario u) {
		return usDao.actualizarUsuario(u);
	}

}
