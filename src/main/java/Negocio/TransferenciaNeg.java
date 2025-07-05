package Negocio;

import java.sql.Date;

import Dominio.Cuenta;

public interface TransferenciaNeg {
	
	public boolean transferirCuenta(Cuenta CuentaSaliente, Cuenta CuentaDestino, float monto, Date fecha);
}
