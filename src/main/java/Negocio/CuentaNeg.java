package Negocio;

import Dominio.Cuenta;
import Excepciones.ClienteNoExisteException;

public interface CuentaNeg {
	public boolean crearCuenta(Cuenta cuenta) throws ClienteNoExisteException;
	
}
