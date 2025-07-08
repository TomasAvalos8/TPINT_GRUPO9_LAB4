package Datos;

import java.util.List;

import Dominio.Movimiento;

public interface MovimientoDao {

public List<Movimiento> listarMovimientosPorCuenta(int NroCuenta);
public List<Movimiento> listarMovimientosPorCuentaYFechas(int nroCuenta, String fechaDesde, String fechaHasta);
public void insertarMovimiento(Movimiento mov);
	
}
