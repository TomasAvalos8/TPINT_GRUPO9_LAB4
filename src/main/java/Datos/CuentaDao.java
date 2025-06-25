package Datos;

import Dominio.Cuenta;

public interface CuentaDao {
	public boolean crearCuenta(Cuenta cuenta);
	public int obtenerSiguienteIdCuenta();
}
