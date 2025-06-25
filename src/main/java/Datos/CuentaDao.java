package Datos;

import Dominio.Cuenta;
import Excepciones.ClienteNoExisteException;

public interface CuentaDao {
	public boolean crearCuenta(Cuenta cuenta) throws ClienteNoExisteException;
	public int obtenerSiguienteIdCuenta();
}
