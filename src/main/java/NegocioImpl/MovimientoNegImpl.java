package NegocioImpl;

import java.util.List;

import Dominio.Movimiento;
import Negocio.MovimientoNeg;
import Datos.MovimientoDao;
import DatosImpl.MovimientoDaoImpl;

public class MovimientoNegImpl implements MovimientoNeg {

	private MovimientoDao mDao = new MovimientoDaoImpl();
		public List<Movimiento> listarMovimientosPorCuenta(int NroCuenta) {
			return mDao.listarMovimientosPorCuenta(NroCuenta);
	}

}
