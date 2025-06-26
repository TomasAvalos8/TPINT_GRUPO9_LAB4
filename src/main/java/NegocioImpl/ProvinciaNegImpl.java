package NegocioImpl;

import java.util.List;

import Datos.ProvinciaDao;
import DatosImpl.ProvinciaDaoImpl;
import Dominio.Provincia;
import Negocio.ProvinciaNeg;

public class ProvinciaNegImpl implements ProvinciaNeg{

	ProvinciaDao PDao = new ProvinciaDaoImpl();

	@Override
	public List<Provincia> obtenerProvincias() {
		// TODO Auto-generated method stub
		return PDao.obtenerTodasLasProvincias();
	}
	
	
}
