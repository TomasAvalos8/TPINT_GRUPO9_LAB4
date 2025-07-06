package Negocio;

import java.util.List;
import Dominio.Movimiento;

public interface MovimientoNeg {

	public List<Movimiento> listarMovimientosPorCuenta(int NroCuenta);
}
