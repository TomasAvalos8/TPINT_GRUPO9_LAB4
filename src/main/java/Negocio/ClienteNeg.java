package Negocio;

import Dominio.Cliente;
import Excepciones.ClienteYaExisteException;
import java.util.List;
public interface ClienteNeg {
	
	public boolean insertar(Cliente c) throws ClienteYaExisteException;
	public List<Cliente> listarClientes();
	public boolean eliminarCliente(int id);
	public boolean actualizarCliente(Cliente cliente);
	
	Cliente obtenerClientePorIdUsuario(int idUsuario);
	Cliente obtenerClienteConLocalidadProvincia(int idUsuario);
}
