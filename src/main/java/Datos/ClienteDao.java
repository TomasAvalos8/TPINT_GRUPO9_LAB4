package Datos;

import java.util.List;

import Dominio.Cliente;
import Excepciones.ClienteYaExisteException;

public interface ClienteDao {

	public boolean insertarCliente(Cliente cliente) throws ClienteYaExisteException;

	List<Cliente> listarClientes();
	public boolean eliminarCliente(int id);
	public boolean actualizarCliente(Cliente cliente);
	public Cliente obtenerClientePorIdUsuario(int idUsuario);
	public Cliente obtenerClientePorDni(long dni);
	public Cliente obtenerClienteConLocalidadProvincia(int idUsuario);
}
