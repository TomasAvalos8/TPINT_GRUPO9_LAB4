package Negocio;

import Dominio.Cuenta;
import Excepciones.ClienteNoExisteException;
import java.util.List;

public interface CuentaNeg {
	public boolean crearCuenta(Cuenta cuenta) throws ClienteNoExisteException;
	public boolean eliminarCuenta(int id);
	public boolean actualizarCuenta(Cuenta cuenta);
	public List<Cuenta> obtenerTodasLasCuentas();
	public int obtenerSiguienteIdCuenta();
	
	public List<Cuenta> obtenerCuentasPorTipo(int idTipoCuenta);
	List<Cuenta> obtenerCuentasPorDniCliente(int dniCliente);
	void depositarEnCuenta(int idCuenta, double monto);
}
