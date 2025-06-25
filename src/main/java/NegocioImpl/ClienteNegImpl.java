package NegocioImpl;

import Dominio.Cliente;
import Negocio.ClienteNeg;
import Datos.ClienteDao;
import DatosImpl.ClienteDaoImpl;

public class ClienteNegImpl implements ClienteNeg{

	private ClienteDao cDao= new ClienteDaoImpl();

	@Override
	public boolean insertar(Cliente c) {
		
		return cDao.insertarCliente(c);
	}
	
}
