package Datos;

import java.util.List;

import Dominio.Cliente;

public interface ClienteDao {

	public boolean insertarCliente(Cliente cliente);

	List<Cliente> listarClientes();
	public boolean eliminarCliente(int id);
	public boolean actualizarCliente(Cliente cliente);
	public Cliente obtenerClientePorIdUsuario(int idUsuario);
}
