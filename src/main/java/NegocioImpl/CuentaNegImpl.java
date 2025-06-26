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
}