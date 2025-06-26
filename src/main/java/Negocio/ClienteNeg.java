package Negocio;

import Dominio.Cliente;
import java.util.List;
public interface ClienteNeg {
	
	public boolean insertar(Cliente c);
	public List<Cliente> listarClientes();
	public boolean eliminarCliente(int id);
}
