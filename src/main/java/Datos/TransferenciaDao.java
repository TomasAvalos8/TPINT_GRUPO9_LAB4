package Datos;

import java.sql.Date;

import Dominio.Cuenta;

public interface TransferenciaDao {
	
	public int transferir(Cuenta CuentaSaliente, Cuenta CuentaDestino, float monto, Date fecha);

}
