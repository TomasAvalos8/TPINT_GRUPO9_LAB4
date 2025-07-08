package Negocio;

import java.util.List;
import Dominio.Movimiento;

public interface MovimientoNeg {

	public List<Movimiento> listarMovimientosPorCuenta(int NroCuenta);
	public List<Movimiento> listarMovimientosPorCuentaYFechas(int nroCuenta, String fechaDesde, String fechaHasta);
	public void insertarMovimiento(Movimiento mov);
}
