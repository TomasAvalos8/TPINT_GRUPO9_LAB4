package Datos;

import java.sql.Date;

import Dominio.Cuenta;

public interface TransferenciaDao {
	
	boolean Transferir(Cuenta CuentaSaliente, Cuenta CuentaDestino, float monto, Date fecha);

}
