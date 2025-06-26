package NegocioImpl;

import java.util.List;

import Datos.LocalidadDao;
import DatosImpl.LocalidadDaoImpl;
import Dominio.Localidad;
import Dominio.Provincia;
import Negocio.LocalidadNeg;

public class LocalidadNegImpl implements LocalidadNeg {

	LocalidadDao LDao= new LocalidadDaoImpl();

	@Override
	public List<Localidad> obtenerLocalidades(int provincia) {
	
		return LDao.obtenerLocalidadesPorProvincia(provincia);
	}
	
	
}
