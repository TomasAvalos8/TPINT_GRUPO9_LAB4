package NegocioImpl;

import java.sql.Date;

import Negocio.TransferenciaNeg;
import Datos.TransferenciaDao;
import DatosImpl.TransferenciaDaoImpl;
import Dominio.Cuenta;

public class TransferenciaNegImpl implements TransferenciaNeg{

	private TransferenciaDao transferenciadao = new TransferenciaDaoImpl();

	@Override
	public boolean transferirCuenta(Cuenta CuentaSaliente, Cuenta CuentaDestino, float monto, Date fecha) {
		
		return transferenciadao.Transferir(CuentaSaliente, CuentaDestino, monto, fecha);
	}
	
	

}
