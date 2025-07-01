package Datos;

import java.sql.Date;

public interface TransferenciaDao {
	
	boolean Transferir(int CuentaSaliente, int CuentaDestino, Float monto, Date fecha);

}
