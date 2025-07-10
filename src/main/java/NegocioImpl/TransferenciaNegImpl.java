package NegocioImpl;

import java.sql.Date;

import Negocio.TransferenciaNeg;
import Datos.TransferenciaDao;
import DatosImpl.TransferenciaDaoImpl;
import Dominio.Cuenta;

public class TransferenciaNegImpl implements TransferenciaNeg{

	private TransferenciaDao transferenciadao = new TransferenciaDaoImpl();

	@Override
	public int transferirCuenta(Cuenta CuentaSaliente, Cuenta CuentaDestino, float monto, String concepto, Date fecha) {
		
		return transferenciadao.transferir(CuentaSaliente, CuentaDestino, monto, concepto, fecha);
	}
	
	

}
