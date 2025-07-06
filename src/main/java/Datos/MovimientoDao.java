package Datos;

import java.util.List;

import Dominio.Movimiento;

public interface MovimientoDao {

public List<Movimiento> listarMovimientosPorCuenta(int NroCuenta);
	
}
