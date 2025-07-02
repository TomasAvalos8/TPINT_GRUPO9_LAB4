package NegocioImpl;

import Dominio.Cliente;
import Negocio.ClienteNeg;

import java.util.List;

import Datos.ClienteDao;
import DatosImpl.ClienteDaoImpl;

public class ClienteNegImpl implements ClienteNeg{

	private ClienteDao cDao= new ClienteDaoImpl();

	@Override
	public boolean insertar(Cliente c) {
		
		return cDao.insertarCliente(c);
	}
	@Override
    public List<Cliente> listarClientes() {
        return cDao.listarClientes();
    }
	
	public boolean eliminarCliente(int id) {
		return cDao.eliminarCliente(id);
	}
	
	public boolean actualizarCliente(Cliente cliente){
		return cDao.actualizarCliente(cliente);
		
	}
	@Override
	public Cliente obtenerClientePorIdUsuario(int idUsuario) {
		// TODO Auto-generated method stub
		return cDao.obtenerClientePorIdUsuario(idUsuario);
	}
}
