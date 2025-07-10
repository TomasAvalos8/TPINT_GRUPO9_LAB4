package Negocio;

import java.sql.Date;

import Dominio.Cuenta;

public interface TransferenciaNeg {
	
	public int transferirCuenta(Cuenta CuentaSaliente, Cuenta CuentaDestino, float monto, String concepto, Date fecha);
}
