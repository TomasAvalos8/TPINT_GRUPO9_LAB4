package Negocio;

import java.sql.Date;

import Dominio.Cuenta;

public interface TransferenciaNeg {
	
	public boolean transferirCuenta(Cuenta CuentaSaliente, Cuenta CuentaDestino, Float monto, Date fecha);
}
