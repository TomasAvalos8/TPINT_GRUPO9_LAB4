package NegocioImpl;

import Dominio.Cuenta;
import Excepciones.ClienteNoExisteException;
import Datos.CuentaDao;
import DatosImpl.CuentaDaoImpl;
import Negocio.CuentaNeg;
import java.util.List;

public class CuentaNegImpl implements CuentaNeg {

	CuentaDao cuentadao = new CuentaDaoImpl();
	
	@Override
	public boolean crearCuenta(Cuenta cuenta) throws ClienteNoExisteException {
		boolean estado = false;
	    if (cuenta.getCBU() != null && cuenta.getCBU().trim().length() > 0 &&
	        cuenta.getCreacion() != null && cuenta.getSaldo() >= 0) {
	        estado = cuentadao.crearCuenta(cuenta);
	    }
	    return estado;
	}

	@Override
	public boolean eliminarCuenta(int id) {
		return cuentadao.eliminarCuenta(id);
	}

	@Override
	public boolean actualizarCuenta(Cuenta cuenta) {
		return cuentadao.actualizarCuenta(cuenta);
	}

	@Override
	public List<Cuenta> obtenerTodasLasCuentas() {
		return cuentadao.obtenerTodasLasCuentas();
	}

	@Override
	public int obtenerSiguienteIdCuenta() {
		return cuentadao.obtenerSiguienteIdCuenta();
	}

	@Override
	public List<Cuenta> obtenerCuentasPorTipo(int idTipoCuenta) {
		CuentaDao cuentaDao = new CuentaDaoImpl();
	    return cuentaDao.obtenerCuentasPorTipo(idTipoCuenta);
	}

	@Override
	public List<Cuenta> obtenerCuentasPorDniCliente(int dniCliente) {
		// TODO Auto-generated method stub
		return cuentadao.obtenerCuentasPorDni(dniCliente);
	}

	@Override
	public void depositarEnCuenta(int idCuenta, double monto) {
		cuentadao.depositarEnCuenta(idCuenta, monto);
	}

	@Override
	public List<Cuenta> obtenerCuentasPorIdUsuario(int idUsuario) {
		return cuentadao.obtenerCuentasPorIdUsuario(idUsuario);
	}

	@Override
	public Cuenta obtenerCuentaPorCBU(String cbu) {
		return cuentadao.obtenerCuentaPorCBU(cbu);
	}

	@Override
	public Cuenta obtenerCuentaPorId(int id) {
		return cuentadao.obtenerCuentaPorId(id);
	}

	@Override
	public List<Cuenta> obtenerCuentasPorDni(int dni) {
		return cuentadao.obtenerCuentasPorDni(dni);
	}
}