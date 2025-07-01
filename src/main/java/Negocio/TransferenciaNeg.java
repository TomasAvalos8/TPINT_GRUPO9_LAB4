package Negocio;

import java.sql.Date;

public interface TransferenciaNeg {
	
	public boolean transferirCuenta(int CuentaSaliente, int CuentaDestino, Float monto, Date fecha);
}
