package Datos;

import java.util.List;

import Dominio.Cuenta;
import Excepciones.ClienteNoExisteException;

public interface CuentaDao {
	public boolean crearCuenta(Cuenta cuenta) throws ClienteNoExisteException;
	public int obtenerSiguienteIdCuenta();
	public List<Cuenta> obtenerTodasLasCuentas();
	public boolean eliminarCuenta(int id);
    public boolean actualizarCuenta(Cuenta cuenta);
    public List<Cuenta> obtenerCuentasPorTipo(int idTipoCuenta);
}
