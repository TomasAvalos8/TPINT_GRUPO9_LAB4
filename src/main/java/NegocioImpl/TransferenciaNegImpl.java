package NegocioImpl;

import java.sql.Date;

import Negocio.TransferenciaNeg;
import Datos.TransferenciaDao;
import DatosImpl.TransferenciaDaoImpl;

public class TransferenciaNegImpl implements TransferenciaNeg{

	private TransferenciaDao transferenciadao = new TransferenciaDaoImpl();

	@Override
	public boolean transferirCuenta(int CuentaSaliente, int CuentaDestino, Float monto, Date fecha) {
		
		return transferenciadao.Transferir(CuentaSaliente, CuentaDestino, monto, fecha);
	}
	
	

}
