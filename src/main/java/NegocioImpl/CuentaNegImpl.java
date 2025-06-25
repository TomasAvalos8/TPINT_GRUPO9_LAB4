package NegocioImpl;

import Dominio.Cuenta;
import Datos.CuentaDao;
import DatosImpl.CuentaDaoImpl;
import Dominio.Cuenta;
import Negocio.CuentaNeg;

public class CuentaNegImpl implements CuentaNeg {

	CuentaDao cuentadao = new CuentaDaoImpl();
	
	public boolean crearCuenta(Cuenta cuenta) {
		
		boolean estado = false;

	    if (cuenta.getCBU() != null && cuenta.getCBU().trim().length() > 0 &&
	        cuenta.getCreacion() != null && cuenta.getSaldo() >= 0) {
	        
	        estado = cuentadao.crearCuenta(cuenta);
	    }

	    return estado;
	}

}